package com.unbank.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import com.unbank.fetch.HttpClientBuilder;

public class Test {
	private static RequestConfig requestConfig = RequestConfig.custom()
			.setSocketTimeout(30000).setConnectTimeout(30000).build();
	private static BasicCookieStore cookieStore = new BasicCookieStore();
	static CloseableHttpClient httpClient;

	public static void main(String[] args) {
		PoolingHttpClientConnectionManager poolingHttpClientConnectionManager = new PoolingHttpClientConnectionManager();
		HttpClientBuilder httpClientBuilder = new HttpClientBuilder(false,
				poolingHttpClientConnectionManager, cookieStore);
		httpClient = httpClientBuilder.getHttpClient();
		// String temp = "http://www.yinpiao.com/JSON/GetJsonBill";
		// Map<String, String> params = new HashMap<String, String>();
		// params.put("pn", "2");
		// params.put("o", "10");
		// params.put("w", "");
		// params.put("i", "0");
		// Map<String, String> headers = new HashMap<String, String>();
		// headers.put("Accept",
		// "application/json, text/javascript, */*; q=0.01");
		// headers.put("Accept-Encoding", "gzip,deflate,sdch");
		// headers.put("Accept-Language", "zh-CN,zh;q=0.8,en;q=0.6");
		// headers.put("Connection", "keep-alive");
		// headers.put("Content-Type",
		// "application/x-www-form-urlencoded; charset=UTF-8");
		// headers.put(
		// "Cookie",
		// "from=; CNZZDATA1253101689=747322261-1420598066-%7C1420598066; _jzqa=1.4429922628883153400.1420598231.1420598231.1420598231.1;_jzqc=1; _jzqckmp=1; _jzqb=1.1.10.1420598231.1;_qzja=1.1766931996.1420598231490.1420598231490.1420598231490.1420598231490.1420598231490..0.0.1.1;_qzjb=1.1420598231490.1.0.0.0; _qzjc=1; _qzjto=1.1.0");
		// headers.put("Host", "www.yinpiao.com");
		// headers.put("Origin", "http://www.yinpiao.com");
		// headers.put("Referer", "http://www.yinpiao.com/bill");
		// headers.put(
		// "User-Agent",
		// "Mozilla/5.0 (Windows NT 5.1) AppleWebKit/537.36 (KHTML,like Gecko) Chrome/33.0.1750.154 Safari/537.36");
		// headers.put("X-Requested-With", "XMLHttpRequest");
		// String charset = "utf-8";
		// String bodyhtml = post(temp, params, headers, charset);
		//
		// JSONObject json = JSONObject.fromObject(bodyhtml);
		// Map<String, Object> map = (Map) json;
		// bodyhtml = (String) map.get("d");
		// System.out.println(bodyhtml);

		// String url =
		// "http://www.yanglee.com/ajax/ProductSearch.ashx?_=1420679917746&mode=Search&perpage=60&page=2&ProStr=NodeCode%253D%2527105015016007%2527%2520and%2520FlowState%253D99%2520and%2520IsDel%253D0%2520and%2520ProductLevel%253C%253E%2527VIP%2527%2520&strOrder=released%2520desc&ProductState=&jigou=&qixian=&shouyi=&InvestField=&ApplyWay=";
		// String html = getHtml(url, "utf-8");
		// System.out.println(html);

		// String url
		// ="http://www.yanglee.com/product/product_Lists.aspx?ptype=87";
		//
		// String a =
		// url.split("http://www.yanglee.com/product/product_Lists.aspx\\?ptype=")[1];
		// System.out.println(a);

		// String url
		// ="http://source.chinaventure.com.cn/list.php?catid=1&type=0&industry=0&keyword2=0&pageNo=3";
		// String html =getHtml(url, "utf-8");
		// System.out.println(html);
		// System.out.println(String.valueOf(new
		// Date().getTime()).substring(0,10));

		// String url = "http://www.chinabill.cn/jsp/tradeList.jsp";
		//
		// String html = getHtml(url, "utf-8");
		// System.out.println(html);

		// String temp =
		// "http://www.chinabill.cn/cbt/CbtController/searchTradeInfoList";
		// Map<String, String> params = new HashMap<String, String>();
		// // String a = url.split("http://www.chinabill.cn/")[1];
		// params.put("pageNum", "3");
		// params.put("type", "1");
		// params.put("startTime", "");
		// params.put("endTime", "");
		// params.put("startAmount", "");
		// params.put("endAmount", "");
		// Map<String, String> headers = new HashMap<String, String>();
		// headers.put("Accept",
		// "application/json, text/javascript, */*; q=0.01");
		// headers.put("Accept-Encoding", "gzip,deflate,sdch");
		// headers.put("Accept-Language", "zh-CN,zh;q=0.8,en;q=0.6");
		// headers.put("Connection", "keep-alive");
		// headers.put("Content-Type",
		// "application/x-www-form-urlencoded; charset=UTF-8");
		// headers.put("Host", "www.chinabill.cn");
		// headers.put("Origin", "www.chinabill.cn");
		// headers.put("Referer", "http://www.chinabill.cn/jsp/tradeList.jsp");
		// headers.put(
		// "User-Agent",
		// "Mozilla/5.0 (Windows NT 5.1) AppleWebKit/537.36 (KHTML,like Gecko) Chrome/33.0.1750.154 Safari/537.36");
		// headers.put("X-Requested-With", "XMLHttpRequest");
		// String charset = "utf-8";
		// String bodyhtml = post(temp, params, headers, charset);
		//
		// JSONObject json = JSONObject.fromObject(bodyhtml);
		// Map<String, Object> map = (Map) json;
		// JSONArray jsonArray = (JSONArray) map.get("rows");
		//
		// String a = jsonArray.toString();
		// DESTools desTools = new DESTools("1234567");
		// String str2 = desTools.encryptStr(a);
		// Document document = Jsoup.parse(str2);
		// Element body = document.select("body").first();
		// String bodyText = body.text();
		// bodyText = bodyText.replaceAll(" ", "\n");
		// String b = desTools.decryptStr(bodyText);
		// JSONArray jsonArray2 = JSONArray.fromObject(b);
		// for (Object object : jsonArray2) {
		// System.out.println(object);
		// }
		// for (Object object : jsonArray) {
		// System.out.println(object);
		//
		// }

		// String url ="http://www.piaojuyi.com/ztinfo.asp?fbuser=&pagenum=1";
		// String html = getHtml(url, "gbk");
		// System.out.println(html);

		String url = "http://10.0.0.127:8080/extract.htm";
		Map<String, String> params = new HashMap<String, String>();
		String title = "IO调度策略之我见 ";
		params.put("title", title);
		// String content =
		// "和策略分离，层法改造的，而其他的算法都是对linus电梯的改进，deadline算法避免了饥饿，避免了磁盘中临近的区域大量的请求被瞬间提交，从而造成早期提交的一个稍微远一些的请求饥饿，因为deadline算法中每一个请求都有一个饥饿时间限制值，一个请求同时要插入到两个队列，其中一个队列是linus电梯里面的队列，另一个是FIFO的按照到达时间排序的队列，这个FIFO队列也按照read和write进行了区分，read队列的饥饿时间限制要更短一些，因为应用程序对于读请求一般都是同步意义的，并且对于磁盘文件来讲如果顺序读的话，前面的读没有完成后面的读更是无法进行，而写操作就不是这样的，应用程序写文件一般都是异步意义上的，更复杂的，如果很多进程都在写一个文件的一个区域，那么只要最后一个写操作完成就可以了，前面的几个都可以直接抛弃，再者，写入缓存还是写入磁盘，这都不是应用程序所关心的，因此写操作的超时时间要大于读操作，deadline在linus电梯的基础上避免了饥饿，那么引入了另一个问题，试想如果一系列写操作正在进行，这些写操作都是经过linus电梯算法合并和排序过的，这时突然有个读请求饥饿超时到时间了，那么不可避免的，磁头要移动到读请求的位置，这样显然降低了磁盘的吞吐量，然而这个突然的移动还不得不做，读请求完成以后，按照deadline的逻辑，磁盘的磁头又返回刚才被读请求打断的写请求的位置，然后...，这一次又一次的移动，不断降低着磁盘的吞吐量，并且磁头频繁移动，损坏磁盘，AS算法出现了，在不得不服务超时读请求后，不是返回写请求，而是等待一段时间，因为调度策略认为，在等待的这一段时间内，可能会有另一个读请求，将读请求积攒在一起总比来来去去要好，AS调度策略的要点在于到底是否要等待以及等待多久，这些都是按照对进程的统计完成的，算法很复杂，然而思想却很简单，一个不得不做的读请求完成以后，IO调度器在一段时间内等待“相邻”读请求的到来，随着预测得到的等待时间的增长，“相邻”的概念范围越来越宽，并且也能加进来一些写请求，最终如果这个相邻的概念已经扩展到了整个磁盘，那么AS算法也就退化成了deadline算法。实质上，AS算法就是摆脱了deadline算法的弊端，它的弊端就是磁头可能来回移动从而降低了吞吐量，而deadline算法是摆脱了linus电梯的弊端，它的弊端就是会引起饥饿，总而言之，吞吐量和磁头寻道紧密相连，它和饥饿是一对冤家，需要权衡！";
		String content = "		<P >　　 <STRONG>【理财案例】</STRONG></P> <P >　　张小姐，今年28岁，山西阳泉县人，目前每月税后收入2000元。老公每月税后收入5000元，夫妻俩人都有社保。育有一儿，现已三岁，平时由爷爷奶奶照顾着。家庭每月生活开支2000元，其他月开支500元，家庭年保险费用3000元/年，保额50000元。目前家庭有活期和现金存款2万元，定期存款12万元，自住房一套价值20万元，汽车市值8万元。想咨询理财师家庭如何进行稳健理财。</P> <P >　　 <STRONG>【理财目标】</STRONG></P> <P >　　家庭如何进行稳健理财。</P> <P >　　 <STRONG>【案例分析】</STRONG></P> <P >　　张小姐家庭财务情况： <SPAN>作为山西阳泉县的一个普通家庭，张小姐和先生的收入都不错，家庭年收入总计84000元，年支出总计33000元，每年能结余资金51000元。同时家庭还有14万元的存款，总的来说家庭经济情况还可以。不过，理财师认为家庭在理财方式上，还需适当增加一些投资，进行一些稳健型的高收益投资，让家庭财富实现利益最大化。其次，在孩子教育金方面，建议提前储备，缓解未来家庭的经济压力。第三，夫妻保障方面也需完善，适当补充一些商业保险，提高家庭保障。</P> <P >　　 <STRONG>【理财建议】</STRONG></P> <P >　　分析了张小姐家的财务情况，并结合家庭理财目标，理财师给予了张小姐以下几点家庭理财建议：</P> <P >　　1、保守理财转为固定理财</P> <P >　　张小姐家在收入开支方面比较合理，而且家庭每月总收入是7000元，除去每月2500元的生活开支，每月能有4500元结余资金。对于这部分的资金，理财师不建议直接存活期或现金储备，可以有计划地拿出三分之一收入作为家庭备用金，金额为1500元；三分之一收入储备孩子教育金，金额为1500元；剩余三分之一收入进行储蓄，金额为1500元。</P> <P >　　另外，在现今物价飞速上涨的年代，家庭14万元存款一直存银行，长期来看，金钱会贬值。因此，不建议张小姐采取过于保守的理财方式。考虑到张小姐和老公都很年轻，工作和收入较稳定，可将保守理财方式转为固定理财，14万元配置固定收益类产品，年化收益率为10%，14万元1年收益1.4万，收益比存银行1年利息（4200元=14万*3%）整整高出9800元，相对来说比较划算。</P> <P >　　2、提前储备孩子教育金</P> <P >　　大多数家庭已经认识到家庭教育投资的必要性，都会做到提前储备孩子教育金，一来能满足孩子日后上学的资金需求，二来缓解家庭经济的上的压力。张小姐可以每月拿出三分之一收入1500元来储备孩子教育金，作为孩子生活用品费用、营养补充以及学费等，这部分资金可根据孩子年龄的增长相应地增加。在储备教育金的方式上，理财师认为可以采用1年零存整取的方式，每月按时投入资金，期满后可自动转存。或者通过基金定投的方式积累教育金，每月投资1500元，定投15年，到孩子上大学时，足够支付普通大学教育费用。</P> <P >　　3、夫妻再适当配置一些商业保险</P> <P >　　张小姐和老公在保障方面，理财师建议以后考虑再配置一些商业保险来补充社保，先配置意外险和重疾险，然后考虑养老型保险，提高家庭保障。家庭保险费用的支出最好控制在家庭年收入的10%-20%，这样既不会影响到家庭的正常开支，又能获得必要的保障。通过以上家庭投资理财方案，张小姐家的资产不仅实现了稳健增值，而且在孩子教育金方面也提前做好了准备，家庭成员的保障也得到了提高。</P></SPAN>";
		Document document = Jsoup.parse(content);
		params.put("content", document.text());
		params.put("count", "10");
		// url = url + "?title=理财案例&content=理财案例张小姐，今年28岁，山西阳泉县人，目前每月税后收入2000元";
		// String html = getHtml(url, "utf-8");
		String html = post(url, params, null, "utf-8");
		System.out.println(html);
		// JSONObject json = JSONObject.fromObject(html);
		JSONArray jsonArray = JSONArray.fromObject(html);
		for (Object object : jsonArray) {
			System.out.println(object);
		}

	}

