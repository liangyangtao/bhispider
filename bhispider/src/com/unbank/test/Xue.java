package com.unbank.test;


import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import com.unbank.fetch.HttpClientBuilder;

public class Xue {
	private static RequestConfig requestConfig = RequestConfig.custom()
			.setSocketTimeout(30000).setConnectTimeout(30000).build();
	private static BasicCookieStore cookieStore = new BasicCookieStore();
	static CloseableHttpClient httpClient;

	public static void main(String[] args) {
		PoolingHttpClientConnectionManager poolingHttpClientConnectionManager = new PoolingHttpClientConnectionManager();
		HttpClientBuilder httpClientBuilder = new HttpClientBuilder(false,
				poolingHttpClientConnectionManager, cookieStore);
		httpClient = httpClientBuilder.getHttpClient();
		String url = "http://bbs.gmaple.net/forum.php?mod=post&action=newthread&fid=44&extra=&topicsubmit=yes";
		Map<String, String> params = new HashMap<String, String>();
		params.put("formhash", "c6acb69e");
		params.put("posttime", new Date().getTime() + "");
		params.put("wysiwyg", "1");
		params.put("subject", getEcodeString("","gbk"));
		params.put("message", getEcodeString("","gbk"));
		params.put("replycredit_extcredits", "0");
		params.put("replycredit_times", "1");
		params.put("replycredit_membertimes", "1");
		params.put("replycredit_random", "100");
		params.put("tags", getEcodeString("雅思 考试 出国", "gbk"));
		params.put("allownoticeauthor", "1");
		params.put("usesig", "1");
		params.put("connect_publish_t", "0");
		postHtml(url, params, "utf-8");

	}

