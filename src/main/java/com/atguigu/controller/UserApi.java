package com.atguigu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.atguigu.util.wxLogin;
import java.util.HashMap;
import java.util.Map;
import com.alibaba.fastjson.*;

@RestController
@RequestMapping("/api")
public class UserApi {

   // @Autowired
    //private UserService userService;


    @PostMapping("/me/login")
    //public JsonResult user_login(
    public String user_login(
        @RequestParam("code") String code
//        @RequestParam("userHead") String userHead,
//        @RequestParam("userName") String userName,
//        @RequestParam("userGender") String userGender,
//        @RequestParam("userCity") String userCity,
//        @RequestParam("userProvince") String userProvince
    ) {
        // 配置请求参数
        Map<String, String> param = new HashMap<>();
        //会员端appid wx9dabed0089993ef3
        //会员端appsecret c19bc48143163c69c41d6c8134b6fa98
        //会员端openid osxFH4wTSaP9LCEB3AZmoI5LWuMQ
        //商户id 1525988861

        //itol appid: wxca481df05b53c896
        //itol appsecret 3380d60cec8755112336a59d6049526e
        //itol open_id ooWuZ5Wdj72u1vwt-dwL-Pst_crc
        //param.put("appid", "wxca481df05b53c896");
        //param.put("secret", "3380d60cec8755112336a59d6049526e");
        param.put("appid", "wx9dabed0089993ef3");
        param.put("secret", "c19bc48143163c69c41d6c8134b6fa98");
        //param.put("js_code", "061abR6p1d8sni0pBZ4p1IQS6p1abR6v");
        param.put("js_code", code);
        param.put("grant_type", "authorization_code");
        // 发送请求
        System.out.println("code is ===="+code);
        String wxResult = wxLogin.doGet("https://api.weixin.qq.com/sns/jscode2session", param);
        System.out.println("wxResult is ===="+wxResult);

        JSONObject jsonObject = JSONObject.parseObject(wxResult);
        // 获取参数返回的
        String session_key = jsonObject.get("session_key").toString();
        String open_id = jsonObject.get("openid").toString();
        // 根据返回的user实体类，判断用户是否是新用户，不是的话，更新最新登录时间，是的话，将用户信息存到数据库
       System.out.println("openid is ===="+open_id);
//        User user = userService.selectByOpenId(open_id);
//        if(user != null){
//            user.setUserNewLogin(new Date());
//            userService.updateById(user);
//        }else{
//            User insert_user = new User();
//            insert_user.setUserHead(userHead);
//            insert_user.setUserName(userName);
//            insert_user.setUserGender(userGender);
//            insert_user.setUserNewLogin(new Date());
//            insert_user.setUserCity(userCity);
//            insert_user.setUserProvince(userProvince);
//            insert_user.setUserOpenid(open_id);
//            System.out.println("insert_user:"+insert_user.toString());
//            // 添加到数据库
//            Boolean flag = userService.insert(insert_user);
//            if(!flag){
//                return new JsonResult(ResultCode.FAIL);
//            }
//        }
        // 封装返回小程序
//        Map<String, String> result = new HashMap<>();
//        result.put("session_key", session_key);
//        result.put("open_id", open_id);
//        return new JsonResult(ResultCode.SUCCESS, result);
        return wxResult;

    }

}


