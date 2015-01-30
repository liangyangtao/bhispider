package com.unbank.test;

import java.util.Date;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;

import com.unbank.fetch.HttpClientBuilder;

public class BhiPaser {
	private static RequestConfig requestConfig = RequestConfig.custom()
			.setSocketTimeout(30000).setConnectTimeout(30000)
			.setStaleConnectionCheckEnabled(true)
			.setCircularRedirectsAllowed(true).setMaxRedirects(50).build();
	private static BasicCookieStore cookieStore = new BasicCookieStore();
	static CloseableHttpClient httpClient;
	private static String SessionId;
	private static String code;

	public static void main(String[] args) {
		PoolingHttpClientConnectionManager poolingHttpClientConnectionManager = new PoolingHttpClientConnectionManager();
		HttpClientBuilder httpClientBuilder = new HttpClientBuilder(false,
				poolingHttpClientConnectionManager, cookieStore);
		httpClient = httpClientBuilder.getHttpClient();
		String url = "http://www.bhi.com.cn/projects/ItemList.aspx?a=1&p=t";
		getNoCookieHtml(url, "utf-8");
		System.out.println(SessionId);
		System.out.println(code);
		SessionId = cookiesToString();
		url = "http://www.bhi.com.cn/Public/Isvalid.ashx?num=0.37273750442937625";
		getNoCookieHtml(url, "utf-8");
		System.out.println(SessionId);
		System.out.println(code);
		url = "http://www.bhi.com.cn/Public/IsExistValid.aspx?_=1421027525311";
		code = getNoCookieHtml(url, "utf-8");
		System.out.println(SessionId);
		System.out.println(code);
		url = "http://www.bhi.com.cn/Projects/ShowDetails.aspx?id=10921462";
		String html = getHtml(url, "utf-8");
		System.out.println(html);
		// System.out.println(SessionId);
		// System.out.println(code);

	}

	public static String getNoCookieHtml(String url, String charset) {
		HttpClientContext context = HttpClientContext.create();
		context.setCookieStore(cookieStore);
		String useCharset = charset;
		HttpGet httpGet = new HttpGet(url);
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

	public static String getHtml(String url, String charset) {
		HttpClientContext context = HttpClientContext.create();
		context.setCookieStore(cookieStore);
		String useCharset = charset;
		HttpGet httpGet = new HttpGet(url);
		fillHeader(url, httpGet);
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

	private static void fillHeader(String url, HttpGet httpGet) {
		String starttTime = String.valueOf(new Date().getTime()).substring(0,
				10);
		String endTime = String.valueOf(new Date().getTime() + 60).substring(0,
				10);
		httpGet.setHeader(
				"User-Agent",
				"Mozilla/5.0 (Windows NT 5.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/33.0.1750.154 Safari/537.36");
		httpGet.setHeader("Accept",
				"text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
		httpGet.setHeader("Accept-Language",
				"zh-CN,zh;q=0.8,en-us;q=0.8,en;q=0.6");
		httpGet.setHeader("Accept-Encoding", "gzip, deflate,sdch");
		httpGet.setHeader("Host", "www.bhi.com.cn");
		httpGet.setHeader("Connection", "keep-alive");
		httpGet.setHeader("Referer",
				"http://www.bhi.com.cn/projects/ItemList.aspx?a=1&p=t");
		httpGet.setHeader("Cache-Control", "max-age=0");
		httpGet.setHeader("Cookie", SessionId + " CheckCode=" + code + ";"
				+ " Hm_lvt_8d994d177d2158b74a6011c3839d1a20=" + starttTime
				+ "," + endTime + ";"
				+ " Hm_lpvt_8d994d177d2158b74a6011c3839d1a20=" + starttTime
				+ "; " + "BHI_BROWSE_STATICS_WIDTH=1920;"
				+ " BHI_BROWSE_STATICS_HEIGHT=1080;"
				+ "ASPSESSIONIDCSASQBRD=PDNFAMIBCDJMAAIDADIHCGNB;");
		// Hm_lvt_8d994d177d2158b74a6011c3839d1a20=1420790384,1421026609;
		// BHI_BROWSE_STATICS_WIDTH=1920;
		// BHI_BROWSE_STATICS_HEIGHT=1080;
		// Hm_lpvt_8d994d177d2158b74a6011c3839d1a20=1421028232;
		// ASP.NET_SessionId=zehuttmk1ydu5umveswtr4uv;
		// ASPSESSIONIDCSASQBRD=PDNFAMIBCDJMAAIDADIHCGNB; CheckCode=fc56
	}

	public static String cookiesToString() {
		StringBuffer cooke = new StringBuffer();
		List<Cookie> cookies = cookieStore.getCookies();
		if (cookies != null) {
			for (Cookie c : cookies) {
				cooke.append(c.getName() + "=" + c.getValue() + ";");
			}
		}
		return cooke.toString();
	}

	public static String makecookiesString() {
		return null;

	}

	public static void printCookies() {
		System.out.println("---查看当前Cookie---");
		List<Cookie> cookies = cookieStore.getCookies();
		if (cookies != null) {
			for (Cookie c : cookies) {
				System.out.println(c.getName() + ":" + c.getValue());

			}
			System.out.println("======================================");
		}
	}
}
