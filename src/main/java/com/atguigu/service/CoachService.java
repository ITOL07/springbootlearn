package com.atguigu.service;

import com.atguigu.entity.Coach;
import com.atguigu.entity.Course;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface CoachService {
    public Coach getCoachById(String coach_id);

    //根据club_id获取教练
    public List<Coach> getCoachByClubId(String club_id);

    public List<Coach> getCoachByMemId(String memId);

    boolean addCoach(Coach record);

    boolean updateCoach(Coach record);


    public List<Map<Object,Object>> getCoachLessByView(String coachId, Integer status);

    public List<Map<Object,Object>> getCoachLessByView_id(String coachId);

    public List<Map<Object,Object>> getCoachIncomeById(String coachId,Date reg_date);

    public Map<Object,Object> getCoachIncomeSumById(String coachId,Date reg_date);

    public float getCoachIncomePct(Integer cnt,String para_id);

    public List<Map<Object,Object>> getMyMemberId(String coachId);

}