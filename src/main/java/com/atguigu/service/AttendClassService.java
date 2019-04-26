package com.atguigu.service;

import com.atguigu.entity.MemberCourse;
import com.atguigu.entity.MemberLesson;

public interface AttendClassService {

    boolean updateStatus(MemberLesson record);

    boolean updateMemberNum(MemberCourse record);
}
