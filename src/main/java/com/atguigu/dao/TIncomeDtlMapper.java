package com.atguigu.dao;

import com.atguigu.entity.TIncomeDtl;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

public interface TIncomeDtlMapper {
    int insert(TIncomeDtl record);

    int insertSelective(TIncomeDtl record);

    TIncomeDtl selectByPrimaryKey(@Param("courseId") String courseId, @Param("coachId") String coachId,@Param("clubId") String clubId,@Param("regDate") String regDate);

    int updateSelective(TIncomeDtl record);
    Map<String,Object> selectCoachLesSum(@Param("courseId") String courseId, @Param("coachId") String coachId, @Param("regDate") String regDate);
    Map<String,Object> selectClubLesSum(@Param("courseId") String courseId, @Param("clubId") String clubId, @Param("regDate") String regDate);

}