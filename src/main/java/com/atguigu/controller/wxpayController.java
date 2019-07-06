package com.atguigu.controller;

import com.atguigu.dao.TClubLessonRegMapper;
import com.atguigu.dao.TCoachLessonRegMapper;
import com.atguigu.entity.*;
import com.atguigu.service.*;
import com.atguigu.util.getSeqNo;
import com.atguigu.wechatpay.app.UnifiedOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 微信支付接口，包括统一下单和订单查询
 */
@RestController
@RequestMapping("/pay")
public class wxpayController {

    private Logger logger = LoggerFactory.getLogger(CoachController.class);
    @Resource
    private OrderService orderService;

    @Resource
    private UserService1 userService1 ;

    @Resource
    private MemberService memberService;

    @Resource
    private CourseService courseService;

    @Resource
    private TCoachLessonRegMapper tCoachLessonRegMapper;

    @Resource
    private TClubLessonRegMapper tClubLessonRegMapper;

    @Resource
    private IncomeService incomeService;

    @PostMapping("/id")
    public  Map<String, Object> wxpay(
            @RequestParam("desc") String desc,
            @RequestParam("order_no") String order_no,
            @RequestParam("openid") String openid,
            @RequestParam("sale_id") String sale_id,
            @RequestParam("try_flag") String try_flag,
            @RequestParam("price") String price,
            @RequestParam("count") Integer count
    ){
        Map<String, Object> resultMap = new HashMap<String, Object>();
//        String money = "0.01";

        //如果是体验课，先查询该用户是否购买过体验课
        if(try_flag.equals("1")){
            System.out.println("openid========="+openid);
            User user=this.userService1.getUserByOpenId(openid);
            String user_try_flag=user.getTry_flag();
            System.out.println("user_try_flag====["+user_try_flag+"]");
            if(user_try_flag.equals("1")){ //已体验过
                resultMap.put("errcode","已体验过该课程，无法重复体验");
                resultMap.put("errno","-1");
                return resultMap;
            }
        }
        OrderDtl order = new OrderDtl();

        BigDecimal bigtotalAmount = new BigDecimal(price);

        resultMap = UnifiedOrder.weixinPrePay(order_no, bigtotalAmount, desc,openid);
        //插入order表
        System.out.println("order_no==="+order_no+"====openid======"+openid);
        order.setOrderNo(order_no);
        order.setAmount(bigtotalAmount);
        order.setMemId(openid);
        order.setSaleId(sale_id);
        order.setDes(desc);
        order.setTry_flag(try_flag);
        order.setCount(count);

        //体验课的话，需要关联更新用户表的try_flag字段为真

        boolean bool = this.orderService.addOrder(order);

        //插入order_payinfo表，以支持重新付款

        OrderPayInfo orderPayInfo = new OrderPayInfo();
        orderPayInfo.setOrderNo(order_no)
                .setAppId(resultMap.get("appid").toString())
                .setPackage1(resultMap.get("package").toString())
                .setNoncestr(resultMap.get("noncestr").toString())
                .setTimestamp(resultMap.get("timestamp").toString())
                .setSign(resultMap.get("sign").toString())
                .setResultCode(resultMap.get("result_code").toString())
                .setReturnCode(resultMap.get("return_code").toString());

        orderService.insertOrderPayInfo(orderPayInfo);

        Map<String, Object> payMap = new HashMap<String, Object>();
        payMap=UnifiedOrder.queryWxResult(order_no);
        System.out.println(payMap);
        return resultMap;
    }

