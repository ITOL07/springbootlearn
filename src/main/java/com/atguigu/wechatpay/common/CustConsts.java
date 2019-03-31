package com.atguigu.wechatpay.common;

public class CustConsts {
	// 微信参数配置  
	public static final String APPID="wx9dabed0089993ef3";//应用id
	public static final String MCH_ID="1525988861";//商户号
	public static final String API_KEY = "guyueyundong20190329startupAPI00";//apikey
	public static final String OPENID= "osxFH4wTSaP9LCEB3AZmoI5LWuMQ";
	public static final boolean FLAG_SX = false;//如果是沙箱测试修改为true，生产改为false
	// 统一支付地址
	public static final String UNIFOD_URL="https://api.mch.weixin.qq.com/pay/unifiedorder";
	public static final String UNIFOD_URL_SX="https://api.mch.weixin.qq.com/sandboxnew/pay/unifiedorder";
	public static final String UNIFOD_METHOD="POST";
	// 沙箱秘钥获取地址
	public static final String SIGNKEY_URL="https://api.mch.weixin.qq.com/pay/getsignkey";
	public static final String SIGNKEY_URL_SX="https://api.mch.weixin.qq.com/sandboxnew/pay/getsignkey";
	public static final String SIGNKEY_METHOD="POST";
	// 结果查询地址
	public static final String RESULT_URL="https://api.mch.weixin.qq.com/pay/orderquery";
	public static final String RESULT_URL_SX="https://api.mch.weixin.qq.com/sandboxnew/pay/orderquery";
	public static final String RESULT_METHOD="POST";
	// 接收微信异步通知地址
	//public static final String NOTIFY_URL = MfpContextHolder.getProps("wechatpay.NOTIFY_URL");
	public static final String NOTIFY_URL = "https://www.guyueyundong.com/";
	public static final boolean WXPROXY_FLAG = false;// 正向代理服务器开关:ture为使用代理服务器，false为不使用代理服务器
//	public static final String WXPROXY_URL = MfpContextHolder.getProps("wechatpay.WXPROXY_URL");// 正向代理服务器地址
//	public static final Integer WXPROXY_PORT = 8609;// 正向代理服务器端口
	
}
