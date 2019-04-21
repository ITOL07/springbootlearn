package com.atguigu.service.impl;

import com.atguigu.dao.*;
import com.atguigu.entity.Coach;
import com.atguigu.entity.CoachCourse;
import com.atguigu.entity.Course;
import com.atguigu.entity.TCoachLessonReg;
import com.atguigu.service.CoachService;
import com.atguigu.service.CourseService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service("coachService")
public class CoachServiceImpl implements CoachService {

    @Resource
    private CoachMapper coach;
    @Resource
    private CoachLessonMapper coachLess;
    @Resource
    TCoachLessonRegMapper tCoachLessonRegMapper;
    @Resource
    TPubParamMapper tPubParamMapper;


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

    public List<Map<Object,Object>> getCoachLessByView(String memId, Integer status){
        List<Map<Object, Object>> map=coachLess.selectByView(memId,status);
        return map;
    }

    public List<Map<Object,Object>> getCoachLessByView_id(String coachId){
        List<Map<Object, Object>> map=coachLess.selectByView_id(coachId);
        return map;
    }

    public List<Map<Object,Object>> getCoachIncomeById(String coachId,Date reg_date){
        List<Map<Object,Object>> map=tCoachLessonRegMapper.selectByCoachId(coachId,reg_date);
        return map;
    }

    public Map<Object,Object> getCoachIncomeSumById(String coachId,Date reg_date){
        Map<Object,Object> map=tCoachLessonRegMapper.selectSumByCoachId(coachId,reg_date);
        return map;
    }

    public float getCoachIncomePct(Integer cnt,String para_id){
        return tPubParamMapper.selectPct(cnt,para_id);
    }
}