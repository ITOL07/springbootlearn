package com.atguigu.service.impl;

import com.atguigu.dao.CourseMapper;
import com.atguigu.entity.Course;
import com.atguigu.service.CourseService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("courseService")
public class CourseServiceImpl implements CourseService {

    @Resource
    private CourseMapper course;


    public Course getCourseById(String course_id) {
        return course.selectByPrimaryKey(course_id);
    }

    public List<Course> getCourseByClubId(String club_id,String try_flag) {
        return course.selectByClubId(club_id,try_flag);
    }

    public boolean addCourse(Course record){
        boolean result = false;
        try {
            course.insertSelective(record);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    public boolean delCourse(String course_id){
        boolean result = false;
        try {
            course.deleteByPrimaryKey(course_id);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    public boolean updateCourse(Course record) {
        boolean result = false;
        try {
            course.updateByPrimaryKeySelective(record);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    @Override
    public Course selectByPrimaryKey(String course_id) {
        return course.selectByPrimaryKey(course_id);
    }
    @Override
    public String getMaxId(){
        return course.selectMaxCourseId();

    }

}