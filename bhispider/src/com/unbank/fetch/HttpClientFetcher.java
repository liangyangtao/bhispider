package com.unbank.fetch;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.LinkedBlockingQueue;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.unbank.entity.Information;
import com.unbank.queue.BaseQueue;

public class HttpClientFetcher extends BaseQueue implements Runnable {
	private static Logger logger = LoggerFactory
			.getLogger(HttpClientFetcher.class);
	private final String _DEFLAUT_CHARSET = "utf-8";
	protected LinkedBlockingQueue<Object> informationQueue;
	private CloseableHttpClient httpClient;
	private BasicCookieStore cookieStore;
	private RequestConfig requestConfig = RequestConfig.custom()
			.setSocketTimeout(1000).setConnectTimeout(1000).build();

	public HttpClientFetcher(LinkedBlockingQueue<Object> informationQueue,
			CloseableHttpClient httpClient, BasicCookieStore cookieStore) {
		this.cookieStore = cookieStore;
		this.httpClient = httpClient;
		this.informationQueue = informationQueue;
	}

	@Override
	public void run() {
		while (true) {
			try {
				if (informationQueue.size() <= 10000
						&& informationQueue.size() > 0) {
					Information information = null;
					information = (Information) take(informationQueue);
					if (information != null) {
						consumeInformation(information);
					}
				}
				sleeping((int) Math.random() * 1000);
			} catch (Exception e) {
				logger.info("", e);
				continue;
			}

		}

	}

	private void fillHeader(String url, HttpGet httpGet) {
		httpGet.setHeader("User-Agent",
				"Mozilla/5.0 (Windows NT 5.1; rv:29.0) Gecko/20100101 Firefox/29.0");
		httpGet.setHeader("Accept",
				"text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
		httpGet.setHeader("Accept-Language",
				"zh-cn,zh;q=0.8,en-us;q=0.5,en;q=0.3");
		httpGet.setHeader("Accept-Encoding", "gzip, deflate");
		httpGet.setHeader("Connection", "keep-alive");
		httpGet.setHeader("Referer", url);
		httpGet.setHeader("Cache-Control", "max-age=0");
	}

	private void consumeInformation(Information information) {
		HttpClientContext context = HttpClientContext.create();
		context.setCookieStore(cookieStore);
		String useCharset = information.getCharset();
		if (useCharset == null) {
			useCharset = _DEFLAUT_CHARSET;
		}
		String url = information.getUrl();
		HttpGet httpGet = new HttpGet(url);
		fillHeader(url, httpGet);
		httpGet.setConfig(requestConfig);
		try {
			CloseableHttpResponse response = httpClient.execute(httpGet,
					context);
			try {
				HttpEntity entity = response.getEntity();
				information
						.setContent(EntityUtils.toString(entity, useCharset));
			} finally {
				response.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public String get(String url) {
		return get(url, _DEFLAUT_CHARSET);
	}

	public String get(String url, String charset) {
		return get(url, null, charset);
	}

	public String get(String url, Map<String, String> headers, String charset) {
		HttpClientContext context = HttpClientContext.create();
		context.setCookieStore(cookieStore);
		String useCharset = charset;
		if (charset == null) {
			useCharset = _DEFLAUT_CHARSET;
		}
		HttpGet httpGet = new HttpGet(url);
		if (headers != null) {
			for (String key : headers.keySet()) {
				httpGet.setHeader(key, headers.get(key));
			}
		}
		httpGet.setConfig(requestConfig);
		try {
			CloseableHttpResponse response = httpClient.execute(httpGet,
					context);
			try {
				HttpEntity entity = response.getEntity();
				return EntityUtils.toString(entity, useCharset);
			} finally {
				response.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public String post(String url, Map<String, String> params) {
		return post(url, params, null, null);
	}

	public String post(String url, Map<String, String> params,
			Map<String, String> headers) {
		return post(url, params, headers, null);
	}

	public String post(String url, Map<String, String> params, String charset) {
		return post(url, params, null, charset);
	}

	public String post(String url, Map<String, String> params,
			Map<String, String> headers, String charset) {
		HttpClientContext context = HttpClientContext.create();
		context.setCookieStore(cookieStore);
		String useCharset = charset;
		if (charset == null) {
			useCharset = _DEFLAUT_CHARSET;
		}
		try {
			HttpPost httpPost = new HttpPost(url);
			if (headers != null) {
				for (String key : headers.keySet()) {
					httpPost.setHeader(key, headers.get(key));
				}
			}
			List<NameValuePair> nvps = new ArrayList<NameValuePair>();
			if (params != null) {
				for (String key : params.keySet()) {
					nvps.add(new BasicNameValuePair(key, params.get(key)));
				}
				httpPost.setEntity(new UrlEncodedFormEntity(nvps));
			}
			httpPost.setConfig(requestConfig);
			CloseableHttpResponse response = httpClient.execute(httpPost,
					context);
			try {
				HttpEntity entity = response.getEntity();
				return EntityUtils.toString(entity, useCharset);
			} finally {
				response.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public String getCookie(String key) {
		List<Cookie> cookies = cookieStore.getCookies();
		if (cookies != null) {
			for (Cookie c : cookies) {
				if (c.getName().equals(key)) {
					return c.getValue();
				}
			}
		}
		return null;
	}

	public void printCookies() {
		System.out.println("---查看当前Cookie---");
		List<Cookie> cookies = cookieStore.getCookies();
		if (cookies != null) {
			for (Cookie c : cookies) {
				System.out.print(c.getName() + "         :" + c.getValue());
				System.out.print("  domain:" + c.getDomain());
				System.out.print("  expires:" + c.getExpiryDate());
				System.out.print("  path:" + c.getPath());
				System.out.println("    version:" + c.getVersion());
			}
		}
	}

}
