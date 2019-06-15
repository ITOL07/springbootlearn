package com.atguigu.service;

import com.atguigu.entity.*;

import java.util.List;
import java.util.Map;

public interface MemberService {

    public Member getMemberById(String memId);


    public List<Map<Object,Object>> getMemberLessById(String memId);

    public List<Map<Object,Object>> selectMemberInfo_1(String coachid);

    public List<Map<Object,Object>> getMemberLessByIdS(String memId,String status);

    public List<Map<Object,Object>> getMemberLessByView(String memId,String coachId, String clubId,String status);
    public List<Map<Object,Object>> getMemberLessByCoachId(String coachId,String status);

    public List<Map<Object,Object>> getMemberLessByViewDate(String memId,String coachId,String status,String reg_date);
    public List<Map<Object,Object>> getMemberLessByCoachIdDate(String coachId,String status,String reg_date);

    boolean addMemberLes(MemberLesson mem);

    public String getMaxId(String memId, String sale_id);

//    public int updateMemberLes(MemberLesson mem,String mem_id,String sale_id,Byte seq_no);

    public int updateMemberLes(MemberLesson mem);

    public Map<Object,Object> getMemCourseSum(String mem_id);

    public Map<String,String> selecctInfoByKcid(MemberLesson record);

    public int selectseqno(MemberLesson record);

    public List<Map<String,String>> selectLessonList(String mem_id,String coach_id);
    public List<Map<String, String>> selectClubList(String course_id);
    public List<Map<Object, Object>> selectMemberInfo(String coachid);
    public List<Map<Object, String>> selectByLesson(String mem_id, String course_id, String club_id, String coach_id);

    public int addMemberCourse(MemberCourse m);
    public String getMaxKcId();
    public int addLess(MemberLesson ml);

    public boolean updateMember(Member mem);

    public boolean addMember(Member record);

    public List<String> getListTimes(String mem_id,String date);
    public Map<Object, Object> getMemCourseInfo(String mem_id,String coachId,String clubId);

    public boolean cancalClass(MemberLesson mem);

    //新增取消课程表记录
    public int addMemLesscancel(TMemberLessonCancel record);
    //更新取消课程表记录
    public int updateMemLesscancel(TMemberLessonCancel record);
    //查询取消课程表记录
    public TMemberLessonCancel getMemLesscancel(TMemberLessonCancelKey key);

    public List<Map<Object,Object>> getMemberLess(String memId,String coachId,String clubId,String status,String reg_date);

    public List<TMemberLessonCancel> getMemberLessCancel(String memId,String coachId,String clubId,String status,String reg_date);
    }
