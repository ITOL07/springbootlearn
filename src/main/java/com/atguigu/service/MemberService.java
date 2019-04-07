package com.atguigu.service;

import com.atguigu.entity.Member;
import com.atguigu.entity.MemberLesson;

import java.util.List;
import java.util.Map;

public interface MemberService {

    public Member getMemberById(String memId);



    public List<Map<Object,Object>> getMemberLessById(String memId);

    public List<Map<Object,Object>> getMemberLessByIdS(String memId,String status);

}
