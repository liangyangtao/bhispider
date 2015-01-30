package com.unbank.tools;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.unbank.paser.entity.WebBean;

public class ConfigReader {

	public static List<WebBean> getWebBeans() {
		Document doc = null;
		try {
			File file = new File(ConfigReader.class.getClassLoader()
					.getResource("").toURI().getPath()
					+ "webbean_config.xml");
			doc = Jsoup.parse(file, "utf-8");
		} catch (Exception e) {
			e.printStackTrace();
		}
		List<WebBean> beans = new ArrayList<WebBean>();
		Elements bs = doc.select("webBean");
		for (Element e : bs) {
			String webUrl = e.select("webUrl").text().trim();
			String crawlType = e.select("crawlType").text().trim();
			String bodyXPath = e.select("bodyXPath").text().trim();
			String cloumXpath = e.select("cloumXpath").text().trim();
			String detailurlXpath = e.select("detailurlXpath").text().trim();
			String nextPageXpath = e.select("nextPageXpath").text().trim();
			String tableName = e.select("tableName").text().trim();
			String compareAtrr = e.select("compareAtrr").text().trim();
			Map<String, String> detailAtrr = new HashMap<String, String>();
			Element detailXpathElement = e.select("detailXpath").first();
			Elements elements = detailXpathElement.children();
			for (Element element : elements) {
				detailAtrr.put(element.tagName().trim(), element.text().trim());
			}
			WebBean b = new WebBean(webUrl, crawlType, bodyXPath, cloumXpath,
					detailurlXpath, nextPageXpath, detailAtrr, tableName,
					compareAtrr);
			beans.add(b);
		}
		return beans;
	}
}
