package com.atguigu.service;

import com.atguigu.entity.Course;

import java.util.List;

public interface CourseService {
    public Course getCourseById(String courseId);

    //根据club_id获取课程
    public List<Course> getCourseByClubId(String club_id,String try_flag);

//    public Course getOrderByMemId(String memId);

    boolean addOrder(Course record);

    boolean updateOrder(Course record);

}