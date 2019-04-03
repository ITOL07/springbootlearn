package com.atguigu.dao;

import com.atguigu.entity.UserIcons;

import javax.validation.constraints.Size;
import java.util.List;

public interface UserIconsMapper {
    int deleteByPrimaryKey(String iconName);

    int insert(UserIcons record);

    int insertSelective(UserIcons record);

    UserIcons selectByPrimaryKey(String iconName);

    int updateByPrimaryKeySelective(UserIcons record);

    int updateByPrimaryKey(UserIcons record);

    List<UserIcons> selectByUser(String userid);
}