package com.atguigu.service.impl;


import com.atguigu.dao.ClubMapper;
import com.atguigu.dao.CoachCourseMapper;
import com.atguigu.entity.Club;
import com.atguigu.entity.CoachCourse;
import com.atguigu.service.CoachCourseService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("coachCourseService")
public class CoachCourseServiceImpl implements CoachCourseService{
    @Resource
    private CoachCourseMapper coachCourseMapper;


    public CoachCourse getCoachCourseById(int clubId) {
        return coachCourseMapper.selectByPrimaryKey(clubId);
    }
}
