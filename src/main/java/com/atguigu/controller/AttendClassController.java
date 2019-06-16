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
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
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
            Map<String, String> map1 = memberService.selecctInfoByKcid(memberLesson);
            Course course = courseService.selectByPrimaryKey(map1.get("sale_id"));
            Date date = new Date();
            //装载教练日记表t_coach_lesson_reg实体类
            TCoachLessonReg tCoachLessonReg = new TCoachLessonReg();
            tCoachLessonReg.setRegDate(date).setCourseType(course.getType().toString());
            TCoachLessonReg tCoachLessonReg1 = tCoachLessonRegService.seletNumByDate(tCoachLessonReg);

            int les_count = null!=tCoachLessonReg1?tCoachLessonReg1.getLesCount():0;
            tCoachLessonReg.setCoachId(map1.get("coach_id"))
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
            tClubLessonReg.setClubId(map1.get("club_id"))
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


        Map<String, String> map1 = memberService.selecctInfoByKcid(memberLesson);
        logger.info(map1.toString());
        Date start_time = new Date();
        Date end_time = new Date();
//        java.sql.Timestamp timestamp= new Timestamp(map1.get("start_time_1"));
        logger.info("map1.get(\"start_time_1\")"+map1.get("start_time_1"));
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try{

            start_time=formatter.parse(map1.get("start_time_1"));
            end_time=formatter.parse(map1.get("end_time_1"));
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
            tCancel.setMemId(map1.get("mem_id"))
                    .setCancelUser(map1.get("coach_name"))
                    .setClubName(map1.get("club_name"))
                    .setCoachName(map1.get("coach_name"))
                    .setCourseName(map1.get("course_name"))
                    .setStartTime1(start_time)
                    .setEndTime1(end_time)
                    .setMemIcon(map1.get("mem_icon"))
                    .setCourseType(map1.get("course_type"))
                    .setCoachId(map1.get("coach_id"))
                    .setClubId(map1.get("club_id"))
                    .setBz1(map1.get("bz1"))
                    .setMemName(map1.get("mem_name"));
            memberService.addMemLesscancel(tCancel);
        }
        //原来有记录，更新
        else{
            tCancel.setKcId(kc_id)
                    .setSeqNo(f);
            tCancel.setMemId(map1.get("mem_id"))
                    .setCancelUser(map1.get("coach_name"))
                    .setClubName(map1.get("club_name"))
                    .setCoachName(map1.get("coach_name"))
                    .setCourseName(map1.get("course_name"))
                    .setStartTime1(start_time)
                    .setEndTime1(end_time)
                    .setCoachId(map1.get("coach_id"))
                    .setClubId(map1.get("club_id"))
                    .setMemIcon(map1.get("mem_icon"))
                    .setCourseType(map1.get("course_type"))
                    .setBz1(map1.get("bz1"))
                    .setMemName(map1.get("mem_name"));
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
