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
import com.unbank.tools.MD5;

@Service
public class PjtxTablePaser extends BaseTablePaser {
	private String domain1 = "www.pjtx.net";

	public PjtxTablePaser() {
		TableFilterLocator.getInstance().register(domain1, this);
	}

	public void paseNodetailTable(Elements cloumElements, WebBean webBean) {
		cloumElements.remove(0);
		String detailurlXpath = webBean.getDetailurlXpath();
		for (Element element : cloumElements) {
			try {
				System.out.println(element);
				System.out.println(detailurlXpath);
				String detailurl = null;
				detailurl = element.select(detailurlXpath).first()
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
			} catch (Exception e) {
				e.printStackTrace();
				continue;
			}
		}

	}
}
