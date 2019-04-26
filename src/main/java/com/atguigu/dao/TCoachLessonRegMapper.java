package com.atguigu.dao;

import com.atguigu.entity.TCoachLessonReg;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface TCoachLessonRegMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TCoachLessonReg record);

    int insertSelective(TCoachLessonReg record);

    TCoachLessonReg selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TCoachLessonReg record);

    int updateByPrimaryKey(TCoachLessonReg record);

    List<Map<Object,Object>> selectByCoachId(@Param("coachId") String coachId,@Param("reg_date") Date reg_date);
    Map<Object,Object> selectSumByCoachId(@Param("coachId") String coachId,@Param("reg_date") Date reg_date);

    TCoachLessonReg seletNumByDate(TCoachLessonReg record);

}