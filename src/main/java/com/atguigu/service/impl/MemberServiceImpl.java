package com.atguigu.service.impl;

import com.atguigu.dao.MemberCourseMapper;
import com.atguigu.dao.MemberLessonMapper;
import com.atguigu.dao.MemberMapper;
import com.atguigu.entity.Member;
import com.atguigu.entity.MemberCourse;
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
    @Resource
    MemberCourseMapper memberCourseMapper;

    public Member getMemberById(String memId){

        return member.selectByPrimaryKey(memId);
    }

    public MemberCourse getMemberCourseByMemId(String memId){
        return memberCourseMapper.selectByMemId(memId);
    }

    public List<Map<Object, Object>> getMemberLessById(String memId){
        List<Map<Object, Object>> map=mem_les.selectById(memId);
        return map;
    }

    public List<Map<Object,Object>> getMemberLessByIdS(String memId,String status){
        List<Map<Object, Object>> map=mem_les.selectByIdS(memId,status);
        return map;
    }

    public List<Map<Object,Object>> getMemberLessByCoachId(String coachId,String status){
        List<Map<Object, Object>> map=mem_les.selectByCoachId(coachId,status);
        return map;
    }

    public List<Map<Object,Object>> getMemberLessByView(String memId,String coachId,String status){
        List<Map<Object, Object>> map=mem_les.selectByView(memId,coachId,status);
        return map;
    }

    public List<Map<Object,Object>> getMemberLessByViewDate(String memId,String coachId,String status,String reg_date){
        List<Map<Object, Object>> map=mem_les.selectByViewDate(memId,coachId,status,reg_date);
        return map;
    }
    public List<Map<Object,Object>> getMemberLessByCoachIdDate(String coachId,String status,String reg_date){
        List<Map<Object, Object>> map=mem_les.selectByCoachIdDate(coachId,status,reg_date);
        return map;
    }


    public boolean addMemberLes(MemberLesson record){
        boolean result = false;
        try {
            mem_les.insertSelective(record);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }
    public String getMaxId(String memId,String sale_id){
        return mem_les.selectMaxId(memId,sale_id);
    }

//    public int updateMemberLes(MemberLesson mem,String mem_id,String sale_id,Byte seq_no){
    public int updateMemberLes(MemberLesson mem){
        return mem_les.update(mem);
    }

    public Map<Object,Object> getMemCourseSum(String mem_id){
        return memberCourseMapper.selectMemCourseSum(mem_id);
    }
}
