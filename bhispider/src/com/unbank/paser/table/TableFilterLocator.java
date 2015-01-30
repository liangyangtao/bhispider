package com.unbank.paser.table;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;



public class TableFilterLocator {

	private static TableFilterLocator filterLocator=new TableFilterLocator();
	
	HashMap<String, BaseTablePaser> filters = new HashMap<String, BaseTablePaser>();
	
	BaseTablePaser baseFilter =new BaseTablePaser();
	
	public BaseTablePaser getFilter(String url) {
		String host=getDomain(url);
		if(filters.containsKey(host)) {
			return filters.get(host);
		}
		return baseFilter;
	}
	
	public void register(String host, BaseTablePaser filter) {
		filters.put(host, filter);
	}

	public void unregister(String host) {
		filters.remove(host);
	}
	
	
	
	public static TableFilterLocator getInstance(){
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
