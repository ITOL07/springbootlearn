package com.atguigu.controller;

import com.alibaba.fastjson.JSONObject;
import com.atguigu.entity.User;
import com.atguigu.service.UserService1;
import com.atguigu.util.CommonRpc;
import com.atguigu.util.getSeqNo;
import com.atguigu.util.wxLogin;
//import com.google.gson.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 手机用户登录模块，包括
 * 1：用户登录
 * 2：用户注册
 * 3：重置密码
 * 4：查看是否已注册
 * 5：获取手机验证码
 */
@RestController
@RequestMapping("/user")
public class Userlogin {

    @Autowired
    private UserService1 userService;


    @PostMapping("/login")
    //public JsonResult user_login(
    public JSONObject user_login(
        @RequestParam("phoneNo") String userName,
        @RequestParam("passwd") String passwd
    ) {

        Map<String, String> param = new HashMap<>();
        JSONObject result= new JSONObject();
        // 根据返回的user实体类，判断用户是否是新用户，不是的话，更新最新登录时间，是的话，将用户信息存到数据库
       System.out.println("username ===="+userName+"========passwd====="+passwd);
       Map<Object,Object> map = userService.getUserByName(userName);


        if(map != null){
            String dbpasswd=map.get("password").toString();
            System.out.println("dbpasswd==="+dbpasswd);
            System.out.println(dbpasswd.equals(passwd));
            if(!dbpasswd.equals(passwd)){
                result.put("errono","-2");
                result.put("errocode","密码错误");
                return result;
            }
            String id=map.get("id").toString();
            User user = userService.getUserById(id);
            user.setLastLogin(new Date());
            user.setChn(new Byte("1"));

            userService.updateUser(user);
            result.put("errono","0");
            result.put("errocode","ok");
            result.put("id",map.get("id").toString());

        }else{
            result.put("errono","-1");
            result.put("errocode","无此用户");
        }
        // 封装返回小程序
//        Map<String, String> result = new HashMap<>();
////        result.put("session_key", session_key);
//        result.put("open_id", open_id);
        return result;
    }

    @RequestMapping("/signup")
    public JSONObject getphone(
            @RequestParam("phoneNo") String phoneNo,
            @RequestParam("passwd") String passwd,
            @RequestParam("type") Integer type
    ){

        Map<String, String> param = new HashMap<>();
        JSONObject result= new JSONObject();
        // 根据返回的user实体类，判断用户是否是新用户，不是的话，更新最新登录时间，是的话，将用户信息存到数据库
        System.out.println("username ===="+phoneNo+"========passwd====="+passwd);
        Map<Object,Object> map = userService.getUserByName(phoneNo);
        int index=0;

        if(map != null){
            result.put("errono","0");
            result.put("errocode","用户已存在,请登录");  //用户已存在
        }else{
            User insert_user = new User();
            insert_user.setLastLogin(new Date());

            String maxId=userService.getMaxId(type);
            System.out.println("maxId==="+maxId);
            if(maxId!=null) {
                index = Integer.parseInt(maxId.substring(8));
            }
            String id= getSeqNo.getId(12,index,type);
            insert_user.setId(id);
            insert_user.setUserName(phoneNo);
            insert_user.setPassword(passwd);
            System.out.println("insert_user======"+insert_user.toString());
            // 添加到数据库

            Boolean flag = userService.addUser(insert_user);
            result.put("errocode","注册成功，请登录");

        }
        // 封装返回小程序
//        Map<String, String> result = new HashMap<>();
////        result.put("session_key", session_key);
//        result.put("open_id", open_id);
        return result;
    }

    @RequestMapping("/forgetPass")
    public JSONObject forgetPass(
            @RequestParam("phoneNo") String phoneNo,
            @RequestParam("passwd") String passwd
    ){

        Map<String, String> param = new HashMap<>();
        JSONObject result= new JSONObject();
        // 根据返回的user实体类，判断用户是否注册过，不是的话，提示先注册，是的话，将用户信息存到数据库
        System.out.println("username ===="+phoneNo+"========passwd====="+passwd);
        Map<Object,Object> map = userService.getUserByName(phoneNo);


        if(map == null){
            result.put("errono","-1");  //用户不存在
            result.put("errocode","用户不存在,请先注册");
        }else{
            User user = new User();
            user.setLastLogin(new Date());
            user.setPassword(passwd);

            user.setUserName(phoneNo);
            user.setPassword(passwd);
            System.out.println("user======"+user.toString());
            // 添加到数据库

            Boolean flag = userService.updateUser1(user);
            result.put("errocode","密码重置成功，请登录");

        }
        // 封装返回小程序
//        Map<String, String> result = new HashMap<>();
////        result.put("session_key", session_key);
//        result.put("open_id", open_id);
        return result;
    }

