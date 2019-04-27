package com.atguigu.dao;

import com.atguigu.entity.UserIcons;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface UserIconsMapper {
    int deleteByPrimaryKey(String iconName);

    int insert(UserIcons record);

    int insertSelective(UserIcons record);

    UserIcons selectByPrimaryKey(String iconName);

    int updateByPrimaryKeySelective(UserIcons record);

    int updateByPrimaryKey(UserIcons record);

    List<UserIcons> selectByUser(String userid);

    List<Map<String,String>> queryCoachInfoIcons(@Param("coach_id") String coach_id,@Param("type") Integer type);
    List<UserIcons> selectByType(Integer type);

    List<UserIcons> selectByIdType(@Param("user_id") String user_id,@Param("type") Integer type);
}