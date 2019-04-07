package com.atguigu.service.impl;


import com.atguigu.dao.ClubMapper;
import com.atguigu.dao.CoachCourseMapper;
import com.atguigu.entity.Club;
import com.atguigu.entity.CoachCourse;
import com.atguigu.service.CoachCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service("coachCourseService")
public class CoachCourseServiceImpl implements CoachCourseService{
    @Resource
    private CoachCourseMapper coachCourseMapper;


    public List<CoachCourse> getCoachCourseById(String clubId) {
//        List<CoachCourse> ls = new List<CoachCourse>
        return coachCourseMapper.selectByPrimaryKey(clubId);
    }

//    @Override
//    public List<Map<Object, Object>> selecttest(int coachId) {
//        List<Map<Object, Object>> map = coachCourseMapper.selecttest(coachId);
//        return map;
//    }
//
//    @Override
//    public CoachCourse selecttest1(int coachId) {
//        return coachCourseMapper.selecttest1(coachId);
//    }
}
