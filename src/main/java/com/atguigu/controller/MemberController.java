package com.atguigu.controller;

import com.alibaba.fastjson.JSONObject;
import com.atguigu.entity.Course;
import com.atguigu.entity.Member;
import com.atguigu.entity.MemberLesson;
import com.atguigu.entity.OrderDtl;
import com.atguigu.service.CoachCourseService;
import com.atguigu.service.CourseService;
import com.atguigu.service.MemberService;
import com.atguigu.service.OrderService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 会员信息页
 */
@RestController
@RequestMapping("/member")
public class MemberController {

    @Resource
    private MemberService memberService;
    @Resource
    private OrderService orderService;
    @Resource
    private CoachCourseService coachCourseService;
    @Resource
    private CourseService courseService;

    /**
     *
     * @param mem_id  会员id，与user表中的id相同
     * @return 返回会员的基本信息，在我的"身体档案"中可查看
     */
    @RequestMapping("/qry")
    public Member qryMember(
            @RequestParam("mem_id") String mem_id
    ){
        Member mem= new Member();

        Map<String, String> param = new HashMap<>();
        JSONObject result= new JSONObject();

        System.out.println("username ===="+mem_id);
        mem = memberService.getMemberById(mem_id);

        return mem;
    }

    /**
     *
     * @param mem_id 会员id，与user表中的id相同
     * @return 返回会员购买过的订单信息，此方法返回基本信息，包含sale_id，可根据sale_id查看订单详细信息（即课程信息）
     */
    @RequestMapping("/qryOrder")
    public List<OrderDtl> qryMemOrder(
            @RequestParam("mem_id") String mem_id
    ){
        System.out.println("username ===="+mem_id);

        return orderService.getOrderByMemId(mem_id);
    }

    /**
     *
     * @param mem_id 会员id，与user表中的id相同
     * @return 返回会员购买过的订单信息，此方法返回详细信息，即课程信息
     */
    @RequestMapping("/qryOrderDtl")
    public List<Course> qryMemOrderDtl(
            @RequestParam("mem_id") String mem_id
    ){

        List<OrderDtl> orderList = new ArrayList<OrderDtl>();
        List<Course> courseList = new ArrayList<Course>();

        Map<String, String> param = new HashMap<>();

        System.out.println("username ===="+mem_id);

        orderList=orderService.getOrderByMemId(mem_id);
        for(OrderDtl order:orderList ){
            String sale_id=order.getSaleId();
            System.out.println("sale_id====="+sale_id);
            Course course  = courseService.getCourseById(sale_id);

            courseList.add(course);
            System.out.println("id return ====:"+course);
        }

        return courseList;
    }

    /**
     *
     * @param mem_id 会员id
     * @param status 课程状态 预约 上课
     * @return 返回课时信息状态
     */
    @RequestMapping("/qryLesson")
    public List<Map<Object,Object>> qryLesson(
            @RequestParam("mem_id") String mem_id,
            @RequestParam("status") String status
    ){
        System.out.println("mem_id ===="+mem_id+"status===="+status);

        List<Map<Object,Object>> list= new ArrayList<Map<Object,Object>>();
//        List<Map<Object,Object>> list_res= new ArrayList<Map<Object,Object>>();
        list=memberService.getMemberLessByIdS(mem_id,status);

        for(Map<Object,Object> map:list){
            String sale_id=map.get("mem_id").toString();
            System.out.println("start_time====="+map.get("end_time_1"));
        }
//        return memberService.getMemberLessByIdS(mem_id,status);
        return memberService.getMemberLessByView(mem_id,status);
    }

    /**
     *
     * @param mem_id
     * @param sale_id
     * @param club_id
     * @param coach_id
     * @param st_time
     * @param end_time
     * @return
     */
    @RequestMapping("/addLesson")
    public boolean addLesson(
            @RequestParam("mem_id") String mem_id,
            @RequestParam("sale_id") String sale_id,
            @RequestParam("club_id") int club_id,
            @RequestParam("coach_id") String coach_id,
            @RequestParam("st_time") String st_time,
            @RequestParam("end_time") String end_time
    ){
        MemberLesson m= new MemberLesson();
        String maxSeqno=memberService.getMaxId(mem_id,sale_id);
        System.out.println("maxSeqno===="+maxSeqno);
        m.setMemId(mem_id);
        m.setSaleId(sale_id);
        m.setClubId(club_id);
        m.setCoachId(coach_id);
        m.setStatus(Byte.parseByte("0"));
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd HH:mm:ss");
        try{
            Date d1=sdf.parse(st_time);
            m.setStartTime1(d1);
            Date d2=sdf.parse(end_time);
            m.setEndTime1(d2);
        }catch(ParseException e){
            e.printStackTrace();
        }
        Byte b=Byte.parseByte(maxSeqno);
        m.setSeqNo(++b);

        Date d = new Date();
        m.setChTime(d);

        System.out.println("mem_id ===="+mem_id);

        return memberService.addMemberLes(m);
    }

    /**
     *
     * @param mem_id
     * @param sale_id
     * @param seq_no
     * @param club_id
     * @param coach_id
     * @param st_time
     * @param end_time
     * @return
     */

    @RequestMapping("/updateLesson")
    public int updateLesson(
            @RequestParam("mem_id") String mem_id,
            @RequestParam("sale_id") String sale_id,
            @RequestParam("seq_no") Byte seq_no,
            @RequestParam("club_id") int club_id,
            @RequestParam("coach_id") String coach_id,
            @RequestParam("st_time") String st_time,
            @RequestParam("end_time") String end_time
    ){
        MemberLesson m= new MemberLesson();

        m.setMemId(mem_id);
        m.setSaleId(sale_id);
        m.setSeqNo(seq_no);
        m.setClubId(club_id);
        m.setCoachId(coach_id);
        m.setStatus(Byte.parseByte("0"));

        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd HH:mm:ss");
        try{
            Date d1=sdf.parse(st_time);
            m.setStartTime1(d1);
            Date d2=sdf.parse(end_time);
            m.setEndTime1(d2);
        }catch(ParseException e){
            e.printStackTrace();
        }

        Date d = new Date();
        m.setChTime(d);

        System.out.println("mem_id ===="+mem_id);

        return memberService.updateMemberLes(m);
    }


}
