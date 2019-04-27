package com.atguigu.controller;

import com.atguigu.service.UserIconsService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/userIcons")
public class UserIconsController {

    @Resource
    UserIconsService userIconsService;

    /***
     * @param coach_id 教练id
     * @param type 图片类型
     * queryCoachInfoIcons 查询教练不同类型的证件照
     */
    @ResponseBody
    @RequestMapping("/queryCoachInfoIcons")
    public List<Map<String, String>> queryCoachInfoIcons(
            @RequestParam("coach_id") String coach_id,
            @RequestParam("type") Integer type) {
        System.out.println("coach_id ====" + coach_id + "；type=====" + type);

        return userIconsService.queryCoachInfoIcons(coach_id,type);

    }
}
