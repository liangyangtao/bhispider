package com.unbank.paser.other;

import com.unbank.fetch.Fetcher;
import com.unbank.paser.entity.WebBean;
import com.unbank.paser.nextpage.NextPagePaser;

public class WebBeanPaser {

	public void paseWebBean(WebBean webBean, Fetcher fetcher) {
	    new NextPagePaser().paseNextPage(webBean, fetcher);

	}

}
