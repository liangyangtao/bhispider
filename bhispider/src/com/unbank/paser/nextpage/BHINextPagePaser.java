package com.unbank.paser.nextpage;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.unbank.fetch.JsoupNetFetcher;
import com.unbank.tools.SimpleTools;

public class BHINextPagePaser {

	public List<String> getProjectsUrls(Date startTime, Date endTime) {
		int i = 1;
		List<String> urls = new ArrayList<String>();
		while (true) {
			try {
				String url = "http://www.bhi.com.cn/solr/SolrProject.ashx?solr_core=0&solr_rows=30&solr_rsort=0&solr_keywords=%E8%AF%B7%E8%BE%93%E5%85%A5%E5%85%B3%E9%94%AE%E8%AF%8D&solr_area=&solr_industry=&solr_date=0&solr_cbcolumns=%E6%8B%9F&solr_currentPage="
						+ i + "&solr_shenbao=&_=1413787680866";
				i++;
				Document document = new JsoupNetFetcher().fetchText(url);
				Elements elements = document.select("a");
				Element dateElement = elements.get(0);
				String timeString = StringUtils.substringBetween(
						dateElement.toString(), "adddate&quot;:&quot;",
						"&quot;,&quot;area");
				if (SimpleTools.stringToDate(timeString).after(endTime)) {
					continue;
				} else if (SimpleTools.stringToDate(timeString).before(
						startTime)) {
					break;
				}
				for (Element element : elements) {
					String tempURL = "http://www.bhi.com.cn"
							+ element.attr("href");
					String temp = tempURL.replace("\\", "");
					urls.add(temp);
				}
			} catch (Exception e) {
				e.printStackTrace();
				continue;
			}

		}
		return urls;
	}

}
