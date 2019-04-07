package com.atguigu.service.impl;

import com.atguigu.dao.MemberLessonMapper;
import com.atguigu.dao.MemberMapper;
import com.atguigu.entity.Member;
import com.atguigu.entity.MemberLesson;
import com.atguigu.service.MemberService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service("memberService")
public class MemberServiceImpl implements MemberService {
    @Resource
    MemberMapper member;
    @Resource
    MemberLessonMapper mem_les;

    public Member getMemberById(String memId){

        return member.selectByPrimaryKey(memId);
    }

    public List<Map<Object, Object>> getMemberLessById(String memId){
        List<Map<Object, Object>> map=mem_les.selectById(memId);
        return map;
    }

    public List<Map<Object,Object>> getMemberLessByIdS(String memId,String status){
        List<Map<Object, Object>> map=mem_les.selectByIdS(memId,status);
        return map;
    }
}
