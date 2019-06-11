package com.atguigu.controller;

import com.atguigu.entity.Club;
import com.atguigu.entity.Coach;
import com.atguigu.entity.Member;
import com.atguigu.entity.User;
import com.atguigu.service.ClubService;
import com.atguigu.service.CoachService;
import com.atguigu.service.MemberService;
import com.atguigu.service.UserService1;
import com.atguigu.util.CommParams;
import com.atguigu.util.getSeqNo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.atguigu.util.wxLogin;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import com.alibaba.fastjson.*;

import javax.annotation.Resource;

/**
 * 微信用户登录接口，接收小程序上传的code，返回open_id
 */
@RestController
@RequestMapping("/wxuser")
public class UserApi {

    private Logger logger = LoggerFactory.getLogger(UserApi.class);

    @Autowired
    private UserService1 userService;

    @Resource
    private MemberService memberService;
    @Resource
    private CoachService coachService;
    @Resource
    private ClubService clubService;


    @PostMapping("/login")
    //public JsonResult user_login(
    public Map<Object,Object> user_login(
        @RequestParam("code") String code,
        @RequestParam("type") Integer type,
        @RequestParam("nickName") String nickName,
        @RequestParam("gender") String gender,
        @RequestParam("icon") String icon
    ) {
        // 配置请求参数
        Map<String, String> param = new HashMap<>();

        String appid="";
        String app_secret="";
        logger.info("login type======"+type);
        switch (type){
            case 1:
                    appid=CommParams.APPID_MEM;
                    app_secret=CommParams.APP_SECRET_MEM;
                    break;
            case 2:
                    appid=CommParams.APPID_COACH;
                    app_secret=CommParams.APP_SECRET_COACH;
                    break;
            case 3:
                    appid=CommParams.APPID_CLUB;
                    app_secret=CommParams.APP_SECRET_CLUB;
                    break;
        }

        param.put("appid", appid);
        param.put("secret", app_secret);
        param.put("js_code", code);
        param.put("grant_type", "authorization_code");
        // 发送请求
        logger.info("code is ===="+code);
//        String wxResult = wxLogin.doGet("https://api.weixin.qq.com/sns/jscode2session", param);
        String wxResult = wxLogin.doGet(CommParams.WX_LOGIN_URL, param);

        Map<Object,Object> resMap=new HashMap<>();


        logger.info("wxResult is ===="+wxResult);

        JSONObject jsonObject = JSONObject.parseObject(wxResult);
        // 获取参数返回的
        String session_key = jsonObject.get("session_key").toString();
        String open_id = jsonObject.get("openid").toString();
        resMap.put("openid",open_id);
        // 根据返回的user实体类，判断用户是否是新用户，不是的话，更新最新登录时间，是的话，将用户信息存到数据库
       logger.info("openid is ===="+open_id+"sessio_key====="+session_key);
//        User user = userService.getUserByOpenId(open_id);
        User user = userService.getUser(new User().setOpenId(open_id));
        if(user != null){
            user.setLastLogin(new Date());
            userService.updateUser(user);
            resMap.put("id",user.getId());

            Map<String, String> in_tmp = new HashMap<>();

            in_tmp.put("nickName",nickName);
            in_tmp.put("gender",gender);
            in_tmp.put("icon",icon);
            updateUser(user.getId(),in_tmp);

        }else{
            User insert_user = new User();

            insert_user.setOpenId(open_id);
            insert_user.setLastLogin(new Date());

            String maxId=userService.getMaxId(type);
            logger.info("maxId==="+maxId);
            String id="";
            if(maxId==null){
                id= getSeqNo.getId(12,0,type);
            }
            else{
                int index=Integer.parseInt(maxId.substring(10));
                id= getSeqNo.getId(12,index,type);
            }

            insert_user.setId(id);
            insert_user.setType(type);
            logger.info("insert_user:"+insert_user.toString());
            // 添加到数据库
            Boolean flag = userService.addUser(insert_user);
//            if(!flag){
//                return new JsonResult(ResultCode.FAIL);
//            }
            resMap.put("id",id);
            Map<String, String> in_tmp = new HashMap<>();

            in_tmp.put("nickName",nickName);
            in_tmp.put("gender",gender);
            in_tmp.put("icon",icon);
            insertUser(id,in_tmp);
        }
        // 封装返回小程序

        return resMap;

    }

    public void insertUser(String id,Map<String,String> map){
        String prefix=id.substring(0,2);
        System.out.println("prefix===="+prefix+"   "+prefix.startsWith("JL"));
        if(prefix.startsWith("JL")){
            Coach coach=new Coach();
            coach.setCoachId(id);
            coachService.addCoach(coach);
            logger.info("教练+"+id+"插入教练表成功");
        }else if (prefix.startsWith("HY")){
            Member member=new Member();
            member.setMemId(id)
                    .setIcon(map.get("icon"))
                    .setNickName(map.get("nickName"));

            memberService.addMember(member);
            logger.info("学员+"+id+"插入学员表成功");
        }else if (prefix.startsWith("CD")){
            Club club =new Club();
            club.setClubId(id)
                .setIcon(map.get("icon"))
                ;

            clubService.addUser(club);
            logger.info("场地+"+id+"插入场地表成功");
        }
    }

    public void updateUser(String id,Map<String,String> map){
        String prefix=id.substring(0,2);
        System.out.println("prefix===="+prefix+"   "+prefix.startsWith("JL"));
        if(prefix.startsWith("JL")){
            Coach coach=new Coach();
            coach.setCoachId(id)
                .setIcon(map.get("icon"));
            coachService.updateCoach(coach);
            logger.info("教练+"+id+"更新教练表成功");
        }else if (prefix.startsWith("HY")){
            Member member=new Member();
            member.setMemId(id)
                    .setIcon(map.get("icon"))
                    .setNickName(map.get("nickName"))
            .setSex(map.get("gender"));

            memberService.updateMember(member);
            logger.info("学员+"+id+"更新学员表成功");
        }else if (prefix.startsWith("CD")){
            Club club =new Club();
            club.setClubId(id)
                .setPhoto(map.get("icon"));

            clubService.updateClub(club);
            logger.info("场地+"+id+"更新场地表成功");
        }
    }
}


