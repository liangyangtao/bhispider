package com.unbank.paser.detail;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONArray;

import org.jsoup.nodes.Element;
import org.springframework.stereotype.Service;

import com.unbank.dao.WebBeanStore;
import com.unbank.filter.url.URLBaseFilter;
import com.unbank.filter.url.URLFilter;
import com.unbank.paser.entity.WebBean;
import com.unbank.tools.DESTools;

@Service
public class ChinaBillDetailPaser extends BaseDetailPaser {

	String domain = "www.chinabill.cn";

	public ChinaBillDetailPaser() {
		DetailPaserFilterLocator.getInstance().register(domain, this);
	}

	public Map<String, Object> fillDetailMaps(Element document, WebBean webBean) {

		DESTools desTools = new DESTools("1234567");
		Element body = document.select("body").first();
		String bodyText = body.text();
		bodyText = bodyText.replaceAll(" ", "\n");
		String b = desTools.decryptStr(bodyText);
		JSONArray jsonArray = JSONArray.fromObject(b);
		for (Object object : jsonArray) {
			try {
				Map<String, Object> colums = new HashMap<String, Object>();
				Map<String, Object> map = (Map) object;
				URLFilter urlFilter = new URLBaseFilter();
				if (urlFilter.checkNewsURL((String) map.get("t_id"))) {
					colums.put("pro_money", map.get("face_amount"));
					colums.put("pro_rate", map.get("rate"));
					colums.put("issue_date", map.get("issue_date"));
					colums.put("drawer_date", map.get("drawer_date"));
					colums.put("issue_place", map.get("issue_place_city"));
					colums.put("pro_limit", map.get("term_days"));
					colums.put("pro_type", map.get("t_direction_name"));
					colums.put("pro_bank", map.get("bank_type_name"));
					colums.put("pro_type_name", map.get("t_type_name"));
					colums.put("trade_site", map.get("trade_site"));
					colums.put("detailurl", map.get("t_id"));
					colums.put("TIME", new Date());
					new WebBeanStore().saveWebBean(colums, webBean);
				}
			} catch (Exception e) {
				e.printStackTrace();
				continue;
			}

		}
		return null;
	}
}
