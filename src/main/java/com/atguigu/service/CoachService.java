package com.atguigu.service;

import com.atguigu.entity.Coach;
import com.atguigu.entity.Course;

import java.util.List;

public interface CoachService {
    public Coach getCoachById(String coach_id);

    //根据club_id获取教练
    public List<Coach> getCoachByClubId(String club_id);

    public List<Coach> getCoachByMemId(String memId);

    boolean addCoach(Coach record);

    boolean updateCoach(Coach record);

}