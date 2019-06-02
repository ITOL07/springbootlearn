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
import java.util.ArrayList;
import java.util.HashMap;
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

    public List<Map<Object, Object>> getMemberLessById(String memId){
        List<Map<Object, Object>> map=mem_les.selectById(memId);
        return map;
    }

    @Override
    public List<Map<Object, Object>> selectMemberInfo_1(String coachid) {
        return  mem_les.selectMemberInfo_1(coachid);
    }

    public List<Map<Object,Object>> getMemberLessByIdS(String memId,String status){
        List<Map<Object, Object>> map=mem_les.selectByIdS(memId,status);
        return map;
    }

    @Override
    public List<Map<Object, Object>> getMemberLessByView(String memId, String coachId, String clubId,String status) {
        return mem_les.selectByView(memId,coachId,clubId,status);
    }

    @Override
    public List<Map<Object, Object>> getMemberLessByCoachId(String coachId, String status) {
        return mem_les.selectByCoachId(coachId,status);
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

    @Override

    public Map<Object, Object> getMemCourseSum(String mem_id) {
        return memberCourseMapper.selectMemCourseSum(mem_id);
    }

    @Override
    public Map<String, String> selecctInfoByKcid(MemberLesson record) {
        return mem_les.selecctInfoByKcid(record);
    }

    @Override
    public int selectseqno(MemberLesson record) {

        return mem_les.selectseqno(record);
    }

    @Override
    public List<Map<Object, Object>> selectMemberInfo(String coachid) {
        List<Map<Object, String>> list = mem_les.selectMemberInfo(coachid);
        List<Map<Object, Object>> list1= new ArrayList<>();
        Map<String,String> map1 = new HashMap<>();
        map1.put("cumulative","6");
        map1.put("completed","6");
        map1.put("ordering","6");
        map1.put("unorder","6");
        Map<String,Map<String,String>> map2 = new HashMap<>();
        map2.put("course",map1);

        for(Map<Object,String> map : list){
            System.out.println("name===="+map.get("name"));
            System.out.println("map.get(\"telephone\")"+map.get("telephone"));
            map.put("id","1");
            map.put("photo","../../images/student/photo.png");
            map.put("flag","取消置顶");
            Map map3 = new HashMap();
            map3.putAll(map);
            map3.putAll(map2);
            list1.add(map3);
        }


        return list1;
    }
    @Override
    public List<Map<String,String>> selectLessonList(String mem_id,String coach_id) {
        List<Map<String,String>> list = mem_les.selectLessonList(mem_id,coach_id);
        return list;
    }
    @Override
    public List<Map<Object, String>> selectByLesson(String mem_id, String course_id, String club_id, String coach_id) {

        List<Map<Object, String>> mapList = mem_les.selectByLesson(mem_id,course_id,club_id,coach_id);

        return mapList;
    }
    @Override
    public List<Map<String, String>> selectClubList(String course_id) {
        List<Map<String,String>> list = mem_les.selectClubList(course_id);
        return list;
    }



    //课程
    @Override
    public int addMemberCourse(MemberCourse m){
        return memberCourseMapper.insertSelective(m);
    }
    @Override
    public String getMaxKcId(){
        return memberCourseMapper.selectMaxKcId();
    }

    //课时
    @Override
    public int addLess(MemberLesson ml){
        return mem_les.insertSelective(ml);
    }

    public boolean updateMember(Member mem){
        boolean result = false;
        try {
            member.updateByPrimaryKeySelective(mem);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }
    public boolean addMember(Member record){
        boolean result = false;
        try {
            member.insertSelective(record);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    @Override
    public List<String> getListTimes(String mem_id, String date) {
        List<String> listTimes = mem_les.getListTimes(mem_id, date);

        return listTimes;
    }


    public Map<Object, Object> getMemCourseInfo(String mem_id,String coachId,String clubId) {
        return memberCourseMapper.selectMemCourseInfo(mem_id,coachId,clubId);
    }
}
