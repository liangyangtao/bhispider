package com.unbank.paser.table;

import org.jsoup.nodes.Element;
import org.springframework.stereotype.Service;

@Service
public class CourtRmfyggTablePaser extends BaseTablePaser {
	private String domain1 = "rmfygg.court.gov.cn";

	public CourtRmfyggTablePaser() {
		TableFilterLocator.getInstance().register(domain1, this);
	}

	public String getDetailUrl(String detailurlXpath, Element element) {
		Element aElement = element.select(detailurlXpath).first();
		String detailurl = aElement.absUrl("href");
		String temp[] = detailurl.split("/");
		String urlend = temp[temp.length - 1];
		String preurl = detailurl.split(urlend)[0];
		return preurl + "block" + urlend;
	}
}
