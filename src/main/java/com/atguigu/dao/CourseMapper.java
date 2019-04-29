package com.atguigu.dao;

import com.atguigu.entity.Course;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface CourseMapper {
    int deleteByPrimaryKey(String courseId);

    int insert(Course record);

    int insertSelective(Course record);

    Course selectByPrimaryKey(String courseId);

    List<Course> selectByClubId(@Param("clubId") String clubId, @Param("try_flag") String try_flag);
    List<Course> selectByCoachId(@Param("coachId") String coachId, @Param("try_flag") String try_flag);

    int updateByPrimaryKeySelective(Course record);

    int updateByPrimaryKey(Course record);

    String selectMaxCourseId();

    List<Map<String,String>> selectTypeByClubId(@Param("clubId") String clubId, @Param("try_flag") String try_flag);

    List<Map<String,String>> getClubOrCoach(@Param("clubId") String clubId,@Param("coachId") String coachId,@Param("courseType") String courseType);
}