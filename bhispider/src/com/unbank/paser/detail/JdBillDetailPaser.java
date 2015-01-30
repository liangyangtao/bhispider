package com.unbank.paser.detail;

import java.util.HashMap;
import java.util.Map;

import org.jsoup.nodes.Element;
import org.springframework.stereotype.Service;

import com.unbank.paser.entity.WebBean;

@Service
public class JdBillDetailPaser extends BaseDetailPaser {

	String domain = "bill.jr.jd.com";

	public JdBillDetailPaser() {
		DetailPaserFilterLocator.getInstance().register(domain, this);
	}

	public Map<String, Object> fillDetailMaps(Element document, WebBean webBean) {
		Map<String, Object> colums = new HashMap<String, Object>();
		String pro_name = document.select("h3").first().textNodes().get(0)
				.text().trim();
		String pro_num = getNumbers(pro_name);
		String bill_bank = document.select("h3 > span:nth-child(1)").text()
				.trim();
//		String dateTime[] = document.select("h3 > span:nth-child(2)").text()
//				.trim().split(" ");
//		String publish_date = dateTime[0];
//		String publish_time = dateTime[1];
		String bill_rate = document
				.select(".bill-item > span:nth-child(1) > b:nth-child(1)")
				.text().trim();
		String bill_money = document.select(
				".bill-item > span:nth-child(2) > b:nth-child(1)").text();
		String bill_day = document.select(".bill-item > span:nth-child(3) > b:nth-child(1)").text();
		String checkText = document.select(".bill-btn-wrap > a:nth-child(1)")
				.first().text();

		if (!checkText.equals("已抢光")) {
			return colums;
		}

		colums.put("pro_name", pro_name);
		colums.put("pro_num", pro_num);
		colums.put("bill_bank", bill_bank);
//		colums.put("publish_date", publish_date);
//		colums.put("publish_time", publish_time);
		colums.put("bill_rate", bill_rate);
		colums.put("bill_money", bill_money);
		colums.put("bill_day", bill_day);
		return colums;
	}

}
