package com.unbank.paser.table;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Service;

@Service
public class Jr138TablePaser extends BaseTablePaser {
	private String domain1 = "www.138jr.com";

	public Jr138TablePaser() {
		TableFilterLocator.getInstance().register(domain1, this);
	}

	@Override
	public String getDetailUrl(String detailurlXpath, Element element) {

		// <a style="cursor: pointer;"
		// onclick="javascript:window.open('/member_admin/displayLeft.htm?action=queryBillInfo&id=90145&way=0&type=1')"
		// class="bank_4d">详细</a> |
		Element a_Element = element.select("a").first();
		String javascriptText = a_Element.attr("onclick");
		String temp = StringUtils.substringBetween(javascriptText,
				"javascript:window.open('", "')");
		temp = "http://www.138jr.com" + temp;
		return temp;
	}

}
