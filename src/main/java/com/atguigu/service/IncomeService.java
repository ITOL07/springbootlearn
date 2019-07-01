package com.atguigu.service;

import com.atguigu.entity.*;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface IncomeService {

    /**
     *  插入提成计算结果表
     * @param t
     * @return
     */
    public int insertIncom(TIncome t);

    //插入提成计算记录表
    public int insertIncomDtl(TIncomeDtl t);

    public TIncomeDtl getIncomDtl(String courseId,String regDate);

    public int updateIncomDtl(TIncomeDtl t);

    public TIncome getIncom(String userId,String regDate);

    public int updateIncom(TIncome t);

    public Map<String,Object> getCoachLesSum(String courseId,String coachId,String regDate);

    public CourseTc getCourseTcInfo(String courseId);

    public Map<String,Object> getClubLesSum(String courseId,String clubId,String regDate);

    public Map<String,Object> getSum(String userId,String regDate);
}