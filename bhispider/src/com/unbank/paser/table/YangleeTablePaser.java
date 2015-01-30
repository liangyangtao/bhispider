package com.unbank.paser.table;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Service;

import com.unbank.fetch.Fetcher;

@Service
public class YangleeTablePaser extends BaseTablePaser {
	private String domain1 = "www.yanglee.com";

	public YangleeTablePaser() {
		TableFilterLocator.getInstance().register(domain1, this);
	}

	public Document htmlToDocument(Fetcher fetcher, String url) {
		String temp = null;
		if (url.contains("http://www.yanglee.com/product/product_Lists.aspx")) {
			String a = url
					.split("http://www.yanglee.com/product/product_Lists.aspx\\?ptype=")[1];
			temp = "http://www.yanglee.com/ajax/ProductSearch.ashx?_=1420679917746&mode=Search&perpage=60&page="
					+ a
					+ "&ProStr=NodeCode%253D%2527105015016007%2527%2520and%2520FlowState%253D99%2520and%2520IsDel%253D0%2520and%2520ProductLevel%253C%253E%2527VIP%2527%2520&strOrder=released%2520desc&ProductState=&jigou=&qixian=&shouyi=&InvestField=&ApplyWay=";
		} else {
			temp = url;
		}
		String bodyhtml = fetcher.get(temp, "utf-8");
		if (bodyhtml == null || bodyhtml.isEmpty()) {
			return null;
		}
		Document document = Jsoup.parse(bodyhtml, url);
		return document;
	}
}
