package com.unbank.quartz.task;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;

import com.unbank.fetch.Fetcher;
import com.unbank.fetch.HttpClientBuilder;
import com.unbank.filter.url.SimpleBloomFilter;
import com.unbank.paser.entity.WebBean;
import com.unbank.paser.other.WebBeanPaser;
import com.unbank.tools.ConfigReader;

public class SpiderConsole {

	private static Log logger = LogFactory.getLog(SpiderConsole.class);
	public static SimpleBloomFilter simpleBloomFilter = new SimpleBloomFilter();
	public static PoolingHttpClientConnectionManager poolingHttpClientConnectionManager = new PoolingHttpClientConnectionManager();
	public static BasicCookieStore cookieStore = new BasicCookieStore();

	public void inittask() {

		HttpClientBuilder httpClientBuilder = new HttpClientBuilder(false,
				poolingHttpClientConnectionManager, cookieStore);
		CloseableHttpClient httpClient = httpClientBuilder.getHttpClient();
		Fetcher fetcher = new Fetcher(cookieStore, httpClient);
		List<WebBean> beans = new ArrayList<WebBean>();
		beans = ConfigReader.getWebBeans();
		for (WebBean webBean : beans) {
			try {
				new WebBeanPaser().paseWebBean(webBean, fetcher);
			} catch (Exception e) {
				e.printStackTrace();
				continue;
			}

		}
	}

}
