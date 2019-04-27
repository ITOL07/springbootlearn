package com.atguigu.service;

import com.atguigu.entity.UserIcons;

import java.util.List;
import java.util.Map;

public interface UserIconsService {

    boolean insertUserIcon(UserIcons icons);

    List<UserIcons> selectByUser(String userid);


    public List<Map<String,String>> queryCoachInfoIcons(String coach_id, Integer type);

    List<UserIcons> selectByType(Integer type);
    List<UserIcons> selectByIdType(String user_id,Integer type);
}
