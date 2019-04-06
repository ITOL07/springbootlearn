package com.atguigu.dao;

import com.atguigu.entity.Member;

public interface MemberMapper {
    int deleteByPrimaryKey(String memId);

    int insert(Member record);

    int insertSelective(Member record);

    Member selectByPrimaryKey(String memId);

    int updateByPrimaryKeySelective(Member record);

    int updateByPrimaryKey(Member record);
}