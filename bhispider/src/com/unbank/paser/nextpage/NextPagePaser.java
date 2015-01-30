package com.unbank.paser.nextpage;

import com.unbank.fetch.Fetcher;
import com.unbank.paser.entity.WebBean;
import com.unbank.paser.table.BaseTablePaser;
import com.unbank.paser.table.TableFilterLocator;

public class NextPagePaser {

	public void paseNextPage(WebBean webBean, Fetcher fetcher) {
		String temp = webBean.getNextPageXpath();
		if (temp.isEmpty()) {
			String url = webBean.getWebUrl();
			paserTable(url, webBean, fetcher);
		} else {
			for (int i = 1; i <=20; i++) {
				try {
					String url = temp.replace("@@@", i + "");
					paserTable(url, webBean, fetcher);
				} catch (Exception e) {
					e.printStackTrace();
					continue;
				}
			}

		}

	}

	private void paserTable(String url, WebBean webBean, Fetcher fetcher) {
		TableFilterLocator tableFilterLocator = TableFilterLocator
				.getInstance();
		BaseTablePaser baseTablePaser = tableFilterLocator.getFilter(url);
		baseTablePaser.paseTable(url, webBean, fetcher);
	}

}
