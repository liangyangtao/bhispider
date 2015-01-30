package com.unbank.paser.detail;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.unbank.paser.entity.WebBean;

public class BaseDetailPaser {
	public static String getNumbers(String content) {
		Pattern pattern = Pattern.compile("\\d+");
		Matcher matcher = pattern.matcher(content);
		while (matcher.find()) {
			return matcher.group(0);
		}
		return "";
	}
	public Map<String, Object> fillDetailMaps(Element document, WebBean webBean) {
		Map<String, Object> colums = new HashMap<String, Object>();
		Map<String, String> detailAtrr = webBean.getDetailAtrr();
		Iterator<String> iterator = detailAtrr.keySet().iterator();
		while (iterator.hasNext()) {
			try {
				String name = iterator.next();
				if (name.equals("detailurl")) {
					continue;
				}
				String key = detailAtrr.get(name);
				Element nodeElement = document.select(key).first();
				String value = nodeElement.text().trim();
				colums.put(name, value);
			} catch (Exception e) {
				e.printStackTrace();
				continue;
			}

		}
		return colums;
	}
	public void removeNoNeedElement(Element element, String string) {
		if (element == null) {
			return;
		}
		Elements elements = element.select(string);
		for (Element element2 : elements) {
			element2.remove();
		}

	}
}
