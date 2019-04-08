package com.atguigu.dao;

import com.atguigu.entity.User;

import java.util.Map;

public interface UserMapper {
    int deleteByPrimaryKey(String id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(String id);
    User selectByOpenid(String openID);
    String selectMaxId();

    Map<Object,Object> selectbyUsername(String userName);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    int updateByUserName(User record);

}