	public static String getEcodeString(String content, String chasert) {
		String now = null;
		try {
			now = URLEncoder.encode(content, chasert);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return now;
	}

	public void reply() {

		// http://bbs.gmaple.net/forum.php?mod=post&action=reply&fid=44&tid=15711&extra=&replysubmit=yes&infloat=yes&handlekey=fastpost&inajax=1

		// Accept:text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8
		// Accept-Encoding:gzip,deflate,sdch
		// Accept-Language:zh-CN,zh;q=0.8,en;q=0.6
		// Cache-Control:max-age=0
		// Connection:keep-alive
		// Content-Length:122
		// Content-Type:application/x-www-form-urlencoded
		// Cookie:1X4k_2132_saltkey=rL0378Lw; 1X4k_2132_lastvisit=1420525026;
		// 1X4k_2132_forum_lastvisit=D_44_1420528626; 1X4k_2132_visitedfid=44;
		// 1X4k_2132_seccode=2113.7503cfa9ff63d833e7;
		// 1X4k_2132_ulastactivity=ab4dOk5n7d0PL%2B9k%2FQKw8tQDjK9MdigxGYGLQH5GKPVXN1%2F5Exfw;
		// 1X4k_2132_auth=06c89OknHJA7a6eqkQthsZ4pIpDzTY%2Bo6nnRcG2TNUi2Q4UpzxZU0ATQoAnP5LJUS8wZ25oRfo%2BPMS58b3nU;
		// 1X4k_2132_lastcheckfeed=7%7C1420528681;
		// 1X4k_2132_security_cookiereport=b2c7BAmqt9ZXiu%2B9pgUCihY424Zbu%2Fm2SemhQ3%2FRFvonvpQtk7zC;
		// 1X4k_2132_st_t=7%7C1420528684%7C679c1f1a9953824c32e87995f8f9a033;
		// 1X4k_2132_editormode_e=1; BRIDGE_REFRESH=15000;
		// baidu_qiao_v3_count_4953623=1; 1X4k_2132_connect_not_sync_feed=1;
		// 1X4k_2132_connect_not_sync_t=1;
		// 1X4k_2132_st_p=7%7C1420528785%7Cedba41a8602988c3dcf87bd42fd7217d;
		// 1X4k_2132_viewid=tid_15711;
		// CNZZDATA1253399161=82260815-1420528633-%7C1420528633;
		// Hm_lvt_f05f12496e63e2bd131394247803f001=1420528638;
		// Hm_lpvt_f05f12496e63e2bd131394247803f001=1420528792; hasshown=1;
		// VERSION=2,0,0,0; 1X4k_2132_smile=1D1; BRIDGE_INVITE_0=0;
		// tjpctrl=1420530676054; BRIDGE_NEED=1; BRIDGE_CLOCK=1420530580057;
		// 1X4k_2132_sid=i72bns; 1X4k_2132_lip=124.193.178.206%2C1420528681;
		// 1X4k_2132_lastact=1420530578%09forum.php%09ajax;
		// 1X4k_2132_connect_is_bind=1
		// Host:bbs.gmaple.net
		// Origin:http://bbs.gmaple.net
		// Referer:http://bbs.gmaple.net/forum.php?mod=viewthread&tid=15711&extra=
		// User-Agent:Mozilla/5.0 (Windows NT 5.1) AppleWebKit/537.36 (KHTML,
		// like Gecko) Chrome/33.0.1750.154 Safari/537.36

		// message:(unable to decode value)
		// posttime:1420528785
		// formhash:c6acb69e
		// usesig:1
		// subject:
		// connect_publish_t:0

		// http://bbs.gmaple.net/forum.php?mod=post&action=reply&fid=44&tid=15711&extra=&replysubmit=yes&infloat=yes&handlekey=fastpost&inajax=1

		// Accept:text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8
		// Accept-Encoding:gzip,deflate,sdch
		// Accept-Language:zh-CN,zh;q=0.8,en;q=0.6
		// Cache-Control:max-age=0
		// Connection:keep-alive
		// Content-Length:122
		// Content-Type:application/x-www-form-urlencoded
		// Cookie:1X4k_2132_saltkey=rL0378Lw; 1X4k_2132_lastvisit=1420525026;
		// 1X4k_2132_forum_lastvisit=D_44_1420528626; 1X4k_2132_visitedfid=44;
		// 1X4k_2132_seccode=2113.7503cfa9ff63d833e7;
		// 1X4k_2132_ulastactivity=ab4dOk5n7d0PL%2B9k%2FQKw8tQDjK9MdigxGYGLQH5GKPVXN1%2F5Exfw;
		// 1X4k_2132_auth=06c89OknHJA7a6eqkQthsZ4pIpDzTY%2Bo6nnRcG2TNUi2Q4UpzxZU0ATQoAnP5LJUS8wZ25oRfo%2BPMS58b3nU;
		// 1X4k_2132_lastcheckfeed=7%7C1420528681;
		// 1X4k_2132_security_cookiereport=b2c7BAmqt9ZXiu%2B9pgUCihY424Zbu%2Fm2SemhQ3%2FRFvonvpQtk7zC;
		// 1X4k_2132_st_t=7%7C1420528684%7C679c1f1a9953824c32e87995f8f9a033;
		// 1X4k_2132_editormode_e=1; BRIDGE_REFRESH=15000;
		// baidu_qiao_v3_count_4953623=1;
		// CNZZDATA1253399161=82260815-1420528633-%7C1420528633;
		// Hm_lvt_f05f12496e63e2bd131394247803f001=1420528638;
		// Hm_lpvt_f05f12496e63e2bd131394247803f001=1420528792; hasshown=1;
		// VERSION=2,0,0,0; 1X4k_2132_smile=1D1; BRIDGE_INVITE_0=0;
		// BRIDGE_NEED=1; 1X4k_2132_clearUserdata=forum;
		// 1X4k_2132_connect_not_sync_t=1; 1X4k_2132_sid=cwfUO8;
		// 1X4k_2132_lip=124.193.178.206%2C1420530582;
		// 1X4k_2132_lastact=1420530582%09forum.php%09viewthread;
		// 1X4k_2132_connect_is_bind=1;
		// 1X4k_2132_st_p=7%7C1420530582%7Ce9cd703519b0053c38a8e9318232f551;
		// 1X4k_2132_viewid=tid_15711; BRIDGE_CLOCK=1420530775340
		// Host:bbs.gmaple.net
		// Origin:http://bbs.gmaple.net
		// Referer:http://bbs.gmaple.net/forum.php?mod=viewthread&tid=15711&extra=
		// User-Agent:Mozilla/5.0 (Windows NT 5.1) AppleWebKit/537.36 (KHTML,
		// like Gecko) Chrome/33.0.1750.154 Safari/537.36

		// message:(unable to decode value)
		// posttime:1420528785
		// formhash:c6acb69e
		// usesig:1
		// subject:
		// connect_publish_t:0

	}

	public static String postHtml(String url, Map<String, String> params,
			String charset) {
		HttpClientContext context = HttpClientContext.create();
		context.setCookieStore(cookieStore);
		String useCharset = charset;
		try {
			HttpPost httpPost = new HttpPost(url);
			fillPostHeader(url, httpPost);
			List<NameValuePair> nvps = new ArrayList<NameValuePair>();
			if (params != null) {
				for (String key : params.keySet()) {
					nvps.add(new BasicNameValuePair(key, params.get(key)));
				}
				httpPost.setEntity(new UrlEncodedFormEntity(nvps));
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

	private static void fillPostHeader(String url, HttpPost httpPost) {
		httpPost.setHeader("Accept",
				"text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
		httpPost.setHeader("Accept-Encoding", "gzip,deflate,sdch");
		httpPost.setHeader("Accept-Language", "zh-CN,zh;q=0.8,en;q=0.6");
		httpPost.setHeader("Cache-Control", "max-age=0");
		httpPost.setHeader("Connection", "keep-alive");
		httpPost.setHeader("Content-Length", "1317");
		httpPost.setHeader("Content-Type", "application/x-www-form-urlencoded");
		httpPost.setHeader(
				"Cookie",
				"1X4k_2132_saltkey=rL0378Lw; 1X4k_2132_lastvisit=1420525026; 1X4k_2132_forum_lastvisit=D_44_1420528626; 1X4k_2132_visitedfid=44; 1X4k_2132_sendmail=1; 1X4k_2132_connect_not_sync_feed=1; 1X4k_2132_connect_not_sync_t=1; 1X4k_2132_seccode=2113.7503cfa9ff63d833e7; 1X4k_2132_ulastactivity=ab4dOk5n7d0PL%2B9k%2FQKw8tQDjK9MdigxGYGLQH5GKPVXN1%2F5Exfw; 1X4k_2132_auth=06c89OknHJA7a6eqkQthsZ4pIpDzTY%2Bo6nnRcG2TNUi2Q4UpzxZU0ATQoAnP5LJUS8wZ25oRfo%2BPMS58b3nU; 1X4k_2132_lastcheckfeed=7%7C1420528681; 1X4k_2132_lip=124.193.178.206%2C1420363582; 1X4k_2132_security_cookiereport=b2c7BAmqt9ZXiu%2B9pgUCihY424Zbu%2Fm2SemhQ3%2FRFvonvpQtk7zC; 1X4k_2132_st_t=7%7C1420528684%7C679c1f1a9953824c32e87995f8f9a033; 1X4k_2132_sid=gDTihx; 1X4k_2132_editormode_e=1; CNZZDATA1253399161=82260815-1420528633-%7C1420528633; Hm_lvt_f05f12496e63e2bd131394247803f001=1420528638; Hm_lpvt_f05f12496e63e2bd131394247803f001=1420528692; hasshown=1; 1X4k_2132_noticeTitle=1; VERSION=2,0,0,0; BRIDGE_INVITE_0=0; 1X4k_2132_smile=1D1; tjpctrl=1420530520639; BRIDGE_REFRESH=15000; BRIDGE_NEED=1; baidu_qiao_v3_count_4953623=1; BRIDGE_CLOCK=1420528783183; 1X4k_2132_lastact=1420528783%09forum.php%09ajax; 1X4k_2132_connect_is_bind=1");
		httpPost.setHeader("Host", "bbs.gmaple.net");
		httpPost.setHeader("Origin", "http://bbs.gmaple.net");
		httpPost.setHeader("Referer",
				"http://bbs.gmaple.net/forum.php?mod=post&action=newthread&fid=44");
		httpPost.setHeader(
				"User-Agent",
				"Mozilla/5.0 (Windows NT 5.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/33.0.1750.154 Safari/537.36");
	}

}
