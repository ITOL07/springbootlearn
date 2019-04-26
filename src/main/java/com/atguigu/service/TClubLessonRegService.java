package com.atguigu.service;

import com.atguigu.entity.TClubLessonReg;

public interface TClubLessonRegService {

    public int insertClubReg(TClubLessonReg t);

    public int updateClubReg(TClubLessonReg t);

    public TClubLessonReg seletNumByDate(TClubLessonReg record);
}
