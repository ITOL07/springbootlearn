package com.atguigu.service.impl;

import com.atguigu.dao.CoachMapper;
import com.atguigu.dao.CourseMapper;
import com.atguigu.entity.Coach;
import com.atguigu.entity.CoachCourse;
import com.atguigu.entity.Course;
import com.atguigu.service.CoachService;
import com.atguigu.service.CourseService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("coachService")
public class CoachServiceImpl implements CoachService {

    @Resource
    private CoachMapper coach;


    public Coach getCoachById(String coach_id) {
        return coach.selectByPrimaryKey(coach_id);
    }

    public List<Coach> getCoachByClubId(String club_id) {
        return coach.selectByClubId(club_id);
    }

    public boolean addCoach(Coach record){
        boolean result = false;
        try {
            coach.insertSelective(record);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    public boolean updateCoach(Coach record) {
        boolean result = false;
        try {
            coach.updateByPrimaryKeySelective(record);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }
    public List<Coach> getCoachByMemId(String memId){
        return null;
    }

}