package com.atguigu.service.impl;

import com.atguigu.dao.ClubMapper;
import com.atguigu.dao.TClubLessonRegMapper;
import com.atguigu.entity.Club;
import com.atguigu.entity.User;
import com.atguigu.service.ClubService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service("clubService")
public class ClubServiceImpl implements ClubService {

    @Resource
    private ClubMapper clubMapper;
    @Resource
    private TClubLessonRegMapper tClubLessonRegMapper;


    public Club getClubById(String userId) {
        return clubMapper.selectByPrimaryKey(userId);
    }

    public boolean addUser(Club record){
        boolean result = false;
        try {
            clubMapper.insertSelective(record);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    public boolean updateClub(Club record){
        boolean result=false;
        try{
            clubMapper.updateByPrimaryKeySelective(record);
            result=true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }
    public String getMaxId(){
        return clubMapper.selectMaxId();
    }

    public List<Map<Object,Object>> getClubLessByViewTime(String clubId,String startTime,Integer status){
        List<Map<Object, Object>> map=clubMapper.selectLessByView(clubId,startTime,status);
        return map;
    }

    public List<Map<Object,Object>> getClubLessByView(String clubId,Integer status){
        List<Map<Object, Object>> map=clubMapper.selectByView(clubId,status);
        return map;
    }

    public List<Map<Object,Object>> getClubLessByView_id(String clubId,String startTime){
        List<Map<Object, Object>> map=clubMapper.selectByView_id(clubId,startTime);
        return map;
    }

    public List<Map<Object,Object>> getCoachIncomeById(String coachId,Date reg_date){
        List<Map<Object,Object>> map=tClubLessonRegMapper.selectByClubId(coachId,reg_date);
        return map;
    }

    public Map<Object,Object> getCoachIncomeSumById(String coachId,Date reg_date){
        Map<Object,Object> map=tClubLessonRegMapper.selectSumByClubId(coachId,reg_date);
        return map;
    }

    public List<Map<Object,Object>> getClubIncomeById(String coachId,Date reg_date){
        List<Map<Object,Object>> map=tClubLessonRegMapper.selectByClubId(coachId,reg_date);
        return map;
    }

    public Map<Object,Object> getClubIncomeSumById(String coachId,Date reg_date){
        Map<Object,Object> map=tClubLessonRegMapper.selectSumByClubId(coachId,reg_date);
        return map;
    }

    public List<Club> getClubInfoByCoachId(String coachId){
        return clubMapper.selectByCoachId(coachId);
    }

    public List<Map<Object,Object>> getMyMemberId(String clubId){
        return clubMapper.selectMyMemId(clubId);
    }
}