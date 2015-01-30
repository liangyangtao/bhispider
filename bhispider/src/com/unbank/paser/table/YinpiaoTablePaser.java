package com.unbank.paser.table;

import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONObject;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Service;

import com.unbank.fetch.Fetcher;

@Service
public class YinpiaoTablePaser extends BaseTablePaser {
	private String domain1 = "www.yinpiao.com";

	public YinpiaoTablePaser() {
		TableFilterLocator.getInstance().register(domain1, this);
	}

	public Document htmlToDocument(Fetcher fetcher, String url) {

		String temp = "http://www.yinpiao.com/JSON/GetJsonBill";
		Map<String, String> params = new HashMap<String, String>();
		String a = url.split("http://www.yinpiao.com/bill/")[1];
		params.put("pn", a);
		params.put("o", "10");
		params.put("w", "");
		params.put("i", "0");
		Map<String, String> headers = new HashMap<String, String>();
		headers.put("Accept", "application/json, text/javascript, */*; q=0.01");
		headers.put("Accept-Encoding", "gzip,deflate,sdch");
		headers.put("Accept-Language", "zh-CN,zh;q=0.8,en;q=0.6");
		headers.put("Connection", "keep-alive");
		headers.put("Content-Type",
				"application/x-www-form-urlencoded; charset=UTF-8");
		headers.put(
				"Cookie",
				"from=; CNZZDATA1253101689=747322261-1420598066-%7C1420598066; _jzqa=1.4429922628883153400.1420598231.1420598231.1420598231.1;_jzqc=1; _jzqckmp=1; _jzqb=1.1.10.1420598231.1;_qzja=1.1766931996.1420598231490.1420598231490.1420598231490.1420598231490.1420598231490..0.0.1.1;_qzjb=1.1420598231490.1.0.0.0; _qzjc=1; _qzjto=1.1.0");
		headers.put("Host", "www.yinpiao.com");
		headers.put("Origin", "http://www.yinpiao.com");
		headers.put("Referer", "http://www.yinpiao.com/bill");
		headers.put(
				"User-Agent",
				"Mozilla/5.0 (Windows NT 5.1) AppleWebKit/537.36 (KHTML,like Gecko) Chrome/33.0.1750.154 Safari/537.36");
		headers.put("X-Requested-With", "XMLHttpRequest");
		String charset = "utf-8";
		String bodyhtml = fetcher.post(temp, params, headers, charset);

		JSONObject json = JSONObject.fromObject(bodyhtml);
		Map<String, Object> map = (Map) json;
		bodyhtml = (String) map.get("d");
		if (bodyhtml == null || bodyhtml.isEmpty()) {
			return null;
		}
		Document document = Jsoup.parse(bodyhtml, url);
		return document;
	}
}
