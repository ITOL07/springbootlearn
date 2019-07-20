package com.atguigu.util;

public class CommParams {

    public static final boolean FLAG_SX = false;//如果是沙箱测试修改为true，生产改为false
    // 会员端登录参数
    public static final String APPID_MEM="wx9dabed0089993ef3";//应用id
    public static final String APP_SECRET_MEM="c19bc48143163c69c41d6c8134b6fa98";//应用密钥

    // 教练端登录参数
    public static final String APPID_COACH="wxbc9ea30aab6c0842";//应用id
    public static final String APP_SECRET_COACH="4be6f6f1bafdcf9c8ee608feb95c60fe";//应用密钥

    //场地端登录参数

    public static final String APPID_CLUB="wx19d1c80e58f2a7e8";//应用id
    public static final String APP_SECRET_CLUB="41a8deafa0a6c7c0f4048d3a114110ab";//应用密钥

    //图片存放根路径
    public static final String IMG_LOCATION="/app/test/images/";
    public static final String IMG_LOCATION_TEST="/tmp/";

    public static final String WEB_URL="https://www.guyueyundong.com";
    // 统一支付地址
    public static final String WX_LOGIN_URL="https://api.weixin.qq.com/sns/jscode2session";

    //图片类型
    //HY--会员，JL--教练，CD--场地，KC--课程
    //ICON--头像，CERT--证书，PIC--图片，AL--相册
    public static final Integer HY_ICON=11;
    public static final Integer JL_ICON=21;
    public static final Integer JL_PIC=22;
    public static final Integer JL_CERT=23;
    public static final Integer JL_AL=24;
    public static final Integer JL_XC=25;
    public static final Integer CD_ICON=31;
    public static final Integer CD_PIC=32;
    public static final Integer HD_ICON=41;
    public static final Integer KC_ICON=51;
    public static final Integer KC_PIC=52;

}
