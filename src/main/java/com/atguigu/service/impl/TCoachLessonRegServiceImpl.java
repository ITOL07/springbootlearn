package com.atguigu.service.impl;

import com.atguigu.dao.TCoachLessonRegMapper;
import com.atguigu.entity.TCoachLessonReg;
import com.atguigu.service.TCoachLessonRegService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("TCoachLessonRegService")
public class TCoachLessonRegServiceImpl  implements TCoachLessonRegService {
    @Resource
    private TCoachLessonRegMapper tCoachLessonRegMapper;

    @Override
    public int insertCoachReg(TCoachLessonReg t) {
        return tCoachLessonRegMapper.insertSelective(t);
    }

    @Override
    public int updateCoachReg(TCoachLessonReg t) {
        return tCoachLessonRegMapper.updateByPrimaryKeySelective(t);
    }

    @Override
    public TCoachLessonReg seletNumByDate(TCoachLessonReg record) {
        return tCoachLessonRegMapper.seletNumByDate(record);
    }
}
