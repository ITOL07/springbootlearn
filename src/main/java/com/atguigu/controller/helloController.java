package com.atguigu.controller;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * 万能的helloWorld，入门
 */
//@Controller
@Component
@Configuration
@EnableScheduling
public class helloController {

    @ResponseBody
    @RequestMapping("/payResult")
    public void getUserById(HttpServletRequest request, Model model){
        //int userId = Integer.parseInt(request.getParameter("id"));
        System.out.println("request====="+request.getQueryString());

    }

//    @Scheduled(cron = "* * 1 * * ?")
//    private void configureTasks() {
//        System.err.println("执行静态定时任务时间: ");
//    }
}

