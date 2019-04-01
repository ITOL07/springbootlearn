package com.atguigu.controller;

import com.atguigu.entity.CoachCourse;
import com.atguigu.service.CoachCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/mydb")
public class CoachCourseController {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Resource
    private CoachCourseService coachCourseService;

    @RequestMapping("/showCoachCourse")
    @ResponseBody
    public CoachCourse getUserById(HttpServletRequest request, Model model){
        int userId = Integer.parseInt(request.getParameter("id"));
        System.out.println("id====="+userId);
        CoachCourse c = this.coachCourseService.getCoachCourseById(userId);
        return c;
    }
}
