package com.unbank.paser.table;

import java.util.Date;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import com.unbank.dao.WebBeanStore;
import com.unbank.fetch.Fetcher;
import com.unbank.filter.url.URLBaseFilter;
import com.unbank.filter.url.URLFilter;
import com.unbank.paser.entity.WebBean;

@Service
public class PiaojuyiTablePaser extends BaseTablePaser {
	private String domain1 = "www.piaojuyi.com";

	public PiaojuyiTablePaser() {
		TableFilterLocator.getInstance().register(domain1, this);
	}

	public void paseNodetailTable(Elements cloumElements, WebBean webBean) {
		String detailurlXpath = webBean.getDetailurlXpath();
		for (Element element : cloumElements) {
			System.out.println(element);
			System.out.println(detailurlXpath);
			String detailurl = element.select(detailurlXpath).first()
					.absUrl("href").trim();
			URLFilter urlFilter = new URLBaseFilter();
			if (urlFilter.checkNewsURL(detailurl)) {
				try {
					Map<String, Object> colums = null;
					colums = fillTabelColums(element, webBean);
					if (colums.size() == 0) {
						continue;
					}
					colums.put("detailurl", detailurl);
					colums.put("TIME", new Date());
					new WebBeanStore().saveWebBean(colums, webBean);
				} catch (Exception e) {
					e.printStackTrace();
					continue;
				}
			}
		}

	}

	public Document htmlToDocument(Fetcher fetcher, String url) {
		System.out.println(url);
		String bodyhtml = fetcher.get(url, "gbk");
		if (bodyhtml == null || bodyhtml.isEmpty()) {
			return null;
		}
		Document document = Jsoup.parse(bodyhtml, url);
		return document;
	}
}
