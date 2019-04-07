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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
//        OrderDtl order = new OrderDtl();

        Map<String, String> param = new HashMap<>();
        JSONObject result= new JSONObject();

        System.out.println("username ===="+mem_id);
        mem = memberService.getMemberById(mem_id);

//        order=orderService.getOrderById("");

        // 封装返回小程序

        return mem;
    }

    /**
     *
     * @param mem_id 会员id，与user表中的id相同
     * @return 返回会员购买过的订单信息，此方法返回基本信息，包含sale_id，可根据sale_id查看订单详细信息（即课程信息）
     */
    @RequestMapping("/qryOrder")
    public OrderDtl qryMemOrder(
            @RequestParam("mem_id") String mem_id
    ){

//        OrderDtl order = new OrderDtl();

//        Map<String, String> param = new HashMap<>();
//        JSONObject result= new JSONObject();

        System.out.println("username ===="+mem_id);

        return orderService.getOrderByMemId(mem_id);

        // 封装返回小程序

//        return order;
    }

    /**
     *
     * @param mem_id 会员id，与user表中的id相同
     * @return 返回会员购买过的订单信息，此方法返回详细信息，即课程信息
     */
    @RequestMapping("/qryOrderDtl")
    public Course qryMemOrderDtl(
            @RequestParam("mem_id") String mem_id
    ){

        OrderDtl order = new OrderDtl();
        Course course = new Course();

        Map<String, String> param = new HashMap<>();
//        JSONObject result= new JSONObject();

        System.out.println("username ===="+mem_id);

        order=orderService.getOrderByMemId(mem_id);
        String sale_id=order.getSaleId();

        System.out.println("sale_id====="+mem_id);
        course  = courseService.getCourseById(sale_id);

            System.out.println("id return ====:"+course);

        return course;
    }

    @RequestMapping("/qryLesson")
    public List<Map<Object,Object>> qryLesson(
            @RequestParam("mem_id") String mem_id,
            @RequestParam("status") String status
    ){
        System.out.println("mem_id ===="+mem_id);
        return memberService.getMemberLessByIdS(mem_id,status);
    }

}
