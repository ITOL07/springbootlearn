package com.atguigu.controller;

import com.atguigu.entity.Club;
import com.atguigu.entity.Course;
import com.atguigu.entity.CourseInfo;
import com.atguigu.entity.User;
import com.atguigu.service.UserService1;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * 万能的helloWorld，入门
 */
@RestController
//@Component
//@Configuration
//@EnableScheduling
public class helloController {
//
    @Resource
    UserService1 userService1;

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
    public User test1(User user){

        System.out.println(user.toString());
        return userService1.getUser(user);

    }
}

