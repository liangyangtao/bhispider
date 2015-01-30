package com.unbank.paser.detail;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.unbank.tools.SimpleTools;

public class BHIDetailPaser {

	public String tableName = "grab_bhi";

	public Map<String, Object> analyzerPaper(String html, String baseUrl,
			Date startTime, Date endTime) {

		Document document = Jsoup.parse(html);
		Map<String, Object> columns = new HashMap<String, Object>();

		// 项目名称
		Element pro_name_element = document.select(
				"#mylogproject > h1:nth-child(1)").first();
		String pro_name_text = pro_name_element.text();
		columns.put("pro_name", pro_name_text);
		// 地区
		Element pro_area_element = document
				.select(".infoTable > tbody:nth-child(1) > tr:nth-child(2) > td:nth-child(2) > a:nth-child(1)")
				.first();
		String pro_area_text = pro_area_element.text();
		columns.put("pro_area", pro_area_text);
		// 发布时间
		Element pro_time_element = document
				.select(".infoTable > tbody:nth-child(1) > tr:nth-child(2) > td:nth-child(4)")
				.first();
		String pro_time_text = pro_time_element.text();

		// 2014-12-10

		if (SimpleTools.stringToDate(pro_time_text).after(endTime)) {
			return null;
		} else if (SimpleTools.stringToDate(pro_time_text).before(startTime)) {
			return null;
		}

		columns.put("pro_time", pro_time_text);
		// 项目性质
		Element pro_nature_element = document
				.select(".infoTable > tbody:nth-child(1) > tr:nth-child(3) > td:nth-child(2)")
				.first();
		String pro_nature_text = pro_nature_element.text();
		columns.put("pro_nature", pro_nature_text);
		// 企业性质
		Element firm_nature_element = document
				.select(".infoTable > tbody:nth-child(1) > tr:nth-child(3) > td:nth-child(4)")
				.first();
		String firm_nature_text = firm_nature_element.text();
		columns.put("firm_nature", firm_nature_text);
		// 行业
		Element pro_trade_element = document
				.select(".infoTable > tbody:nth-child(1) > tr:nth-child(4) > td:nth-child(2) > a:nth-child(1)")
				.first();
		String pro_trade_text = pro_trade_element.text();
		columns.put("pro_trade", pro_trade_text);
		// 投资总额
		Element pro_assets_element = document
				.select(".infoTable > tbody:nth-child(1) > tr:nth-child(4) > td:nth-child(4)")
				.first();
		String pro_assets_text = pro_assets_element.text();
		columns.put("pro_assets", pro_assets_text);
		// 进展阶段
		Element pro_stage_element = document
				.select(".infoTable > tbody:nth-child(1) > tr:nth-child(5) > td:nth-child(2) > a:nth-child(1)")
				.first();
		String pro_stage_text = pro_stage_element.text();
		columns.put("pro_stage", pro_stage_text);
		// 申报方式
		Element pro_way_element = document
				.select(".infoTable > tbody:nth-child(1) > tr:nth-child(5) > td:nth-child(4)")
				.first();
		String pro_way_text = pro_way_element.text();
		columns.put("pro_way", pro_way_text);
		// 审批机关
		Element pro_office_element = document
				.select(".infoTable > tbody:nth-child(1) > tr:nth-child(6) > td:nth-child(2)")
				.first();
		String pro_office_text = pro_office_element.text();
		columns.put("pro_office", pro_office_text);
		// 建设周期
		Element pro_cycle_element = document
				.select(".infoTable > tbody:nth-child(1) > tr:nth-child(6) > td:nth-child(4)")
				.first();
		String pro_cycle_text = pro_cycle_element.text();
		columns.put("pro_cycle", pro_cycle_text);
		// 资金到位
		Element pro_fund_element = document
				.select(".infoTable > tbody:nth-child(1) > tr:nth-child(7) > td:nth-child(2)")
				.first();
		String pro_fund_text = pro_fund_element.text();
		columns.put("pro_fund", pro_fund_text);
		// 设备来源
		Element equipment_source_element = document
				.select(".infoTable > tbody:nth-child(1) > tr:nth-child(7) > td:nth-child(4)")
				.first();
		String equipment_source_text = equipment_source_element.text();
		columns.put("equipment_source", equipment_source_text);
		// 主管单位
		Element governing_unit_element = document
				.select(".infoTable > tbody:nth-child(1) > tr:nth-child(8) > td:nth-child(2)")
				.first();
		String governing_unit_text = governing_unit_element.text();
		columns.put("governing_unit", governing_unit_text);
		// 所在地
		Element address_element = document
				.select(".infoTable > tbody:nth-child(1) > tr:nth-child(9) > td:nth-child(2)")
				.first();
		String address_text = address_element.text();
		columns.put("address", address_text);
		// 主要设备
		Element pro_facility_element = document
				.select(".infoTable > tbody:nth-child(1) > tr:nth-child(10) > td:nth-child(2)")
				.first();
		String pro_facility_text = pro_facility_element.text();
		columns.put("pro_facility", pro_facility_text);
		// 建设内容
		Element pro_content_element = document
				.select(".infoTable > tbody:nth-child(1) > tr:nth-child(11) > td:nth-child(2)")
				.first();
		String pro_content_text = pro_content_element.text();
		columns.put("pro_content", pro_content_text);
		// 单位名称
		Element pro_department_element = document
				.select(".infoTable > tbody:nth-child(1) > tr:nth-child(13) > td:nth-child(2) > span:nth-child(1) > a:nth-child(1)")
				.first();
		String pro_department_text = pro_department_element.text();
		columns.put("pro_department", pro_department_text);
		// 姓名
		Element pro_people_element = document
				.select(".infoTable > tbody:nth-child(1) > tr:nth-child(14) > td:nth-child(2)")
				.first();
		String pro_people_text = pro_people_element.text();
		columns.put("pro_people", pro_people_text);
		// 电话
		Element pro_tel_element = document
				.select(".infoTable > tbody:nth-child(1) > tr:nth-child(15) > td:nth-child(2)")
				.first();
		String pro_tel_text = pro_tel_element.text();
		columns.put("pro_tel", pro_tel_text);
		// 传真
		Element fax_element = document
				.select(".infoTable > tbody:nth-child(1) > tr:nth-child(15) > td:nth-child(4)")
				.first();
		String fax_text = fax_element.text();
		columns.put("fax", fax_text);

		// 以下数据由于界面结构不一样导致的Xpath 结构不一样
		// 邮编
		Element postcode_element = null;
		Element detail_address_element = null;
		Element pro_intro_element = null;
		Elements elements = document
				.select(".infoTable > tbody:nth-child(1) > tr");
		for (Element element : elements) {

			Elements bElement = element.select("b");
			if (bElement.text().trim().equals("邮 编")) {
				postcode_element = element.select("td").get(1);
			}

			if (bElement.text().trim().equals("详细地址")) {
				detail_address_element = element.select("td").get(1);
			}
		}
		pro_intro_element = elements.last().select("td").first();
		// 邮编
		String postcode_text = postcode_element.text();
		columns.put("postcode", postcode_text);
		// 详细地址
		String detail_address_text = detail_address_element.text();
		columns.put("detail_address", detail_address_text);
		// 项目简介
		String pro_intro_text = pro_intro_element.text();
		columns.put("pro_intro", pro_intro_text);
		return columns;

	}
}
