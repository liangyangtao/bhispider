package com.unbank.paser.table;

import java.util.Date;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.unbank.dao.WebBeanStore;
import com.unbank.fetch.Fetcher;
import com.unbank.filter.url.URLBaseFilter;
import com.unbank.filter.url.URLFilter;
import com.unbank.paser.detail.BaseDetailPaser;
import com.unbank.paser.detail.DetailPaserFilterLocator;
import com.unbank.paser.entity.WebBean;
import com.unbank.tools.MD5;

public class BaseTablePaser {
	private static Log logger = LogFactory.getLog(BaseTablePaser.class);

	public Elements getTableElement(Document document, WebBean webBean) {
		String bodyXPath = webBean.getBodyXPath();
		Element tableBodyelement = document.select(bodyXPath).first();
		String cloumXpath = webBean.getCloumXpath();
		Elements cloumElements = tableBodyelement.select(cloumXpath);
		return cloumElements;
	}

	protected Map<String, Object> fillTabelColums(Element document,
			WebBean webBean) {
		DetailPaserFilterLocator detailPaserFilterLocator = DetailPaserFilterLocator
				.getInstance();
		BaseDetailPaser baseDetailPaser = detailPaserFilterLocator
				.getFilter(webBean.getWebUrl());
		Map<String, Object> colums = baseDetailPaser.fillDetailMaps(document,
				webBean);
		return colums;
	}

	public Document htmlToDocument(Fetcher fetcher, String url) {
		String bodyhtml = fetcher.get(url);
		if (bodyhtml == null || bodyhtml.isEmpty()) {
			return null;
		}
		Document document = Jsoup.parse(bodyhtml, url);
		return document;
	}

	public static String getStringByRegList(String html, String regex,
			int number) {
		Pattern patten = Pattern.compile(regex);
		Matcher mat = patten.matcher(html);
		StringBuffer sb = new StringBuffer();
		while (mat.find()) {
			if (number == -1) {
				sb.append(mat.group());
				continue;
			}
			if (number > 0) {
				sb.append(mat.group());
				number--;
			} else {
				break;
			}
		}

		return sb.toString();
	}

	public void paseNodetailTable(Elements cloumElements, WebBean webBean) {
		String detailurlXpath = webBean.getDetailurlXpath();
		for (Element element : cloumElements) {
			try {
				System.out.println(element);
				System.out.println(detailurlXpath);
				String detailurl = MD5.GetMD5Code(element
						.select(detailurlXpath).first().text().trim());
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

	public void paseDetailTable(Elements cloumElements, Fetcher fetcher,
			WebBean webBean) {
		String detailurlXpath = webBean.getDetailurlXpath();
		for (Element element : cloumElements) {
			try {
				System.out.println(element);
				System.out.println(detailurlXpath);

				Map<String, Object> colums = null;
				String detailurl = getDetailUrl(detailurlXpath, element);
				URLFilter urlFilter = new URLBaseFilter();
				if (urlFilter.checkNewsURL(detailurl)) {
					Document detail_document = htmlToDocument(fetcher,
							detailurl);
					if (detail_document == null) {
						continue;
					}
					colums = fillTabelColums(detail_document, webBean);
					if (colums.size() == 0) {
						continue;
					}

					colums.put("detailurl", detailurl);
					colums.put("TIME", new Date());
					new WebBeanStore().saveWebBean(colums, webBean);
				}

			} catch (Exception e) {
				e.printStackTrace();
				continue;
			}
		}
	}

	public String getDetailUrl(String detailurlXpath, Element element) {
		Element aElement = element.select(detailurlXpath).first();
		String detailurl = aElement.absUrl("href");
		return detailurl;
	}

	public void paseTable(String url, WebBean webBean, Fetcher fetcher) {
		Document document = htmlToDocument(fetcher, url);
		if (document == null) {
			return;
		}
		Elements cloumElements = getTableElement(document, webBean);
		if (webBean.getCrawlType().equals("TABLE")) {
			paseNodetailTable(cloumElements, webBean);
		} else {
			paseDetailTable(cloumElements, fetcher, webBean);
		}

	}
}
