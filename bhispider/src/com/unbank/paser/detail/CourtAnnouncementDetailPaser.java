package com.unbank.paser.detail;

import java.util.HashMap;
import java.util.Map;

import org.jsoup.nodes.Element;
import org.springframework.stereotype.Service;

import com.unbank.paser.entity.WebBean;

@Service
public class CourtAnnouncementDetailPaser extends BaseDetailPaser {

	String domain = "rmfygg.court.gov.cn";

	public CourtAnnouncementDetailPaser() {
		DetailPaserFilterLocator.getInstance().register(domain, this);
	}

	public Map<String, Object> fillDetailMaps(Element document, WebBean webBean) {
		Map<String, Object> colums = new HashMap<String, Object>();

		String announcement_type = document.select(".ft1616").first().text();
		String content = document.select(".d22 > p:nth-child(1)").first()
				.text();
		String published_edition = document.select("p.ft1424:nth-child(2)")
				.first().text();

		String temp[] = published_edition.split("刊登版面：");
		try {
			published_edition = temp[1];
		} catch (Exception e) {
			published_edition = "";
		}
		String bulletin_time = document.select("p.ft1424:nth-child(4)").first()
				.text();
		bulletin_time = bulletin_time.split("上传日期：")[1];
		String parties = document.select("p.pt10").first().text();
		String posting_date = document.select("p.ft1424:nth-child(3)").first()
				.text();
		posting_date = posting_date.split("刊登日期：")[1];
		String notice_people = document.select(".d23 > p:nth-child(1)").first()
				.text();
		colums.put("announcement_type", announcement_type);

		colums.put("notice_people", notice_people);

		colums.put("parties", parties);

		colums.put("bulletin_time", bulletin_time);

		colums.put("content", content);

		colums.put("published_edition", published_edition);

		colums.put("posting_date", posting_date);
		return colums;
	}

}
