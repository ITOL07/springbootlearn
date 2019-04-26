package com.atguigu.service;

import com.atguigu.entity.TCoachLessonReg;

public interface TCoachLessonRegService {

    public int insertCoachReg(TCoachLessonReg t);

    public int updateCoachReg(TCoachLessonReg t);

    public TCoachLessonReg seletNumByDate(TCoachLessonReg record);
}
