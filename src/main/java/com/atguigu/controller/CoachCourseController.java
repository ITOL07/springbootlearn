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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/mydb")
public class CoachCourseController {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Resource
    private CoachCourseService coachCourseService;

    @RequestMapping("/showCoachCourse")
    @ResponseBody
    public List<CoachCourse> getUserById(HttpServletRequest request, Model model){
        String userId = request.getParameter("id");
        System.out.println("id====="+userId);
        List<CoachCourse> c = this.coachCourseService.getCoachCourseById(userId);
        return c;
    }
//    @RequestMapping("/showCoachCourse1")
//    @ResponseBody
//    public List<Map<Object,Object>> selecttest(HttpServletRequest request, Model model){
//
//        String userId = request.getParameter("id");
//        System.out.println("id====="+userId);
//        List<Map<Object,Object>> list = this.coachCourseService.selecttest(userId);
//        for (Map<Object, Object> list1: list){
//            System.out.println("id return ====:"+list1.get("coach_Id"));
//        }
//        return list;
//    }
//
//    @RequestMapping("/showCoachCourse2")
//    @ResponseBody
//    public CoachCourse selecttest1(HttpServletRequest request, Model model){
//        String userId = request.getParameter("id");
//        System.out.println("id====="+userId);
//        CoachCourse c = this.coachCourseService.selecttest1(userId);
//        return c;
//    }
}
