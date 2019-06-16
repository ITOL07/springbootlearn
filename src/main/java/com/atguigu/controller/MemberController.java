package com.atguigu.controller;

import com.alibaba.fastjson.JSONObject;
import com.atguigu.entity.*;
import com.atguigu.service.CoachCourseService;
import com.atguigu.service.CourseService;
import com.atguigu.service.MemberService;
import com.atguigu.service.OrderService;
import com.atguigu.util.CommParams;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private Logger logger = LoggerFactory.getLogger(MemberController.class);

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

        logger.info("username ===="+mem_id);
        mem = memberService.getMemberById(mem_id);

        return mem;
    }

    /**
     *
     * @param mem_id 会员id，与user表中的id相同
     * @return 返回会员购买过的订单信息，此方法返回基本信息，包含sale_id，可根据sale_id查看订单详细信息（即课程信息）
     */
    @RequestMapping("/qryOrder_bak")
    public List<OrderDtl> qryMemOrder(
            @RequestParam("mem_id") String mem_id,
            @RequestParam("trade_state") String tradeState
    ){
        logger.info("返回订单信息 mem_id ===="+mem_id+"   trade_state===="+tradeState);
        if(tradeState.equals("")){
            return orderService.getOrderByMemId(mem_id,null);
        } else{
            return orderService.getOrderByMemId(mem_id,tradeState);
        }

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

        logger.info("username ===="+mem_id);

        orderList=orderService.getOrderByMemId(mem_id,null);
        for(OrderDtl order:orderList ){
            String sale_id=order.getSaleId();
            logger.info("sale_id====="+sale_id);
            Course course  = courseService.getCourseById(sale_id);

            courseList.add(course);
            logger.info("id return ====:"+course);
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
            @RequestParam("coach_id") String coach_id,
            @RequestParam("status") String status
    ){
        logger.info("mem_id ===="+mem_id+"status===="+status);

        List<Map<Object,Object>> list= new ArrayList<Map<Object,Object>>();

        list= memberService.getMemberLessByView(mem_id,coach_id,"",status);

        for(Map<Object,Object> map:list){
            System.out.println(map);
            Object ob = null;

            if(map.containsKey("count")){
                ob=map.get("count");
                map.put("count",ob.toString());
            }
            if(map.containsKey("bz2")){
                String tmp= CommParams.WEB_URL+map.get("bz2").toString().replaceAll("/app/test","");
                map.put("bz2",tmp);
            }

            logger.info("+++++++++++"+map.get("start_time_1"));
            map.put("datex",String.valueOf(map.get("start_time_1")).split(" ")[0]);
            String start_time=String.valueOf(map.get("start_time_1")).split(" ")[1];
            String end_time=String.valueOf(map.get("end_time_1")).split(" ")[1];
            logger.info("start_time==="+start_time+"   end_time===="+end_time);

            map.put("timex",start_time.substring(0,start_time.lastIndexOf(":"))+"-"+end_time.substring(0,end_time.lastIndexOf(":")));


        }
        return list;
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
            @RequestParam("club_id") String club_id,
            @RequestParam("coach_id") String coach_id,
            @RequestParam("st_time") String st_time,
            @RequestParam("end_time") String end_time
    ){
        MemberLesson m= new MemberLesson();
        String maxSeqno=memberService.getMaxId(mem_id,sale_id);
        logger.info("maxSeqno===="+maxSeqno);
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
        Integer b=Integer.parseInt(maxSeqno);
        m.setSeqNo(++b);

        Date d = new Date();
        m.setChTime(d);

        logger.info("mem_id ===="+mem_id);

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
            @RequestParam("seq_no") Integer seq_no,
            @RequestParam("club_id") String club_id,
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

        logger.info("mem_id ===="+mem_id);

        return memberService.updateMemberLes(m);
    }

    @RequestMapping("/qryOrder")
    public List<Map<String,String>> qryMemOrder_new(
            @RequestParam("mem_id") String mem_id,
            @RequestParam("trade_state") String tradeState
    ){
        logger.info("返回订单信息 mem_id ===="+mem_id+"   trade_state===="+tradeState);
        List<Map<String,String>> list=new ArrayList<>();
        if(tradeState.equals("")){
            list= orderService.getOrderDtlByMemId(mem_id,null);
        } else{
            list= orderService.getOrderDtlByMemId(mem_id,tradeState);
        }

        for(Map<String,String> map:list){
            System.out.println(map);
            Object ob = null;
            if(map.containsKey("amount")){
                ob= map.get("amount");
                map.put("amount",ob.toString());
            }
            if(map.containsKey("recv_time")){
                ob=map.get("recv_time");
                map.put("recv_time",ob.toString());
            }
            if(map.containsKey("count")){
                ob=map.get("count");
                map.put("count",ob.toString());
            }
            if(map.containsKey("bz2")){
                String tmp= CommParams.WEB_URL+map.get("bz2").replaceAll("/app/test","");
                map.put("bz2",tmp);
            }

        }
        return list;

    }


    @RequestMapping("/qryPayInfo")
    public OrderPayInfo qryPayInfo(
            @RequestParam("order_no") String order_no
    ){
        logger.info("返回订单支付信息 order_no ===="+order_no);
        OrderPayInfo tmp=orderService.getOrderPayInfo(order_no);
        if(tmp==null){
            logger.info("无订单支付信息，请确认订单号是否正确");
            logger.info("返回订单支付信息"+tmp.toString());
            return null;
        }
        else{
            return tmp;
        }

    }

//    /**
//     *
//     * @param mem_id 会员id
//     *  课程状态 预约 上课
//     * @return 返回课时信息状态
//     */
//    @RequestMapping("/qryCancelLesson")
//    public List<TMemberLessonCancel> qryCancelLesson(
//            @RequestParam("mem_id") String mem_id,
//            @RequestParam("coach_id") String coach_id,
//            @RequestParam("club_id") String club_id,
//            @RequestParam("reg_date") String reg_date
//    ){
//        logger.info("qryCancelLesson方法：mem_id ===="+mem_id+"club_id===="+club_id+"coach_id===="+coach_id+"reg_date"+reg_date);
//
//        List<Map<Object,Object>> list= new ArrayList<Map<Object,Object>>();
//        List<TMemberLessonCancel> resList= new ArrayList<>();
//
////        list= memberService.getMemberLessByView(mem_id,coach_id,club_id,"");
//        list= memberService.getMemberLess(mem_id,coach_id,club_id,"",reg_date);
//
//        for(Map<Object,Object> map:list){
//            System.out.println("返回课程信息："+map);
//            Object ob = null;
//            TMemberLessonCancelKey tkey=new TMemberLessonCancelKey();
//            if(map.containsKey("kc_id")){
//                ob=map.get("kc_id");
//                tkey.setKcId(ob.toString());
//            }
//            if(map.containsKey("seq_no")){
//                ob=map.get("seq_no");
//                tkey.setSeqNo(Integer.parseInt(ob.toString()));
//            }
//            TMemberLessonCancel tMemberLessonCancel=memberService.getMemLesscancel(tkey);
//            if(tMemberLessonCancel != null) {
//                logger.info("tMemberLessonCancel =====" + tMemberLessonCancel.toString());
//                resList.add(tMemberLessonCancel);
//            }
//        }
//        return resList;
//    }



    /**
     *
     * @param mem_id 会员id
     *  课程状态 预约 上课
     * @return 返回课时信息状态
     */
    @RequestMapping("/qryCancelLesson")
    public List<TMemberLessonCancel> qryCancelLesson(
            @RequestParam("mem_id") String mem_id,
            @RequestParam("coach_id") String coach_id,
            @RequestParam("club_id") String club_id,
            @RequestParam("reg_date") String reg_date
    ){
        logger.info("qryCancelLesson方法：mem_id ===="+mem_id+"club_id===="+club_id+"coach_id===="+coach_id+"reg_date"+reg_date);

        List<TMemberLessonCancel> list= new ArrayList<>();
        List<TMemberLessonCancel> resList= new ArrayList<>();

//        list= memberService.getMemberLessByView(mem_id,coach_id,club_id,"");
        list = memberService.getMemberLessCancel(mem_id,coach_id,club_id,"",reg_date);
//        for(TMemberLessonCancel map:list){
//            logger.info("+++++++++++"+map.get("start_time_1"));
//
//            map.put("datex",String.valueOf(map.get("start_time_1")).split(" ")[0]);
//            String start_time=String.valueOf(map.get("start_time_1")).split(" ")[1];
//            String end_time=String.valueOf(map.get("end_time_1")).split(" ")[1];
//            logger.info("start_time==="+start_time+"   end_time===="+end_time);
//
//            map.put("timex",start_time.substring(0,start_time.lastIndexOf(":"))+"-"+end_time.substring(0,end_time.lastIndexOf(":")));
//        }

        return list;
    }
}
