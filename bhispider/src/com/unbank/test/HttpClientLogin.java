package com.unbank.test;

import java.awt.FlowLayout;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

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
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import com.unbank.fetch.HttpClientBuilder;

public class HttpClientLogin {
	private static RequestConfig requestConfig = RequestConfig.custom()
			.setSocketTimeout(30000).setConnectTimeout(30000)
			.setStaleConnectionCheckEnabled(true)
			.setCircularRedirectsAllowed(true).setMaxRedirects(50).build();
	private static BasicCookieStore cookieStore = new BasicCookieStore();
	static CloseableHttpClient httpClient;

	public static void main(String[] args) {
		PoolingHttpClientConnectionManager poolingHttpClientConnectionManager = new PoolingHttpClientConnectionManager();
		HttpClientBuilder httpClientBuilder = new HttpClientBuilder(false,
				poolingHttpClientConnectionManager, cookieStore);
		httpClient = httpClientBuilder.getHttpClient();
		login(httpClient, "unbank", "chengxin163");
	}

	private static void login(CloseableHttpClient httpClient, String username,
			String password) {
		try {
			String loginUrl = "http://www.bhi.com.cn/Login/login.aspx?prev=";
			String html = getHtml(loginUrl, "utf-8");
			String validcode = getValidCode();
			String checkURL = "http://www.bhi.com.cn/Public/IsExistValid.aspx?_=1420340888051";
			html = getHtml(checkURL, "utf-8");
			Map<String, String> params = new HashMap<String, String>();
			params.put("name", username);
			params.put("pwd", password);
			params.put("code", validcode);
			html = postHtml(loginUrl, loginUrl, params, "utf-8");
			printCookies();
			// System.out.println(html);// 302 提交表单
			// get 提交
			html = getHtml(loginUrl, "utf-8");
			printCookies();
			String massageUrl2 = "http://www.bhi.com.cn/Login/showmsg.aspx?prev=";
			html = getHtml(massageUrl2, "utf-8");
			System.out.println(html);
			printCookies();
			// String loadUrl = "http://www.bhi.com.cn/Public/LoadAd.ashx";
			// Map<String, String> params2 = new HashMap<String, String>();
			// params2.put("url", "default");
			// html = postHtml(loadUrl, massageUrl2, params2, "utf-8");
			// printCookies();
			// String testUrl =
			// "http://www.bhi.com.cn/Projects/ItemShow.aspx?id=10903318";
			// html = getHtml(testUrl, "utf-8");
			// String testUrl2 =
			// "http://www.bhi.com.cn/Projects/ShowDetails.aspx?id=10903318";
			// html = getHtml(testUrl2, "utf-8");
			System.out.println(html);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static String postHtml(String url, String loginurl,
			Map<String, String> params, String charset) {
		HttpClientContext context = HttpClientContext.create();
		context.setCookieStore(cookieStore);
		String useCharset = charset;
		try {
			HttpPost httpPost = new HttpPost(loginurl);
			fillPostHeader(url, httpPost);
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

	private static void fillPostHeader(String url, HttpPost httpGet) {
		httpGet.setHeader(
				"User-Agent",
				"Mozilla/5.0 (Windows NT 5.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/33.0.1750.154 Safari/537.36");
		httpGet.setHeader("Accept",
				"text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
		httpGet.setHeader("Accept-Language", "zh-CN,zh;q=0.8,en;q=0.6");
		httpGet.setHeader("Accept-Encoding", "gzip, deflate，sdch");
		httpGet.setHeader("Connection", "keep-alive");
		httpGet.setHeader("Referer", url);
		httpGet.setHeader("Cache-Control", "max-age=0");
		httpGet.setHeader("Cookie", cookiesToString());
		httpGet.setHeader("Host", "www.bhi.com.cn");
		httpGet.setHeader("X-Requested-With", "XMLHttpRequest");

	}

	private static String getValidCode() {
		String validCode = null;
		try {
			File file = new File(HttpClientLogin.class.getClassLoader()
					.getResource("").toURI().getPath()
					+ "/temp/temp.jpg");
			file.deleteOnExit();
			String validUrl = "http://www.bhi.com.cn/Public/Isvalid.ashx?num="
					+ new Date().getTime();
			getImage(validUrl);

			JFrame jFrame = new JFrame();
			jFrame.setLayout(new FlowLayout());
			ImageIcon icon = new ImageIcon(HttpClientLogin.class
					.getClassLoader().getResource("").toURI().getPath()
					+ "/temp/temp.jpg");
			jFrame.add(new JLabel(icon));
			jFrame.setBounds(100, 100, 100, 100);
			jFrame.setVisible(true);
			jFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			validCode = JOptionPane.showInputDialog(null, "请输入红色的验证码");
			System.out.println(validCode);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return validCode;
	}

	public static void getImage(String url) {
		HttpClientContext context = HttpClientContext.create();
		context.setCookieStore(cookieStore);
		HttpGet httpGet = new HttpGet(url);
		fillHeader(url, httpGet);
		httpGet.setConfig(requestConfig);
		try {
			CloseableHttpResponse response = httpClient.execute(httpGet,
					context);
			try {
				HttpEntity entity = response.getEntity();
				File file = new File(HttpClientLogin.class.getClassLoader()
						.getResource("").toURI().getPath()
						+ "/temp");
				if (!file.exists()) {
					file.mkdirs();
				}
				FileOutputStream fileOutputStream = new FileOutputStream(file
						+ "/temp.jpg");
				InputStream inputStream = entity.getContent();
				if (!file.exists()) {
					file.createNewFile();
				}
				byte[] bytes = new byte[1024];
				int length = 0;
				while ((length = inputStream.read(bytes)) > 0) {
					fileOutputStream.write(bytes, 0, length);
				}
				inputStream.close();
				fileOutputStream.close();

			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				response.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
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

	public static String makecookiesString(){
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
