package com.atguigu.controller;

import com.atguigu.entity.*;
import com.atguigu.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.text.*;

@RestController
@RequestMapping(value = "/attendClass")
public class AttendClassController {

    private static final Logger logger = LoggerFactory.getLogger(AttendClassService.class);

    @Resource
    private AttendClassService attendClassService;
    @Resource
    private MemberService memberService;
    @Resource
    private CourseService courseService;
    @Resource
    private TCoachLessonRegService tCoachLessonRegService;
    @Resource
    private TClubLessonRegService tClubLessonRegService;
    @Resource
    private IncomeService incomeService;

    /***
     * @param kc_id 课程id
     * @param seq_no 当前课程节数
     * @param choose_flag 课程行进标志
     * updateCourseInfos 首页课程签到签退功能
     *                   涉及memeber_lesson中课程状态
     *                    member_course中课程节数
     *                    t_coach_lesson_reg中教练日计信息
     *                    t_club_lesson_reg中场地日计信息
     *
     */
    @ResponseBody
    @RequestMapping("/updateCourseInfos")
    public Map<String,String> updateCourseInfos(@RequestParam("kc_id") String kc_id,
                                   @RequestParam("seq_no") String seq_no,
                                   @RequestParam("choose_flag") String choose_flag)
    {
        //获取当前时间，与排课时间对比，早于或晚于多长时间，不可签到
        Map<String,String> map = new HashMap<>();
        String resultInfo = "";
        logger.info("kc_id ====" + kc_id + "；seq_no=====" + seq_no+": choose_flag===="+choose_flag);
        int f = Integer.parseInt(seq_no);
        byte status = 0;
        //前端传入课程行进状态，0：签到，1：签退
        if(choose_flag.equals("0")){
            status = 1;
            resultInfo="已签到";
        }else if(choose_flag.equals("1")){
            status = 2;
            resultInfo="已签退";
        }
        //开始进行对会员课时表的status维护
        MemberLesson memberLesson = new MemberLesson();
        Date StatusDate = new Date();
        memberLesson.setSeqNo(f)
                    .setStatus(status)
                    .setKcId(kc_id);
        if(choose_flag.equals("0")){
            memberLesson.setStartTime2(StatusDate);
        }else if(choose_flag.equals("1")){
            memberLesson.setEndTime2(StatusDate);
        }
        boolean flag = attendClassService.updateStatus(memberLesson);
        logger.info("预计完成状态："+resultInfo+",执行结果："+flag);
        map.put("flag",flag+"");
        map.put("resultInfo",resultInfo);
        //完成签退功能后需要对会员课程表课程节数进行修改；需要进行对教练日记表和场地日记表的维护
        if(flag&&choose_flag.equals("1")){
            logger.info("课程签退成功，开始对会员课程表课程节数进行修改");
            MemberCourse memberCourse = new MemberCourse();
            memberCourse.setKcId(kc_id);
            boolean memberUseFlag = attendClassService.updateMemberNum(memberCourse);
            if(memberUseFlag){
                logger.info("更新表member_course成功，已变更会员课程节数信息，开始对教练日记表进行维护");
            }
            //获取维护教练日记表t_coach_lesson_reg,场地日记表t_club_lesson_reg所需要的数据
            Map<String, Object> map1 = memberService.selecctInfoByKcid(memberLesson);
            Course course = courseService.selectByPrimaryKey(map1.get("sale_id").toString());
            Date date = new Date();

            //装载教练日记表t_coach_lesson_reg实体类
            TCoachLessonReg tCoachLessonReg = new TCoachLessonReg();
            tCoachLessonReg.setRegDate(date).setCourseType(course.getType().toString());
            TCoachLessonReg tCoachLessonReg1 = tCoachLessonRegService.seletNumByDate(tCoachLessonReg);

            int les_count = null!=tCoachLessonReg1?tCoachLessonReg1.getLesCount():0;
            tCoachLessonReg.setCoachId(map1.get("real_coach").toString())
                    .setLesCount(les_count+1)
                    .setLesPrice(course.getPrice())
                    .setRegTime(date);
            //开始维护教练日记表t_coach_lesson_reg表数据
            if(les_count==0){
                logger.info("教练日记表今天没有课程，开始新增当天数据");
                int insertCoachReg = tCoachLessonRegService.insertCoachReg(tCoachLessonReg);
                if(insertCoachReg==1){
                    logger.info("教练日记表新增当天数据完成");
                }else{
                    logger.info("教练日记表新增当天数据失败");
                }
            }else{
                logger.info("教练日记表今天已经有结束的课程，开始更新当天类型："+tCoachLessonReg1.getCourseType()+",上课次数为："+(tCoachLessonReg1.getLesCount()+1));
                tCoachLessonReg.setId(tCoachLessonReg1.getId());
                int insertCoachReg = tCoachLessonRegService.updateCoachReg(tCoachLessonReg);
                if(insertCoachReg==1){
                    logger.info("教练日记表更新当天数据完成");
                }else{
                    logger.info("教练日记表更新当天数据失败");
                }
            }

            //装载场地日记表t_club_lesson_reg实体类
            TClubLessonReg tClubLessonReg = new TClubLessonReg();
            tClubLessonReg.setRegDate(date).setCourseType(course.getType().toString());
            TClubLessonReg tClubLessonReg1 = tClubLessonRegService.seletNumByDate(tClubLessonReg);
            int les_count_club = null!=tClubLessonReg1?tClubLessonReg1.getLesCount():0;
            tClubLessonReg.setClubId(map1.get("real_club").toString())
                    .setLesCount(les_count_club+1)
                    .setLesPrice(course.getPrice())
                    .setRegTime(date);
            //开始维护场地日记表t_club_lesson_reg表数据
            if(les_count_club==0){
                logger.info("场地日记表今天没有课程，开始新增当天数据");
                int insertClubReg = tClubLessonRegService.insertClubReg(tClubLessonReg);
                if(insertClubReg==1){
                    logger.info("场地日记表新增当天数据完成");
                }else{
                    logger.info("场地日记表新增当天数据失败");
                }
            }else{
                logger.info("场地日记表今天此类型的课已经有结束的课程，开始更新当天类型："+tClubLessonReg1.getCourseType()+",上课次数为："+(tClubLessonReg1.getLesCount()+1));
                tClubLessonReg.setId(tClubLessonReg1.getId());
                int insertClubReg = tClubLessonRegService.updateClubReg(tClubLessonReg);
                if(insertClubReg==1){
                    logger.info("场地日记表更新当天数据完成");
                }else{
                    logger.info("场地日记表更新当天数据失败");
                }
            }

            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            String today=formatter.format(date);

            //登记t_income_dtl
            String course_id=map1.get("sale_id").toString();
            TIncomeDtl tIncomeDtl = new TIncomeDtl();
                    tIncomeDtl.setCourseId(course_id)
                    .setRegDate(date)
                    .setCoachId(map1.get("real_coach").toString())
                    .setClubId(map1.get("real_club").toString())
                    .setRegTime(date)
            ;

            TIncomeDtl list=incomeService.getIncomDtl(course_id,map1.get("real_coach").toString(),map1.get("real_club").toString(),today);

            //开始登记t_income_dtl表数据
            if(list==null){
                logger.info("t_income_dtl表今天没有课程，开始新增当天数据");
                tIncomeDtl.setKtCnt(1);
                int insertDtl = incomeService.insertIncomDtl(tIncomeDtl);
                if(insertDtl==1){
                    logger.info("t_income_dtl表新增当天数据完成");
                }else{
                    logger.info("t_income_dtl表新增当天数据失败");
                }
            }else{
                logger.info("t_income_dtl表今天此类型的课已经有结束的课程，开始更新当天类型："+tClubLessonReg1.getCourseType()+",上课次数为："+(tClubLessonReg1.getLesCount()+1));

                tIncomeDtl.setKtCnt(list.getKtCnt()+1);
                logger.info("tIncomeDtl.toString():"+tIncomeDtl.toString());
                int insertflag= incomeService.updateIncomDtl(tIncomeDtl);
                if(insertflag==1){
                    logger.info("t_income_dtl表更新当天数据完成");
                }else{
                    logger.info("t_income_dtl表更新当天数据失败");
                }
            }


            //登记t_income,教练部分
            String coach_id=map1.get("real_coach").toString();
            String club_id=map1.get("real_club").toString();

            //1--体验课   其他-- 非体验课
            boolean try_flag=map1.get("course_type").toString().equals("1")?true:false;

            //获取dtl表中按course_id和日期汇总的kt_sum，和xt_sum
            Map<String,Object> lesSummap = incomeService.getCoachLesSum(course_id,coach_id,today);
            Map<String,Object> clublesSummap = incomeService.getClubLesSum(course_id,club_id,today);
            logger.info("map" +lesSummap.toString());

            BigDecimal KT_CNT=(BigDecimal)(lesSummap.get("kt_sum")==null?new BigDecimal(0):lesSummap.get("kt_sum"));
            BigDecimal XT_CNT=(BigDecimal)(lesSummap.get("xt_sum")==null?new BigDecimal(0):lesSummap.get("xt_sum"));

            BigDecimal CD_KT_CNT=(BigDecimal)(clublesSummap.get("kt_sum")==null?new BigDecimal(0):clublesSummap.get("kt_sum"));
            BigDecimal PRICE = (BigDecimal)(map1.get("price"));

            CourseTc tc = incomeService.getCourseTcInfo(course_id);
            logger.info("tc info ==="+tc.toString());
            BigDecimal JL_KTPER,JL_XTPER,CD_KTPER;
            //1--场地教练   0--平台教练
            boolean jlFlag=map1.get("coach_type").toString().equals("0")?true:false;
            if(jlFlag){
                JL_KTPER=tc.getJlKtPer1();
                JL_XTPER=tc.getJlXtPer1();
                CD_KTPER=tc.getCdKt();
            }else{
                JL_KTPER=tc.getJlKtPer2();
                JL_XTPER=tc.getJlXtPer2();
                CD_KTPER=tc.getCdKt();
            }

            BigDecimal kt_sum,xt_sum,cd_kt_sum;
            if(try_flag){
                kt_sum=KT_CNT.multiply(tc.getJlKtCnt1());
                xt_sum=new BigDecimal(0);
                cd_kt_sum=new BigDecimal(0);
            }else{
                kt_sum=KT_CNT.multiply(PRICE).multiply(JL_KTPER);
                xt_sum=XT_CNT.multiply(PRICE).multiply(JL_XTPER);
                cd_kt_sum=CD_KT_CNT.multiply(PRICE).multiply(CD_KTPER);
            }

            TIncome tIncome_jl = new TIncome();
            TIncome tIncome_cd = new TIncome();
            tIncome_jl.setUserId(coach_id)
                    .setRegDate(date)
                    .setRegTime(date)
                    .setKtCnt(KT_CNT)
                    .setKtPer(JL_KTPER)
                    .setXtPer(JL_XTPER)
                    .setXtCnt(new BigDecimal(0))
                    .setKtSum(kt_sum)
                    .setXtSum(xt_sum)
            ;

            tIncome_cd.setUserId(club_id)
                    .setRegDate(date)
                    .setRegTime(date)
                    .setKtCnt(CD_KT_CNT)
                    .setKtPer(CD_KTPER)
                    .setKtSum(cd_kt_sum);


            TIncome tIncome_jl_before=incomeService.getIncom(coach_id,today);

            //开始登记t_income表数据(教练)
            if(tIncome_jl_before==null){
                logger.info("t_income表今天没有课程，新增当天数据"+tIncome_jl.toString());

                int insertDtl = incomeService.insertIncom(tIncome_jl);
                if(insertDtl==1){
                    logger.info("t_income表新增当天数据完成");
                }else{
                    logger.info("t_income表新增当天数据失败");
                }
            }else{
                logger.info("t_income表今天此类型的课已经有记录："+tIncome_jl_before.toString());
                logger.info("t_income表今天此类型的课已经有记录，开始更新："+tIncome_jl.toString());
//                BigDecimal ktcnt_before=tIncome_jl_before.getKtCnt().add(tIncome_jl.getKtCnt());
//                BigDecimal xtcnt_before=tIncome_jl_before.getXtCnt().add(tIncome_jl.getXtCnt());
//                BigDecimal ktsum_before=tIncome_jl_before.getKtSum().add(tIncome_jl.getKtSum());
//                BigDecimal xtsum_before=tIncome_jl_before.getXtSum().add(tIncome_jl.getXtSum());
                BigDecimal ktcnt_before=tIncome_jl_before.getKtCnt().add(new BigDecimal(1));
                BigDecimal xtcnt_before=tIncome_jl_before.getXtCnt();
                BigDecimal ktsum_before=tIncome_jl_before.getKtSum().add(PRICE.multiply(JL_KTPER));
                BigDecimal xtsum_before=tIncome_jl_before.getXtSum().add(PRICE.multiply(JL_XTPER));

                tIncome_jl.setKtCnt(ktcnt_before)
                        .setXtCnt(xtcnt_before)
                        .setKtSum(ktsum_before)
                        .setXtSum(xtsum_before);

                int insertflag= incomeService.updateIncom(tIncome_jl);
                if(insertflag==1){
                    logger.info("t_income表更新当天数据完成");
                }else{
                    logger.info("t_income表更新当天数据失败");
                }
            }
            //开始登记t_income表数据(场地)
            TIncome tIncome_cd_before=incomeService.getIncom(club_id,today);
            if(tIncome_cd_before==null){
                logger.info("t_income表今天没有课程，开始新增当天数据"+tIncome_cd.toString());

                int insertDtl = incomeService.insertIncom(tIncome_cd);
                if(insertDtl==1){
                    logger.info("t_income表新增当天数据完成");
                }else{
                    logger.info("t_income表新增当天数据失败");
                }
            }else{
                logger.info("t_income表今天此类型的课已经有记录"+tIncome_cd_before.toString());
                logger.info("t_income表今天此类型的课已经有记录，开始更新："+tIncome_cd.toString());

                BigDecimal ktcnt_before=tIncome_cd_before.getKtCnt().add(new BigDecimal(1));
                BigDecimal ktsum_before=tIncome_cd_before.getKtSum().add(PRICE.multiply(CD_KTPER));
                tIncome_cd.setKtCnt(ktcnt_before)
//                        .setXtCnt(xtcnt_before)
                        .setKtSum(ktsum_before);
//                        .setXtSum(xtsum_before)
                int insertflag= incomeService.updateIncom(tIncome_cd);
                if(insertflag==1){
                    logger.info("t_income表更新当天数据完成");
                }else{
                    logger.info("t_income表更新当天数据失败");
                }
            }

        }

        return map;

    }

