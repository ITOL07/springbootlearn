package com.atguigu.wechatpay.model;

/**
 * @author nantian
 *
 */
public class OrderForm {
	private String appid;//微信开放平台审核通过的应用APPID
	private String partnerid;//微信支付分配的商户号
	private String prepayid;//微信返回的支付交易会话ID
	private String packageExt;//暂时填写固定值Sign=WXPAY
	private String noncestr;//随机字符串，不长于32位
	private String timestamp;//时间戳
	private String sign;//签名
	
	public String getAppid() {
		return appid;
	}
	public void setAppid(String appid) {
		this.appid = appid;
	}
	public String getPartnerid() {
		return partnerid;
	}
	public void setPartnerid(String partnerid) {
		this.partnerid = partnerid;
	}
	public String getPrepayid() {
		return prepayid;
	}
	public void setPrepayid(String prepayid) {
		this.prepayid = prepayid;
	}
	public String getPackageExt() {
		return packageExt;
	}
	public void setPackageExt(String packageExt) {
		this.packageExt = packageExt;
	}
	public String getNoncestr() {
		return noncestr;
	}
	public void setNoncestr(String noncestr) {
		this.noncestr = noncestr;
	}
	public String getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
	public String getSign() {
		return sign;
	}
	public void setSign(String sign) {
		this.sign = sign;
	}
	
	
}
