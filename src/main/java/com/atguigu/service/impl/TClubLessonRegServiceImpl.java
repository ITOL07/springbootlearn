package com.atguigu.service.impl;

import com.atguigu.dao.TClubLessonRegMapper;
import com.atguigu.entity.TClubLessonReg;
import com.atguigu.service.TClubLessonRegService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("TClubLessonRegService")
public class TClubLessonRegServiceImpl implements TClubLessonRegService {
    @Resource
    private TClubLessonRegMapper tClubLessonRegMapper;

    @Override
    public int insertClubReg(TClubLessonReg t) {
        return tClubLessonRegMapper.insertSelective(t);
    }

    @Override
    public int updateClubReg(TClubLessonReg t) {
        return tClubLessonRegMapper.updateByPrimaryKeySelective(t);
    }

    @Override
    public TClubLessonReg seletNumByDate(TClubLessonReg record) {
        return tClubLessonRegMapper.seletNumByDate(record);
    }
}
