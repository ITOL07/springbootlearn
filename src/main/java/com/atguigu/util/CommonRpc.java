package com.atguigu.util;

import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;

import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class CommonRpc {
    public String sendMsgCode(String phoneNo,String TemplateCode){
        DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou", "LTAI2xRkE3eZJsop", "qtsHTT3MfzRmPT5NNZQJkfjKfXW4jT");
        IAcsClient client = new DefaultAcsClient(profile);

        CommonRequest request = new CommonRequest();
        String verifyCode = String
                .valueOf(new Random().nextInt(8999) + 1000);
        String TemplateParam="{\"code\":\""+verifyCode + "\"}";
        //request.setProtocol(ProtocolType.HTTPS);
        request.setMethod(MethodType.POST);
        request.setDomain("dysmsapi.aliyuncs.com");
        request.setVersion("2017-05-25");
        request.setAction("SendSms");
        request.putQueryParameter("RegionId", "cn-hangzhou");
        request.putQueryParameter("PhoneNumbers", phoneNo);
        request.putQueryParameter("SignName", "古越运动");
        request.putQueryParameter("TemplateCode", TemplateCode);
        request.putQueryParameter("TemplateParam", TemplateParam);
        try {
            CommonResponse response = client.getCommonResponse(request);
            System.out.println(response.getData());
        } catch (ServerException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            e.printStackTrace();
        }
        return verifyCode;
    }

//    public static void main(String[] args) {
//        String phoneNo="18101033062";
////        String phoneNo="18500851435";
//        SMS_152471207  身份验证验证码
//        SMS_152471206  登录确认验证码
//        SMS_152471205  登录异常验证码
//        SMS_152471204  用户注册验证码
//        SMS_152471203  修改密码验证码
//        SMS_152471202  信息变更验证码
//
//        String TemplateCode="SMS_152471206";
//
//        System.out.println("手机号："+phoneNo+"\t发送内容：");
////        CommonRpc com = new CommonRpc();
////        com.sendMsgCode(phoneNo,TemplateCode,TemplateParam);
//    }

    public static boolean isEffectiveDate(Date nowTime, Date startTime, Date endTime) {
        if (nowTime.getTime() == startTime.getTime()
                || nowTime.getTime() == endTime.getTime()) {
            return true;
        }

        Calendar date = Calendar.getInstance();
        date.setTime(nowTime);

        Calendar begin = Calendar.getInstance();
        begin.setTime(startTime);

        Calendar end = Calendar.getInstance();
        end.setTime(endTime);

        if (date.after(begin) && date.before(end)) {
            return true;
        } else {
            return false;
        }
    }
}