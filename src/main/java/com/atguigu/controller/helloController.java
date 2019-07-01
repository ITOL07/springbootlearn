package com.atguigu.controller;

import com.atguigu.entity.*;
import com.atguigu.service.IncomeService;
import com.atguigu.service.UserService1;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.Map;

/**
 * 万能的helloWorld，入门
 */
@RestController
//@Component
//@Configuration
//@EnableScheduling
public class helloController {
//
    private Logger logger = LoggerFactory.getLogger(helloController.class);
    @Resource
    UserService1 userService1;

    @Resource
    IncomeService incomeService;

    @ResponseBody
    @RequestMapping("/payResult")
    public void getUserById(HttpServletRequest request, Model model){
        //int userId = Integer.parseInt(request.getParameter("id"));
        System.out.println("request====="+request.getQueryString());

    }

    @ResponseBody
    @RequestMapping("/test")
    public void test(Club club, CourseInfo courseInfo,Course course){

        System.out.println(club.toString());
        System.out.println(courseInfo.toString());
        System.out.println(course.toString());
    }



//    @Scheduled(cron = "* * 1 * * ?")
//    private void configureTasks() {
//        System.err.println("执行静态定时任务时间: ");
//    }

    @ResponseBody
    @RequestMapping("/test1")
    public void test1(User user){

        Map<String,Object> map = incomeService.getCoachLesSum("0004","JL201904200003","2019-07-02");
        logger.info("map" +map.toString());
        BigDecimal KT_CNT=(BigDecimal)map.get("kt_sum");
        CourseTc tc = incomeService.getCourseTcInfo("0004");
        logger.info("tc info ==="+tc.toString());

//        Map<String,Object> clublesSummap = incomeService.getClubLesSum("0004","CD201904200001","2019-07-02");
        BigDecimal XT_CNT=(BigDecimal)(map.get("xt_sum"));
        logger.info("XT_CNT===="+XT_CNT+"map.get(\"xt_sum\")=="+map.get("xt_sum"));
    }
}

