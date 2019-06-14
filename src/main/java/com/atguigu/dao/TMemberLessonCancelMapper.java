package com.atguigu.dao;

import com.atguigu.entity.TMemberLessonCancel;
import com.atguigu.entity.TMemberLessonCancelKey;

public interface TMemberLessonCancelMapper {
    int deleteByPrimaryKey(TMemberLessonCancelKey key);

    int insert(TMemberLessonCancel record);

    int insertSelective(TMemberLessonCancel record);

    TMemberLessonCancel selectByPrimaryKey(TMemberLessonCancelKey key);

    int updateByPrimaryKeySelective(TMemberLessonCancel record);

    int updateByPrimaryKey(TMemberLessonCancel record);
}