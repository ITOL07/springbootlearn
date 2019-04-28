package com.atguigu.controller;

import com.atguigu.service.UserIconsService;
import com.atguigu.util.CommParams;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/userIcons")
public class UserIconsController {

    private static final Logger logger = LoggerFactory.getLogger(UserIconsController.class);

    @Resource
    UserIconsService userIconsService;

    /***
     * @param coach_id 教练id
     * @param type 图片类型
     * queryCoachInfoIcons 查询教练不同类型的证件照
     */
    @ResponseBody
    @RequestMapping("/queryCoachInfoIcons")
    public Map<String,List<String>> queryCoachInfoIcons(
            @RequestParam("coach_id") String coach_id,
            @RequestParam("type") Integer type) {
        System.out.println("coach_id ====" + coach_id + "；type=====" + type);
        Map<String,List<String>> map = new HashMap<>();
        List<String> list1 = new ArrayList<>();
        List<String> list2 = new ArrayList<>();
        List<Map<String, String>> list = userIconsService.queryCoachInfoIcons(coach_id, type);
        logger.info("list size :"+list.size());
        for(Map<String, String> map1 :list){
            list1.add(CommParams.WEB_URL+map1.get("icon_url").replaceAll("/app/test",""));
            list2.add(map1.get("icon_name"));
        }
        map.put("icons_url",list1);
        map.put("icons_name",list2);
        return map;

    }

    /**
     * 根据coach_id获取教练证件信息
     * @param coach_id 教练ID
     */
    @RequestMapping("/getCoachPapersNum")
    public Map<String, Object>  getCoachPapersNum(@RequestParam("coach_id") String coach_id, HttpServletResponse response){
        logger.info("coach_id :"+ coach_id);
        Map<String,Object> map = new HashMap<>();
        List<Map<String, Integer>> coachPapersNum = userIconsService.getCoachPapersNum(coach_id);
        logger.info("=======================");
        for(Map<String, Integer> map1 :coachPapersNum ){
            logger.info(map1.get("type")+"");
            if(map1.get("type") == 1){
                map.put("credentials",map1.get("num"));
            }else if(map1.get("type") == 2){
                map.put("cases",map1.get("num"));
            }else if(map1.get("type") == 3){
                map.put("albums",map1.get("num"));
            }
        }
        return map;
    }
}
