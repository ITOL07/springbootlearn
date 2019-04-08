package com.atguigu.dao;

import com.atguigu.entity.MemberLesson;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface MemberLessonMapper {
    int insert(MemberLesson record);

    int insertSelective(MemberLesson record);

    List<Map<Object, Object>> selectById(String memId);
    List<Map<Object, Object>> selectByIdS(@Param("memId") String memId,@Param("status")String status);

    List<Map<Object, Object>> selectByView(@Param("memId") String memId,@Param("status")String status);

    String selectMaxId(@Param("memId") String memId, @Param("saleId") String sale_id);

//    int update(MemberLesson record,@Param("memId") String memId,@Param("saleId") String sal_id,@Param("seqNo") Byte seq_no);

    int update(MemberLesson record);
}
