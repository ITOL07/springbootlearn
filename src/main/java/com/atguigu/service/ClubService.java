package com.atguigu.service;

import com.atguigu.entity.Club;

public interface ClubService {
        public Club getClubById(String userId);

        boolean addUser(Club record);

        public String getMaxId();
}
