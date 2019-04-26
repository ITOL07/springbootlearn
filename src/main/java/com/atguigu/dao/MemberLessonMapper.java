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
    List<Map<Object, String>> selectMemberInfo(String coachid);
    List<Map<Object, Object>> selectMemberInfo_1(String coachid);
    List<Map<Object, String>> selectByLesson(@Param("mem_id") String mem_id,
                                             @Param("course_id")String course_id,
                                             @Param("club_id") String club_id,
                                             @Param("coach_id")String coach_id);
    List<Map<Object, Object>> selectByIdS(@Param("memId") String memId,@Param("status")String status);

    List<Map<Object, Object>> selectByView(@Param("memId") String memId,@Param("status")String status);

    String selectMaxId(@Param("memId") String memId, @Param("saleId") String sale_id);

//    int update(MemberLesson record,@Param("memId") String memId,@Param("saleId") String sal_id,@Param("seqNo") Byte seq_no);
    List<Map<String,String>> selectLessonList(String mem_id);

    List<Map<String,String>> selectClubList(String course_id);

    int update(MemberLesson record);

    boolean updatelesson(MemberLesson record);

    boolean updateStatus(MemberLesson record);

    byte selectseqno(MemberLesson record);
}
