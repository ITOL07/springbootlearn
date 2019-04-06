package com.atguigu.service.impl;

import com.atguigu.dao.MemberMapper;
import com.atguigu.entity.Member;
import com.atguigu.service.MemberService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("memberService")
public class MemberServiceImpl implements MemberService {
    @Resource
    MemberMapper member;

    public Member getMemberById(String memId){

        return member.selectByPrimaryKey(memId);
    }
}
