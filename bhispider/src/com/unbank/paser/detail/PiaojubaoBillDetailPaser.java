package com.unbank.paser.detail;

import java.util.HashMap;
import java.util.Map;

import org.jsoup.nodes.Element;
import org.springframework.stereotype.Service;

import com.unbank.paser.entity.WebBean;

@Service
public class PiaojubaoBillDetailPaser extends BaseDetailPaser {

	String domain = "www.pj.com";

	public PiaojubaoBillDetailPaser() {
		DetailPaserFilterLocator.getInstance().register(domain, this);
	}

	public Map<String, Object> fillDetailMaps(Element document, WebBean webBean) {
		Map<String, Object> colums = new HashMap<String, Object>();
		String pro_name = document.select(".licai_cp_left > ul > li > a")
				.first().text().trim();
		String pro_num = getNumbers(pro_name);
		String pro_bank = document.select(".licai_cp_right").text().trim();
		pro_bank = pro_bank.split("：")[1];
		// String dateTime[] = document
		// .select("div:nth-child(1) > h3:nth-child(1) > span:nth-child(2)")
		// .text().trim().split(" ");
		// String publish_date = dateTime[0];
		//
		// String publish_time = dateTime[1];
		String pro_rate = document
				.select("div.licai_cen_left_fot > ul:nth-child(1) > li:nth-child(5)")
				.text().trim();
		String pro_money = document.select(
				"div.licai_cen_left_fot > ul:nth-child(1) > li:nth-child(6)")
				.text();
		String pro_limit = document.select(
				"div.licai_cen_left_fot > ul:nth-child(1) > li:nth-child(8)")
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