    @RequestMapping(value = "/cancleClass")
    public Map<String,String> cancleClass(@RequestParam("kc_id") String kc_id,
                                          @RequestParam("seq_no") String seq_no){

        logger.info("kc_id ====" + kc_id + "；seq_no====="+seq_no);

        TMemberLessonCancelKey tKey=new TMemberLessonCancelKey();
        tKey.setKcId(kc_id)
                .setSeqNo(Integer.parseInt(seq_no));

        int f = Integer.parseInt(seq_no);
        String resultInfo = "课程取消失败，请联系管理员";
        String result = "none";
        //开始进行对会员课时表的status维护
        MemberLesson memberLesson = new MemberLesson();
        memberLesson.setSeqNo(f)
                .setKcId(kc_id)
                .setStatus(null)
                .setStartTime1(null)
                .setEndTime1(null)
        ;


        Map<String, Object> map1 = memberService.selecctInfoByKcid(memberLesson);
        logger.info(map1.toString());
        Date start_time = new Date();
        Date end_time = new Date();
//        java.sql.Timestamp timestamp= new Timestamp(map1.get("start_time_1"));
        logger.info("map1.get(\"start_time_1\")"+map1.get("start_time_1"));
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try{

            start_time=formatter.parse(map1.get("start_time_1").toString());
            end_time=formatter.parse(map1.get("end_time_1").toString());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        //修改member_lesson表状态
        boolean flag = memberService.cancalClass(memberLesson);
        logger.info("the flag is :"+flag);

//        TMemberLessonCancel tCancel= new TMemberLessonCancel();
        TMemberLessonCancel tCancel= memberService.getMemLesscancel(tKey);

        //原来无记录，新增
        if(tCancel==null){
            tCancel = new TMemberLessonCancel();
            tCancel.setKcId(kc_id)
                    .setSeqNo(f);
            tCancel.setMemId(map1.get("mem_id").toString())
                    .setCancelUser(map1.get("coach_name").toString())
                    .setClubName(map1.get("club_name").toString())
                    .setCoachName(map1.get("coach_name").toString())
                    .setCourseName(map1.get("course_name").toString())
                    .setStartTime1(start_time)
                    .setEndTime1(end_time)
                    .setMemIcon(map1.get("mem_icon").toString())
                    .setCourseType(map1.get("course_type").toString())
                    .setCoachId(map1.get("coach_id").toString())
                    .setClubId(map1.get("club_id").toString())
                    .setBz1(map1.get("bz1").toString())
                    .setCancelState(1)
                    .setCancelReason("教练取消")
                    .setMemName(map1.get("mem_name").toString());
            memberService.addMemLesscancel(tCancel);
        }
        //原来有记录，更新
        else{
            tCancel.setKcId(kc_id)
                    .setSeqNo(f);
            tCancel.setMemId(map1.get("mem_id").toString())
                    .setCancelUser(map1.get("coach_name").toString())
                    .setClubName(map1.get("club_name").toString())
                    .setCoachName(map1.get("coach_name").toString())
                    .setCourseName(map1.get("course_name").toString())
                    .setStartTime1(start_time)
                    .setEndTime1(end_time)
                    .setCoachId(map1.get("coach_id").toString())
                    .setClubId(map1.get("club_id").toString())
                    .setMemIcon(map1.get("mem_icon").toString())
                    .setCourseType(map1.get("course_type").toString())
                    .setBz1(map1.get("bz1").toString())
                    .setCancelState(1)
                    .setCancelReason("教练取消")
                    .setMemName(map1.get("mem_name").toString());
            tCancel.setCancelCount(tCancel.getCancelCount()+1);
            memberService.updateMemLesscancel(tCancel);
        }

        Map<String,String>map = new HashMap<>();
        if(flag){
            result = "success";
            resultInfo = "课程取消成功，请重新排课";
        }
        map.put("result",result);
        map.put("resultInfo",resultInfo);
        return map;

    }


    //对超时未签到的课程，记为旷课
    public Map<String,String> cancleClass1(String kc_id,String seq_no,int state){

        logger.info("kc_id ====" + kc_id + "；seq_no====="+seq_no);

        TMemberLessonCancelKey tKey=new TMemberLessonCancelKey();
        tKey.setKcId(kc_id)
                .setSeqNo(Integer.parseInt(seq_no));

        int f = Integer.parseInt(seq_no);
        String resultInfo = "课程取消失败，请联系管理员";
        String result = "none";
        //开始进行对会员课时表的status维护
        MemberLesson memberLesson = new MemberLesson();
        memberLesson.setSeqNo(f)
                .setKcId(kc_id)
                .setStatus(null)
                .setStartTime1(null)
                .setEndTime1(null)
        ;


        Map<String, Object> map1 = memberService.selecctInfoByKcid(memberLesson);
        logger.info(map1.toString());
        Date start_time = new Date();
        Date end_time = new Date();
//        java.sql.Timestamp timestamp= new Timestamp(map1.get("start_time_1"));
        logger.info("map1.get(\"start_time_1\")"+map1.get("start_time_1"));
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try{

            start_time=formatter.parse(map1.get("start_time_1").toString());
            end_time=formatter.parse(map1.get("end_time_1").toString());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        boolean flag = memberService.cancalClass(memberLesson);
        logger.info("the flag is :"+flag);

//        TMemberLessonCancel tCancel= new TMemberLessonCancel();
        TMemberLessonCancel tCancel= memberService.getMemLesscancel(tKey);

        //原来无记录，新增
        if(tCancel==null){
            tCancel = new TMemberLessonCancel();
            tCancel.setKcId(kc_id)
                    .setSeqNo(f);
            tCancel.setMemId(map1.get("mem_id").toString())
                    .setCancelUser(map1.get("coach_name").toString())
                    .setClubName(map1.get("club_name").toString())
                    .setCoachName(map1.get("coach_name").toString())
                    .setCourseName(map1.get("course_name").toString())
                    .setStartTime1(start_time)
                    .setEndTime1(end_time)
                    .setMemIcon(map1.get("mem_icon").toString())
                    .setCourseType(map1.get("course_type").toString())
                    .setCoachId(map1.get("coach_id").toString())
                    .setClubId(map1.get("club_id").toString())
                    .setBz1(map1.get("bz1").toString())
                    .setCancelState(state)
                    .setCancelReason("旷课取消")
                    .setMemName(map1.get("mem_name").toString());
            memberService.addMemLesscancel(tCancel);
        }
        //原来有记录，更新
        else{
            tCancel.setKcId(kc_id)
                    .setSeqNo(f);
            tCancel.setMemId(map1.get("mem_id").toString())
                    .setCancelUser(map1.get("coach_name").toString())
                    .setClubName(map1.get("club_name").toString())
                    .setCoachName(map1.get("coach_name").toString())
                    .setCourseName(map1.get("course_name").toString())
                    .setStartTime1(start_time)
                    .setEndTime1(end_time)
                    .setCoachId(map1.get("coach_id").toString())
                    .setClubId(map1.get("club_id").toString())
                    .setMemIcon(map1.get("mem_icon").toString())
                    .setCourseType(map1.get("course_type").toString())
                    .setBz1(map1.get("bz1").toString())
                    .setCancelState(state)
                    .setCancelReason("旷课取消")
                    .setMemName(map1.get("mem_name").toString());
            tCancel.setCancelCount(tCancel.getCancelCount()+1);
            memberService.updateMemLesscancel(tCancel);
        }

        Map<String,String>map = new HashMap<>();
        if(flag){
            result = "success";
            resultInfo = "课程取消成功，请重新排课";
        }
        map.put("result",result);
        map.put("resultInfo",resultInfo);
        return map;

    }

}
