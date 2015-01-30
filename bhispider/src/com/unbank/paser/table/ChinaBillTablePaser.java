package com.unbank.paser.table;

import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import com.unbank.fetch.Fetcher;
import com.unbank.paser.entity.WebBean;
import com.unbank.tools.DESTools;

@Service
public class ChinaBillTablePaser extends BaseTablePaser {
	private String domain1 = "www.chinabill.cn";

	public ChinaBillTablePaser() {
		TableFilterLocator.getInstance().register(domain1, this);
	}

	public Elements getTableElement(Document document, WebBean webBean) {
		String bodyXPath = webBean.getBodyXPath();
		Elements tableBodyelement = document.select(bodyXPath);
		return tableBodyelement;
	}

	public void paseNodetailTable(Elements cloumElements, WebBean webBean) {
		try {
			fillTabelColums(cloumElements.first(), webBean);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public Document htmlToDocument(Fetcher fetcher, String url) {

		String temp = "http://www.chinabill.cn/cbt/CbtController/searchTradeInfoList";
		Map<String, String> params = new HashMap<String, String>();
		String a = url.split("http://www.chinabill.cn/")[1];
		params.put("pageNum", a);
		params.put("type", "1");
		params.put("startTime", "");
		params.put("endTime", "");
		params.put("startAmount", "");
		params.put("endAmount", "");
		Map<String, String> headers = new HashMap<String, String>();
		headers.put("Accept", "application/json, text/javascript, */*; q=0.01");
		headers.put("Accept-Encoding", "gzip,deflate,sdch");
		headers.put("Accept-Language", "zh-CN,zh;q=0.8,en;q=0.6");
		headers.put("Connection", "keep-alive");
		headers.put("Content-Type",
				"application/x-www-form-urlencoded; charset=UTF-8");
		headers.put("Host", "www.chinabill.cn");
		headers.put("Origin", "www.chinabill.cn");
		headers.put("Referer", "http://www.chinabill.cn/jsp/tradeList.jsp");
		headers.put(
				"User-Agent",
				"Mozilla/5.0 (Windows NT 5.1) AppleWebKit/537.36 (KHTML,like Gecko) Chrome/33.0.1750.154 Safari/537.36");
		headers.put("X-Requested-With", "XMLHttpRequest");
		String charset = "utf-8";
		String bodyhtml = fetcher.post(temp, params, headers, charset);
		JSONObject json = JSONObject.fromObject(bodyhtml);
		Map<String, Object> map = (Map) json;
		JSONArray jsonArray = (JSONArray) map.get("rows");
		String source = jsonArray.toString();
		DESTools desTools = new DESTools("1234567");
		String str2 = desTools.encryptStr(source);
		Document document = Jsoup.parse(str2);
		return document;
	}
}
