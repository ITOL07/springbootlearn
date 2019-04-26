package com.atguigu.service.impl;

import com.atguigu.dao.MemberCourseMapper;
import com.atguigu.dao.MemberLessonMapper;
import com.atguigu.entity.MemberCourse;
import com.atguigu.entity.MemberLesson;
import com.atguigu.service.AttendClassService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("attendClassService")
public class AttendClassServiceImpl implements AttendClassService {
    @Resource
    private MemberLessonMapper memberLessonMapper;
    @Resource
    private MemberCourseMapper memberCourseMapper;


    @Override
    public boolean updateStatus(MemberLesson record) {

        return memberLessonMapper.updateStatus(record);
    }

    @Override
    public boolean updateMemberNum(MemberCourse record) {

        return memberCourseMapper.updateMemberNum(record);
    }
}
