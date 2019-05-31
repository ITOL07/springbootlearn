package com.atguigu.wechatpay.app;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.atguigu.entity.OrderPayInfo;
import com.atguigu.service.OrderService;
import com.atguigu.wechatpay.common.CustConsts;
//import org.apache.commons.lang3.StringUtils;
//import org.apache.log4j.Logger;
import org.jdom.JDOMException;
import org.springframework.stereotype.Repository;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.atguigu.wechatpay.common.CustConsts;
import com.atguigu.wechatpay.utils.PayCommonUtil;

@Repository
public class UnifiedOrder {

//	private static final Logger logger = Logger.getLogger(UnifiedOrder.class);

	/**
	 * 统一下单
	 * 
	 * @param sn
	 * @param totalAmount
	 * @param description
	 * request
	 * @return
	 */

	@Resource
	OrderService orderService;

	public static Map<String, Object> weixinPrePay(String sn,
			BigDecimal totalAmount, String description,String openid) {
		// Setting setting = SettingUtils.get();
		SortedMap<String, Object> parameterMap = new TreeMap<String, Object>();
		String appId = CustConsts.APPID;
		String mchId = CustConsts.MCH_ID;
		String noceStr = PayCommonUtil.getRandomString(32);
		parameterMap.put("appid", appId);
		parameterMap.put("mch_id", mchId);
		parameterMap.put("nonce_str", noceStr);
        parameterMap.put("openid", openid);
//		parameterMap.put("body",
//				StringUtils.abbreviate(description.replaceAll(
//						"[^0-9a-zA-Z\\u4e00-\\u9fa5 ]", ""), 600));
        parameterMap.put("body",
                description.replaceAll(
                        "[^0-9a-zA-Z\\u4e00-\\u9fa5 ]", ""));
		parameterMap.put("out_trade_no", sn);
		parameterMap.put("fee_type", "CNY");
		BigDecimal total = totalAmount.multiply(new BigDecimal(100));
		java.text.DecimalFormat df = new java.text.DecimalFormat("0");
		parameterMap.put("total_fee", df.format(total));
//		parameterMap.put("spbill_create_ip", request.getRemoteAddr());// TODO
//		System.out.println("登录用户ip地址：" + request.getRemoteAddr());
//		parameterMap.put("spbill_create_ip", "192.168.1.1");// 正常应该获取地址，这里暂时写死
		parameterMap.put("notify_url", CustConsts.NOTIFY_URL);

		//小程序支付trade_type JSAPI   微信支付用 APP
		parameterMap.put("trade_type", "JSAPI");
		String sign = "";
		String signKey = "";
		if (CustConsts.FLAG_SX) {
			/* 沙箱测试 */
			System.out.println("沙箱环境！！！");
			signKey = PayCommonUtil.getSignKey(mchId, noceStr);// 获取沙箱秘钥
			sign = PayCommonUtil.createSign1("UTF-8", parameterMap,
					signKey);
		}else{
			/* 生产 */
			System.out.println("生产环境！！！");
			sign = PayCommonUtil.createSign("UTF-8", parameterMap);
		}

		parameterMap.put("sign", sign);
		String requestXML = PayCommonUtil.getRequestXml(parameterMap);
		System.out.println("入参" + requestXML);
		String result = "";
		if(CustConsts.FLAG_SX){
			System.out.println("沙箱环境！！！");
			result = PayCommonUtil.httpsRequest(CustConsts.UNIFOD_URL_SX,
					CustConsts.UNIFOD_METHOD, requestXML);
		}else{
			System.out.println("生产环境！！！");
			result = PayCommonUtil.httpsRequest(CustConsts.UNIFOD_URL,
					CustConsts.UNIFOD_METHOD, requestXML);
		}
		System.out.println("统一下单返回结果：" + result);
		Map<String, String> map = null;
		Map<String, Object> resMap = new HashMap<String, Object>();
		try {
			map = PayCommonUtil.doXMLParse(result);
			Object prepayid = map.get("prepay_id");
			// 二次生成sign
			SortedMap<String, Object> parameterMap2 = new TreeMap<String, Object>();
			parameterMap2.put("appId", appId);
//			parameterMap2.put("partnerid", mchId);
			//parameterMap2.put("prepayid", prepayid);
			parameterMap2.put("package", "prepay_id="+prepayid);
			String noncestr2 = PayCommonUtil.getRandomString(32);
			parameterMap2.put("nonceStr", noncestr2);
			String timestamp2 = String
					.valueOf(System.currentTimeMillis() / 1000);
			parameterMap2.put("timeStamp", timestamp2);
            parameterMap2.put("signType", "MD5");
			
			String sign2 = "";
			if (CustConsts.FLAG_SX) {
				/* 沙箱测试 */
				System.out.println("沙箱环境！！！");
				signKey = PayCommonUtil.getSignKey(mchId, noncestr2);// 获取沙箱秘钥
				sign2 = PayCommonUtil.createSign1("UTF-8", parameterMap2,
						signKey);
			}else{
				/* 生产 */
				System.out.println("生产环境！！！");
				sign2 = PayCommonUtil.createSign("UTF-8", parameterMap2);
			}

			// 返回map打包
			resMap.put("appid", appId);
            resMap.put("package", "prepay_id="+prepayid);
			resMap.put("noncestr", noncestr2);
			resMap.put("timestamp", timestamp2);
			resMap.put("sign", sign2);
			resMap.put("return_code", map.get("return_code"));
			resMap.put("result_code", map.get("result_code"));

            System.out.println("resMap = appid[" + appId + "]partnerid[" + mchId
					+ "]prepayid[" + prepayid + "]package[+prepayid="+prepayid+"]noncestr["
					+ noncestr2 + "]timestamp[" + timestamp2 + "]sign[" + sign2
					+ "]");
		} catch (JDOMException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resMap;
	}

	/**
	 * 下单结果查询
	 * 
	 * @param sn 订单号
	 * @return 订单信息
	 */
	public static Map<String, Object> queryWxResult(String sn) {

		SortedMap<String, Object> parameterMap = new TreeMap<String, Object>();
		String appId = CustConsts.APPID;
		String mchId = CustConsts.MCH_ID;
		String noceStr = PayCommonUtil.getRandomString(32);
		parameterMap.put("appid", appId);
		parameterMap.put("mch_id", mchId);
		parameterMap.put("nonce_str", noceStr);

		parameterMap.put("out_trade_no", sn);
		String sign = "";
		if (CustConsts.FLAG_SX) {
			/* 沙箱测试 */
			System.out.println("沙箱环境！！！");
			String signKey = PayCommonUtil.getSignKey(mchId, noceStr);// 获取沙箱秘钥
			sign = PayCommonUtil.createSign1("UTF-8", parameterMap,
					signKey);
		}else{
			/* 生产 */
			System.out.println("生产环境！！！");
			sign = PayCommonUtil.createSign("UTF-8", parameterMap);
		}
		parameterMap.put("sign", sign);
		String requestXML = PayCommonUtil.getRequestXml(parameterMap);
		System.out.println("订单查询入参:" + requestXML);
		String result = "";
		if(CustConsts.FLAG_SX){
			System.out.println("沙箱环境！！！");
			result = PayCommonUtil.httpsRequest(CustConsts.RESULT_URL_SX,
					CustConsts.RESULT_METHOD, requestXML);
		}else{
			System.out.println("生产环境！！！");
			result = PayCommonUtil.httpsRequest(CustConsts.RESULT_URL,
					CustConsts.RESULT_METHOD, requestXML);
		}
		System.out.println("订单查询返回结果：" + result);
		Map<String, Object> map = null;
		try {
			map = PayCommonUtil.doXMLParse(result);
			String returnCode = (String) map.get("return_code");
			System.out.println("returnCode:" + returnCode);
		} catch (JDOMException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return map;

	}

	public static void main(String[] args) {

//		Map<String, Object> resultMap = new HashMap<String, Object>();
//		String money = "0.01";//TODO 测试马路边捡到一分钱
//
//		BigDecimal bigtotalAmount = new BigDecimal(money);
//		String description = "十万元重要疾病保险";
//		//HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
//
//		resultMap = UnifiedOrder.weixinPrePay("2017051200000008", bigtotalAmount, description,CustConsts.OPENID);
//		if("SUCCESS".equals(resultMap.get("return_code")) && "SUCCESS".equals(resultMap.get("result_code"))) {
//
//
//			resultMap.put("RESPCODE", "2");
//		} else {
//			resultMap.put("RESPCODE", "3");
//			resultMap.put("RESPINFO", "统一下单失败");
//		}
//
//        Map<String, Object> payMap = new HashMap<String, Object>();
//        payMap=UnifiedOrder.queryWxResult("2017051200000008");
//
////		String sn = UUID.randomUUID().toString().replaceAll("-", "");
////		BigDecimal totalAmount = new BigDecimal(2.33);
////		String description = "支付啦";
////		HttpServletRequest request = null;
////		Map<String, Object> resMap = weixinPrePay(sn, totalAmount, description,
////				request);
////		System.out.println("prepay_id is : " + resMap.get("prepayid"));
//
////		String sn = "cfed58a57b0a64b0e9e86f2bd4679dd6";
////		queryWxResult(sn);
	}
}
