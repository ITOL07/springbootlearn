package com.atguigu.wechatpay.notify;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import com.alibaba.fastjson.*;

//import org.apache.log4j.Logger;
//import org.jdom.JDOMException;

//import com.lycheepay.gateway.client.http.HttpRequest;
import com.atguigu.wechatpay.common.CustConsts;
import com.atguigu.wechatpay.utils.PayCommonUtil;

public class NotifyTest {
//	private static final Logger logger = Logger.getLogger(NotifyTest.class);

	public static JSONObject httpRequest(String requestUrl, String requestMethod) {
		JSONObject jsonObject = null;
		StringBuffer buffer = new StringBuffer();
		try {

			URL url = new URL(requestUrl);
			// http协议传输
			HttpURLConnection httpUrlConn = (HttpURLConnection) url
					.openConnection();

			httpUrlConn.setDoOutput(true);
			httpUrlConn.setDoInput(true);
			httpUrlConn.setUseCaches(false);
			// 设置请求方式（GET/POST）
			httpUrlConn.setRequestMethod(requestMethod);

			if ("GET".equalsIgnoreCase(requestMethod))
				httpUrlConn.connect();
			// 将返回的输入流转换成字符串
			InputStream inputStream = httpUrlConn.getInputStream();
			InputStreamReader inputStreamReader = new InputStreamReader(
					inputStream, "utf-8");
			BufferedReader bufferedReader = new BufferedReader(
					inputStreamReader);

			String str = null;
			while ((str = bufferedReader.readLine()) != null) {
				buffer.append(str);
			}
			bufferedReader.close();
			inputStreamReader.close();
			// 释放资源
			inputStream.close();
			inputStream = null;
			httpUrlConn.disconnect();
			//jsonObject = JSONObject.fromObject(buffer.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jsonObject;
	}

	public static String getContent(InputStream is, String charset) {
		String pageString = null;
		InputStreamReader isr = null;
		BufferedReader br = null;
		StringBuffer sb = null;
		try {
			isr = new InputStreamReader(is, charset);
			br = new BufferedReader(isr);
			sb = new StringBuffer();
			String line = null;
			while ((line = br.readLine()) != null) {
				sb.append(line + "\n");
			}
			pageString = sb.toString();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (is != null) {
					is.close();
				}
				if (isr != null) {
					isr.close();
				}
				if (br != null) {
					br.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			sb = null;
		}
		return pageString;
	}

	/**
	 * @param url
	 *            post传参
	 * @param xml
	 * @param method
	 * @param contentType
	 * @return
	 */
	public static String xmlHttpProxy(String url, String xml, String method,
			String contentType) {
		InputStream is = null;
		OutputStream os = null;
		/** 接口调用成功标识 */
		boolean is_success = true;
		try {
			if (null == method || method.equals("")) {
				method = "GET";
			}
			if (null == contentType || contentType.equals("")) {
				contentType = "UTF-8";
			}
			URL _url = new URL(url);
			HttpURLConnection conn = (HttpURLConnection) _url.openConnection();
			conn.setDoInput(true);
			conn.setDoOutput(true);
			conn.setRequestProperty("Content-type", contentType);
			conn.setRequestMethod(method);
			// conn.setRequestProperty("User-Agent",
			// "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
			// 接收参数
			if (null != xml) {
				os = conn.getOutputStream();
				os.write(xml.getBytes());
				os.flush();
			}
			// 返回值
			is = conn.getInputStream();
			return getContent(is, "utf-8");
		} catch (MalformedURLException e) {
			is_success = false;
			e.printStackTrace();
		} catch (IOException e) {
			is_success = false;
			e.printStackTrace();
		} finally {
			try {
				if (os != null) {
					os.close();
				}
				if (is != null) {
					is.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	public static void testNotifyUrl() {

		try {
			//String requestUrl = CustConsts.NOTIFY_URL;
			String xml = "<xml><appid>wxe82c14129856438c</appid><body><![CDATA[test]]></body><fee_type>CNY</fee_type><mch_id>1461694702</mch_id><nonce_str>6gkxKC7C1X0onL2BK9giD5C9f4oo70Vn</nonce_str><notify_url>http://58.49.112.218:8600/mfpServer/wechnotify</notify_url><out_trade_no>2015080612ccccf</out_trade_no><sign><![CDATA[8809779007F99752E70875554DC4CFBE]]></sign><spbill_create_ip>192.168.1.1</spbill_create_ip><total_fee>233</total_fee><trade_type>APP</trade_type></xml>";
			//xmlHttpProxy(requestUrl, xml, "POST", "");
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	public static void main(String[] args) {
		testNotifyUrl();
	}
}