	public static String getHtml(String url, String charset) {
		HttpClientContext context = HttpClientContext.create();
		context.setCookieStore(cookieStore);
		String useCharset = charset;
		HttpGet httpGet = new HttpGet(url);
		// fillHeader(url, httpGet);
		httpGet.setConfig(requestConfig);
		try {
			CloseableHttpResponse response = httpClient.execute(httpGet,
					context);
			try {
				HttpEntity entity = response.getEntity();
				return EntityUtils.toString(entity, useCharset);
			} finally {
				response.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	private static void fillHeader(HttpPost httpGet) {
		httpGet.setHeader("User-Agent",
				"Mozilla/5.0 (Windows NT 5.1; rv:29.0) Gecko/20100101 Firefox/29.0");
		httpGet.setHeader("Accept",
				"text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
		httpGet.setHeader("Accept-Language",
				"zh-cn,zh;q=0.8,en-us;q=0.5,en;q=0.3");
		httpGet.setHeader("Accept-Encoding", "gzip, deflate");
		httpGet.setHeader("Connection", "keep-alive");
		// httpGet.setHeader("Referer", url);
		httpGet.setHeader("Cache-Control", "max-age=0");
	}

	public static String post(String url, Map<String, String> params,
			Map<String, String> headers, String charset) {
		HttpClientContext context = HttpClientContext.create();
		context.setCookieStore(cookieStore);
		String useCharset = charset;
		try {
			HttpPost httpPost = new HttpPost(url);
			// if (headers != null) {
			// for (String key : headers.keySet()) {
			// httpPost.setHeader(key, headers.get(key));
			// }
			// }
			// fillHeader(httpPost);
			// UrlEncodedFormEntity formEntity = new
			// UrlEncodedFormEntity(valuePairs, "GBK");
			// httpPost.setEntity(formEntity);
			List<NameValuePair> nvps = new ArrayList<NameValuePair>();
			if (params != null) {
				for (String key : params.keySet()) {
					nvps.add(new BasicNameValuePair(key, params.get(key)));
				}
				httpPost.setEntity(new UrlEncodedFormEntity(nvps, "utf-8"));
			}
			httpPost.setConfig(requestConfig);
			CloseableHttpResponse response = httpClient.execute(httpPost,
					context);
			try {
				HttpEntity entity = response.getEntity();
				return EntityUtils.toString(entity, useCharset);
			} finally {
				response.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