    @PostMapping("/res")
    public  Map<String, Object> wxpay(
            @RequestParam("order_no") String order_no//,
//            @RequestParam("tradeState") String tradeState
    ){
        Map<String, Object> payMap = new HashMap<String, Object>();
        OrderDtl order = new OrderDtl();
        OrderPayInfo orderPayInfo = new OrderPayInfo();
        Date d = new Date();


        //更新order表支付状态
//        System.out.println("order_no==="+order_no+"====tradeState======"+tradeState);
        System.out.println("收到订单号==="+order_no);
        System.out.println("开始查询支付结果==="+order_no);


        payMap=UnifiedOrder.queryWxResult(order_no);
        String trade_state=payMap.get("trade_state").toString();
        String trade_state_desc=payMap.get("trade_state_desc").toString();
        System.out.println("order_no["+order_no+"]\t trade_state["+trade_state+"]\t trade_state_desc"+trade_state_desc);

        order.setOrderNo(order_no);
        if(trade_state.equals("NOTPAY")){
            trade_state="-1";
        }else if(trade_state.equals("SUCCESS")){
            trade_state="0";

        }

        order.setOrderNo(order_no);
        order.setTradeState(trade_state);
        order.setTradeStateDesc(trade_state_desc);
        order.setRecvTime(d);

        orderPayInfo.setOrderNo(order_no)
                .setStatus(Integer.parseInt(trade_state));

        boolean bool = this.orderService.updateOrder(order);
        System.out.println("更新order数据库状态："+bool);

        int i = this.orderService.updateOrderPayInfo(orderPayInfo);
        System.out.println("更新order数据库状态："+i);

        //更新用户状态
        if(trade_state.equals("0")){
            OrderDtl order_tmp=this.orderService.getOrderById(order_no);
            String open_id=order_tmp.getMemId();
            String try_flag=order_tmp.getTry_flag();
            User user=this.userService1.getUserByOpenId(open_id);
            user.setTry_flag(try_flag);

            boolean bool1 =this.userService1.updateUserByOpenid(user);
            System.out.println("更新user表数据库状态："+bool);


            String course_id=order_tmp.getSaleId();
            int total_lesson=order_tmp.getCount();

            MemberCourse mc=step1(open_id,course_id,total_lesson);
            step2(course_id,total_lesson);
            step3(mc);
            step4(course_id,total_lesson);

        }


        return payMap;
    }

    //step1 插入member_course表
    public MemberCourse step1(String open_id,String course_id,int total){

        int index=0;

        MemberCourse mc=new MemberCourse();
        logger.info("open_id==="+open_id);
        //根据open_id获取user_id
        User user=userService1.getUserByOpenId(open_id);
        String user_id=user.getId();
        logger.info("user_id==="+user_id);
        String maxId=memberService.getMaxKcId();
        logger.info("maxId==="+maxId);
        if(maxId!=null) {
            index = Integer.parseInt(maxId.substring(10));
        }
        String id= getSeqNo.getId(12,index,4);
        logger.info("kcid==="+id);
        mc.setKcId(id).setMemId(user_id)
                .setCourseId(course_id)
                .setTotalLesson(total)
                .setUsed(0)
                .setRem(total)
                .setBuy_time(new Date())
                 .setBuy_count(total);
        logger.info("open_id=["+open_id+"user_id=="+user_id+"    course_id==="+course_id);
        memberService.addMemberCourse(mc);

        return mc;
    }