    @RequestMapping("/isReg")
    public JSONObject isRegistered(
            @RequestParam("phoneNo") String phoneNo
    ){

        Map<String, String> param = new HashMap<>();
        JSONObject result= new JSONObject();
        // 根据返回的user实体类，判断用户是否注册过，不是的话，提示先注册，是的话，将用户信息存到数据库
        System.out.println("username ===="+phoneNo);
        Map<Object,Object> map = userService.getUserByName(phoneNo);


        if(map == null){
            result.put("errono","-1");  //用户不存在
            result.put("errocode","用户不存在");
        }else{
            result.put("errono","0");  //用户不存在
            result.put("errocode","用户存在");
        }
        // 封装返回小程序

        return result;
    }

    @RequestMapping("/getvcode")
    public JSONObject signup(
            @RequestParam("phoneNo") String phoneNo
//            ,
//            @RequestParam("optType") String optType
    ){
        CommonRpc crp = new CommonRpc();

//        SMS_152471207  身份验证验证码
//        SMS_152471206  登录确认验证码
//        SMS_152471205  登录异常验证码
//        SMS_152471204  用户注册验证码
//        SMS_152471203  修改密码验证码
//        SMS_152471202  信息变更验证码
//        switch (optType)

        String optCode="SMS_152471207";
//
        JSONObject jsono= new JSONObject();
        jsono.put("vericode",crp.sendMsgCode(phoneNo,optCode));
        jsono.put("errocode","0");

//        return crp.sendMsgCode(phoneNo,optCode);
        return jsono;
    }

    /**
     *
     * @param phoneNo
     * @param open_id
     * @return
     * 用手机号登录的用户，绑定微信号
     */
    @RequestMapping("/bindWx")
    public JSONObject bindWx(
            @RequestParam("phoneNo") String phoneNo,
            @RequestParam("open_id") String open_id
    ){

        Map<String, String> param = new HashMap<>();
        JSONObject result= new JSONObject();
        // 根据返回的user实体类，判断用户是否注册过，不是的话，提示先注册，是的话，将用户信息存到数据库
        System.out.println("username ===="+phoneNo+"========open_id====="+open_id);
        Map<Object,Object> map = userService.getUserByName(phoneNo);


        if(map == null){
            result.put("errono","-1");  //用户不存在
            result.put("errocode","用户不存在,请先注册");
        }else{
            User user = new User();
            user.setLastLogin(new Date());

            user.setUserName(phoneNo);
            user.setOpenId(open_id);
            System.out.println("user======"+user.toString()+"+++++++open_id====="+open_id);
            // 添加到数据库

            Boolean flag = userService.updateUser1(user);
            result.put("errocode","绑定微信账号成功");

        }
        // 封装返回小程序
//        Map<String, String> result = new HashMap<>();
////        result.put("session_key", session_key);
//        result.put("open_id", open_id);
        return result;
    }

    /**
     *
     * @param phoneNo
     * @param open_id
     * @return
     * 用微信登录的用户，绑定手机号
     */
    @RequestMapping("/bindPhone")
    public JSONObject bindPhone(
            @RequestParam("phoneNo") String phoneNo,
            @RequestParam("open_id") String open_id
    ){

        Map<String, String> param = new HashMap<>();
        JSONObject result= new JSONObject();
        // 根据返回的user实体类，判断用户是否注册过，不是的话，提示先注册，是的话，将用户信息存到数据库
        System.out.println("username ===="+phoneNo+"========open_id====="+open_id);
        User user = userService.getUserByOpenId(open_id);


        if(user == null){
            result.put("errono","-1");  //用户不存在
            result.put("errocode","用户不存在,请先注册");
        }else if (user.getUserName()!=null){
            result.put("errono","-2");  //用户不存在
            result.put("errocode","手机号已被绑定,无须重复绑定");
        }
        else{
            User user_tmp = new User();
            user_tmp.setLastLogin(new Date());

            user_tmp.setUserName(phoneNo);
            //初始密码6个0
            user_tmp.setPassword("670b14728ad9902aecba32e22fa4f6bd");
            user_tmp.setOpenId(open_id);
            System.out.println("user======"+user_tmp.toString()+"+++++++open_id====="+open_id);
            // 添加到数据库

            Boolean flag = userService.updateUserByOpenid(user_tmp);
            result.put("errocode","您现在可以通过手机号登录，初始密码为000000");

        }
        // 封装返回小程序
//        Map<String, String> result = new HashMap<>();
////        result.put("session_key", session_key);
//        result.put("open_id", open_id);
        return result;
    }


}


