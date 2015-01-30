package com.unbank.paser.detail;

import java.util.HashMap;
import java.util.Map;

import org.jsoup.nodes.Element;
import org.springframework.stereotype.Service;

import com.unbank.paser.entity.WebBean;

@Service
public class Jr138BillDetailPaser extends BaseDetailPaser {

	String domain = "www.138jr.com";

	public Jr138BillDetailPaser() {
		DetailPaserFilterLocator.getInstance().register(domain, this);
	}

	public Map<String, Object> fillDetailMaps(Element document, WebBean webBean) {
		Map<String, Object> colums = new HashMap<String, Object>();
		Element typeElement = document.select(".bank_3a").first();
		String typeText = typeElement.text().trim();
		if (typeText.contains("买入")) {
			typeText = "买入";
			// 出票银行
			Element bankElement = document
					.select("div.zhitie_1:nth-child(1) > ul:nth-child(1) > li:nth-child(2)")
					.first();
			String bankText = bankElement.text().trim();
			// 票据期限
			Element deadlineElement = document
					.select("div.zhitie_1:nth-child(1) > ul:nth-child(1) > li:nth-child(4)")
					.first();
			String deadlineText = deadlineElement.text().trim();
			// 发布日期
			Element publishdateElement = document
					.select("div.zhitie_1:nth-child(1) > ul:nth-child(2) > li:nth-child(2)")
					.first();
			String publishdateText = publishdateElement.text().trim();
			// 金额
			Element priceElement = document
					.select("div.zhitie_1:nth-child(1) > ul:nth-child(3) > li:nth-child(2)")
					.first();
			String priceText = priceElement.text().trim().split(" ")[0];
			// 利率报价
			Element rateElement = document
					.select("div.zhitie_1:nth-child(3) > ul:nth-child(1) > li:nth-child(2)")
					.first();
			String rateText = rateElement.text().trim().split("月利率：")[1];

			// 持票人所在地
			Element placeElement = document
					.select("div.zhitie_1:nth-child(3) > ul:nth-child(1) > li:nth-child(4)")
					.first();
			String placeText = placeElement.text().trim();
			// 查询方式
			Element lookElement = document
					.select("div.zhitie_1:nth-child(3) > ul:nth-child(2) > li:nth-child(2)")
					.first();
			String lookText = lookElement.text().trim();
			colums.put("pro_bank", bankText);
			colums.put("pro_date", publishdateText);
			colums.put("pro_rate", rateText.replace("‰  ", ""));
			colums.put("pro_money", priceText.replace("万元", ""));
			colums.put("pro_limit", deadlineText.replace("天", ""));
			colums.put("pro_type", typeText);
			colums.put("look_type", lookText);
			colums.put("pro_place", placeText);

		} else {
			typeText = "卖出";
			// 出票银行
			Element bankElement = document.select(
					".zhitie_2b > ul:nth-child(3) > li:nth-child(1)").first();
			String bankText = bankElement.text().trim();
			// 发布日期
			Element publishdateElement = document.select(".zhitie_3c").first();
			String publishdateText = publishdateElement.text().trim();
			// 金额
			Element priceElement = document.select(".zhitie_3ba").first();
			String priceText = priceElement.text().trim().split(" ")[0];
			// 利率报价
			Element rateElement = document.select(
					".zhitie_1 > ul:nth-child(1) > li:nth-child(2)").first();
			String rateText = rateElement.text().trim().split("月利率：")[1];
			// 持票人所在地
			Element placeElement = document.select(
					".zhitie_1 > ul:nth-child(1) > li:nth-child(4)").first();
			String placeText = placeElement.text().trim();
			// 查询方式
			Element lookElement = document.select(
					".zhitie_1 > ul:nth-child(2) > li:nth-child(2)").first();
			String lookText = lookElement.text().trim();
			colums.put("pro_bank", bankText);
			colums.put("pro_date", publishdateText);
			colums.put("pro_rate", rateText.replace("‰  ", ""));
			colums.put("pro_money", priceText.replace("万元", ""));
			colums.put("pro_type", typeText);
			colums.put("look_type", lookText);
			colums.put("pro_place", placeText);
		}
		return colums;
	}
}
