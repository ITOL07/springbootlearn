package com.atguigu.service.impl;

import com.atguigu.dao.*;
import com.atguigu.entity.*;
import com.atguigu.service.IncomeService;
import com.atguigu.service.OrderService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service("incomeService")
public class IncomeServiceImpl implements IncomeService {

    @Resource
    private OrderDtlMapper order;

    @Resource
    private TCoachLessonRegMapper tCoachLessonRegMapper;

    @Resource
    private TClubLessonRegMapper tClubLessonRegMapper;

    @Resource
    private OrderPayInfoMapper orderPayInfoMapper;

    @Resource
    private TIncomeDtlMapper tIncomeDtlMapper;

    @Resource
    private TIncomeMapper tIncomeMapper;

    @Resource
    private CourseTcMapper courseTcMapper;

    @Override
    public int insertIncom(TIncome t){
        return tIncomeMapper.insertSelective(t);
    }

    @Override
    public int insertIncomDtl(TIncomeDtl t){
        return tIncomeDtlMapper.insertSelective(t);
    }

    @Override
    public TIncomeDtl getIncomDtl(String courseId,String coachId,String clubId,String regDate){
        return tIncomeDtlMapper.selectByPrimaryKey(courseId,coachId,clubId,regDate);
    }
    @Override
    public int updateIncomDtl(TIncomeDtl t){
        return tIncomeDtlMapper.updateSelective(t);
    }
    @Override
    public TIncome getIncom(String userId,String regDate){
        return tIncomeMapper.selectByPrimaryKey(userId,regDate);
    }
    @Override
    public int updateIncom(TIncome t){
        return tIncomeMapper.updateSelective(t);
    }
    @Override
    public Map<String,Object> getCoachLesSum(String courseId,String coachId,String regDate){
        return tIncomeDtlMapper.selectCoachLesSum(courseId,coachId,regDate);
    }
    @Override
    public CourseTc getCourseTcInfo(String courseId){
        return courseTcMapper.selectByPrimaryKey(courseId);
    }
    @Override
    public Map<String,Object> getClubLesSum(String courseId,String clubId,String regDate){
        return tIncomeDtlMapper.selectClubLesSum(courseId,clubId,regDate);
    }

    @Override
    public Map<String,Object> getSum(String userId,String regDate){
        return tIncomeMapper.selectSum(userId,regDate);
    }
}