    //step2 关联更新教练日记表 t_coach_lesson_reg
    //入参 course_id
    public boolean step2(String course_id,int sold_count){
        boolean bool=false;
        //根据课程id找教练信息
        Course c=courseService.getCourseById(course_id);
        String club_id=c.getClubId();
        String coach_id=c.getCoachId();
        String course_type=c.getType();
        BigDecimal price=c.getPrice();
        Date reg_time=new Date();

        //教练日记表
        //先查询有无记录，无则插入，有则更新
        TCoachLessonReg tclr=new TCoachLessonReg();
        tclr.setCoachId(coach_id)
                .setCourseType(course_type)
                .setSoldPrice(price)
                .setRegDate(reg_time)
                .setRegTime(reg_time)
                .setSoldCount(sold_count);
        Map<Object,Object> map=tCoachLessonRegMapper.selectByCoachIdS(coach_id,reg_time,course_type);
        if(map!=null){
            //更新
            int sold_before= (Integer) map.get("sold_count");
            int id=(Integer) map.get("id");
            logger.info("已有记录，原售课数为"+sold_before+"新增售课数为："+sold_count);
            int sum=sold_before+sold_count;
            logger.info("更新售课数为："+sum);
            TCoachLessonReg tc_new=new TCoachLessonReg();
            tc_new.setId(id)
                .setSoldCount(sum)
                .setRegTime(new Date());
            orderService.updateCoachReg(tc_new);
        }else{
            //插入
            orderService.insertCoachReg(tclr);
        }
        logger.info("更新教练日计表完成");

//        店面日记表
        TClubLessonReg tClubLessonReg=new TClubLessonReg();
        tClubLessonReg.setClubId(club_id)
                .setCourseType(course_type)
                .setSoldPrice(price)
                .setRegDate(reg_time)
                .setRegTime(reg_time)
                .setSoldCount(sold_count);
        Map<Object,Object> map_club=tClubLessonRegMapper.selectByClubIdS(club_id,reg_time,course_type);
        if(map_club!=null) {
            //更新
            int sold_before = (Integer) map_club.get("sold_count");
            int id = (Integer) map_club.get("id");
            logger.info("已有记录，原售课数为" + sold_before + "新增售课数为：" + sold_count);
            int sum = sold_before + sold_count;
            logger.info("更新售课数为：" + sum);
            TClubLessonReg tclub_new = new TClubLessonReg();
            tclub_new.setId(id)
                    .setSoldCount(sum)
                    .setRegTime(new Date());
            orderService.updateClubReg(tclub_new);
        }
        else{
            orderService.insertClubReg(tClubLessonReg);
        }
        logger.info("更新场地日计表完成");
        return bool;
    }

    //step3 新增对应的课时，插入member_lesson表
    public boolean step3(MemberCourse mem){
        boolean bool = false;
        String mem_id=mem.getMemId();
        String course_id=mem.getCourseId();
        int total_lesson=mem.getTotalLesson();
        String kc_id=mem.getKcId();

        Course c=courseService.getCourseById(course_id);
        String club_id=c.getClubId();
        String coach_id=c.getCoachId();
        String course_type=c.getType();
        MemberLesson ml = new MemberLesson();
        for(int i=0;i<total_lesson;){
            ml.setMemId(mem_id)
                    .setSaleId(course_id)
                    .setKcId(kc_id)
                    .setClubId(club_id)
                    .setCoachId(coach_id)
                    .setChTime(new Date())
                    .setBz2(course_type)
                    .setSeqNo(++i);
            memberService.addMemberLes(ml);
            logger.info("第"+i+"节课插入lesson表成功");
        }

        return bool;
    }

    //step4 记入销提
    public boolean step4(String course_id,int sold_count){
        boolean bool=false;
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String today=formatter.format(date);

        Course c=courseService.getCourseById(course_id);
        String club_id=c.getClubId();
        String coach_id=c.getCoachId();
//        String course_type=c.getType();

        TIncomeDtl tIncomeDtl = new TIncomeDtl();
        tIncomeDtl.setCourseId(course_id)
                .setRegDate(date)
                .setCoachId(coach_id)
                .setClubId(club_id)
                .setRegTime(date)
        ;

        TIncomeDtl list=incomeService.getIncomDtl(course_id,coach_id,club_id,today);

        //开始登记t_income_dtl表数据
        if(list==null){
            logger.info("t_income_dtl表今天没有课程，开始新增当天数据");
            tIncomeDtl.setXtCnt(sold_count);
            int insertDtl = incomeService.insertIncomDtl(tIncomeDtl);
            if(insertDtl==1){
                logger.info("t_income_dtl表新增当天数据完成");
            }else{
                logger.info("t_income_dtl表新增当天数据失败");
            }
        }else{
            logger.info("t_income_dtl表今天此类型的课已经有记录，开始更新，售课节数为["+sold_count+"]  课程id为"+course_id);

            tIncomeDtl.setXtCnt(list.getXtCnt()+sold_count);

            int insertflag= incomeService.updateIncomDtl(tIncomeDtl);
            if(insertflag==1){
                logger.info("t_income_dtl表更新当天数据完成");
            }else{
                logger.info("t_income_dtl表更新当天数据失败");
            }
        }
        return bool;
    }
}