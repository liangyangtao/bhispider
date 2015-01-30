package com.unbank.paser.detail;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;



public class DetailPaserFilterLocator {

	private static DetailPaserFilterLocator filterLocator=new DetailPaserFilterLocator();
	
	HashMap<String, BaseDetailPaser> filters = new HashMap<String, BaseDetailPaser>();
	
	BaseDetailPaser baseFilter =new BaseDetailPaser();
	
	public BaseDetailPaser getFilter(String url) {
		String host=getDomain(url);
		if(filters.containsKey(host)) {
			return filters.get(host);
		}
		return baseFilter;
	}
	
	public void register(String host, BaseDetailPaser filter) {
		filters.put(host, filter);
	}

	public void unregister(String host) {
		filters.remove(host);
	}
	
	
	
	public static DetailPaserFilterLocator getInstance(){
		return filterLocator;
	}
	
	private static String getDomain(String url) {
		String domain = "";
		try {
			URL u = new URL(url);
			domain = u.getHost();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		return domain;
	}
}
