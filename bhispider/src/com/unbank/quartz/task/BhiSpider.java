package com.unbank.quartz.task;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;

import com.unbank.dao.WebBeanStore;
import com.unbank.fetch.HttpClientBuilder;
import com.unbank.paser.detail.BHIDetailPaser;
import com.unbank.paser.entity.WebBean;
import com.unbank.paser.nextpage.BHINextPagePaser;
import com.unbank.tools.SimpleTools;

public class BhiSpider {

	private static RequestConfig requestConfig = RequestConfig.custom()
			.setSocketTimeout(30000).setConnectTimeout(30000)
			.setStaleConnectionCheckEnabled(true)
			.setCircularRedirectsAllowed(true).setMaxRedirects(50).build();

	private static BasicCookieStore cookieStore = new BasicCookieStore();

	public void BHIConsole() {

		PoolingHttpClientConnectionManager poolingHttpClientConnectionManager = new PoolingHttpClientConnectionManager();
		HttpClientBuilder httpClientBuilder = new HttpClientBuilder(false,
				poolingHttpClientConnectionManager, cookieStore);
		CloseableHttpClient httpClient = httpClientBuilder.getHttpClient();
		Date startTime = SimpleTools.getMyDate(new Date(), -7);
		Date endTime = new Date();
		List<String> urls = getUrls(startTime, endTime);
		analyzerPaper(urls, httpClient, startTime, endTime);
	}

	private static void analyzerPaper(List<String> urls,
			CloseableHttpClient httpClient, Date startTime, Date endTime) {
		for (String url : urls) {
			System.out.println(url);
			try {
				String html = getHtml(httpClient, url, "utf-8");
				if (html == null || html.isEmpty()) {
					continue;
				}

				Map<String, Object> colums = new BHIDetailPaser()
						.analyzerPaper(html, url, startTime, endTime);

				if (colums == null || colums.size() == 0) {
					continue;
				}
				WebBean webBean = new WebBean();
				webBean.setTableName("grab_bhi");
				new WebBeanStore().saveWebBean(colums, webBean);

				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			} catch (Exception e) {
				e.printStackTrace();
				continue;
			}
		}

	}

	private static List<String> getUrls(Date startTime, Date endTime) {
		List<String> urls = new BHINextPagePaser().getProjectsUrls(startTime,
				endTime);
		return urls;
	}

	public static String getHtml(CloseableHttpClient httpClient, String url,
			String charset) {
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
		// String starttTime = String.valueOf(new Date().getTime()).substring(0,
		// 10);
		// String endTime = String.valueOf(new Date().getTime() +
		// 60).substring(0,
		// 10);
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
		// httpGet.setHeader("Cookie",
		// "ASP.NET_SessionId=vhnpp245uydt1g31u5dy4m45;"
		// + " ASPSESSIONIDCSBRTARC=CMFDEICDKLCFFKLPHOBPBJLD;"
		// + " CheckCode=cb2d; "
		// + "Hm_lvt_8d994d177d2158b74a6011c3839d1a20="
		// + starttTime + ";"
		// + "Hm_lpvt_8d994d177d2158b74a6011c3839d1a20=" + endTime
		// + ";" + " BHI_BROWSE_STATICS_WIDTH=1920; "
		// + "BHI_BROWSE_STATICS_HEIGHT=1080");
		httpGet.setHeader(
				"Cookie",
				"ASP.NET_SessionId=vja3oiuys3mwsw454laumo45; ASPSESSIONIDCSRSSACR=NOLFGICABKCIODOPBMJEDJII; CheckCode=ad6a; Hm_lvt_8d994d177d2158b74a6011c3839d1a20=1420791252,1421213953,1421643326,1421909156; Hm_lpvt_8d994d177d2158b74a6011c3839d1a20=1421909178; BHI_BROWSE_STATICS_WIDTH=1920; BHI_BROWSE_STATICS_HEIGHT=1080");
		// ASP.NET_SessionId=1wcrb4mllntctl45xq5igtim;
		// ASPSESSIONIDSSSRQSAD=FKDLBPJCIKHOLCCFOHLPFOHL; CheckCode=cfaf;
		// Hm_lvt_8d994d177d2158b74a6011c3839d1a20=1420791252,1421213953;
		// Hm_lpvt_8d994d177d2158b74a6011c3839d1a20=1421213986;
		// BHI_BROWSE_STATICS_WIDTH=1920; BHI_BROWSE_STATICS_HEIGHT=1080
	}
}
