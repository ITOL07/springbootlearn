package com.atguigu.service;

import com.atguigu.entity.Course;

import java.util.List;
import java.util.Map;

public interface CourseService {
    public Course getCourseById(String courseId);

    //根据club_id获取课程
    public List<Course> getCourseByClubId(String club_id,String try_flag);

//    public Course getOrderByMemId(String memId);

    boolean addCourse(Course record);

    boolean updateCourse(Course record);

    public String getMaxId();

    public boolean delCourse(String course_id);

    Course selectByPrimaryKey(String course_id);

    List<Map<String,String>> getClubOrCoach(String clubId,String coachId,String courseType);
    
    public Course getCourseId(String clubId,String coachId,String courseType);

    public Map<String,String> getCoursePrice(String Id,String courseType);
}