package com.atguigu.dao;

import com.atguigu.entity.MemberLesson;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface MemberLessonMapper {
    int insert(MemberLesson record);

    int insertSelective(MemberLesson record);

    Map<String,String> selecctInfoByKcid(MemberLesson record);

    List<Map<Object, Object>> selectById(String memId);

    List<Map<Object, Object>> selectMemberInfo_1(String coachid);

    List<Map<Object, Object>> selectByIdS(@Param("memId") String memId, @Param("status")String status);

    List<Map<Object, Object>> selectByCoachId(@Param("coachId") String coachId, @Param("status")String status);
    List<Map<Object, Object>> selectByCoachIdDate(@Param("coachId") String coachId, @Param("status")String status,@Param("reg_date") String reg_date);

    List<Map<Object, Object>> selectByClubId(@Param("clubId") String clubId, @Param("status")String status);
    List<Map<Object, Object>> selectByClubIdDate(@Param("clubId") String clubId, @Param("status")String status,@Param("reg_date") String reg_date);

    List<Map<Object, Object>> selectByView(@Param("memId") String memId,@Param("coachId") String coachId,@Param("status")String status);
    List<Map<Object, Object>> selectByViewDate(@Param("memId") String memId,@Param("coachId") String coachId,@Param("status")String status,@Param("reg_date") String reg_date);

    String selectMaxId(@Param("memId") String memId, @Param("saleId") String sale_id);

//    int update(MemberLesson record,@Param("memId") String memId,@Param("saleId") String sal_id,@Param("seqNo") Byte seq_no);
    int update(MemberLesson record);

    boolean updatelesson(MemberLesson record);

    boolean updateStatus(MemberLesson record);

    int selectseqno(MemberLesson record);
    List<Map<String,String>> selectLessonList(@Param("mem_id") String mem_id,
                                              @Param("coach_id")String coach_id);

    List<Map<String,String>> selectClubList(String course_id);

    List<Map<Object, String>> selectMemberInfo(String coachid);
    List<Map<Object, String>> selectByLesson(@Param("mem_id") String mem_id,
                                             @Param("course_id")String course_id,
                                             @Param("club_id") String club_id,
                                             @Param("coach_id")String coach_id);
    List<String> getListTimes(@Param("mem_id") String mem_id,
                              @Param("date")String date);

}
