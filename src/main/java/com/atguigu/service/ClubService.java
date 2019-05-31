package com.atguigu.service;

import com.atguigu.entity.Club;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface ClubService {
        public Club getClubById(String userId);

        boolean addUser(Club record);

        public String getMaxId();

        public List<Map<Object,Object>> getClubLessByViewTime(String clubId,String startTime,Integer status);

        public List<Map<Object,Object>> getClubLessByView(String clubId,Integer status);
        public List<Map<Object,Object>> getClubLessByView_id(String clubId,String startTime);

        public List<Map<Object,Object>> getClubIncomeById(String coachId,Date reg_date);

        public Map<Object,Object> getClubIncomeSumById(String coachId,Date reg_date);
        public boolean updateClub(Club record);
        public List<Club> getClubInfoByCoachId(String coachId);
}
