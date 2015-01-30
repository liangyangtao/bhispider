package com.unbank.paser.detail;

import java.util.HashMap;
import java.util.Map;

import org.jsoup.nodes.Element;
import org.springframework.stereotype.Service;

import com.unbank.paser.entity.WebBean;

@Service
public class JingyinmaoBillDetailPaser extends BaseDetailPaser {

	String domain = "www.jinyinmao.com.cn";

	public JingyinmaoBillDetailPaser() {
		DetailPaserFilterLocator.getInstance().register(domain, this);
	}

	public Map<String, Object> fillDetailMaps(Element document, WebBean webBean) {
		Map<String, Object> colums = new HashMap<String, Object>();
		String pro_name = document.select("h1").first().text().trim();
		String pro_num = getNumbers(pro_name);
		String pro_bank = document
				.select("div.repay-blank > span")
				.text().trim();
		// String dateTime[] = document
		// .select("div:nth-child(1) > h3:nth-child(1) > span:nth-child(2)")
		// .text().trim().split(" ");
		// String publish_date = dateTime[0];
		//
		// String publish_time = dateTime[1];
		String pro_rate = document
				.select("span.expected_yield")
				.text().trim();
		String pro_money = document
				.select(".info > li:nth-child(3)")
				.text();
		String pro_limit = document
				.select(".info > li:nth-child(2)")
				.text();
		colums.put("pro_name", pro_name);
		colums.put("pro_num", pro_num);
		colums.put("pro_bank", pro_bank);
		// colums.put("pro_date", pro_date);
		// colums.put("pro_time", pro_time);
		colums.put("pro_rate", pro_rate);
		colums.put("pro_money", pro_money);
		colums.put("pro_limit", pro_limit);
		return colums;
	}
}
