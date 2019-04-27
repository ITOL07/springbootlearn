package com.atguigu.controller;


import com.atguigu.entity.Coach;
import com.atguigu.entity.Course;
import com.atguigu.service.CoachService;
import com.atguigu.service.CourseService;
import com.atguigu.service.MemberService;
import com.atguigu.util.CommParams;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Map.Entry;

@RestController
@RequestMapping("/coach")
public class CoachController {

    private Logger logger = LoggerFactory.getLogger(CoachController.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Resource
    private CoachService coachService;
    @Resource
    private MemberService memberService;
    @Resource
    private CourseService courseService;

    @RequestMapping("/getCoach")
    public List<Map<String, Object>> getDbType() {
        String sql = "select * from v_coach order by total_count desc limit 10 ";
//        String sql = "select * from v_coach_lesson ";
        List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
        for (Map<String, Object> map : list) {
            Set<Entry<String, Object>> entries = map.entrySet();
            if (entries != null) {
                Iterator<Entry<String, Object>> iterator = entries.iterator();
                while (iterator.hasNext()) {
                    Entry<String, Object> entry = (Entry<String, Object>) iterator.next();
                    Object key = entry.getKey();
                    Object value = entry.getValue();
                    logger.info(key + ":" + value);
                }
            }
        }
        return list;
    }

    @RequestMapping("/qry")
    public Coach getCoach(
            @RequestParam("coach_id") String coach_id
    ) {
        logger.info("coach_id===" + coach_id);
        Coach coach= coachService.getCoachById(coach_id);
        String icon= CommParams.WEB_URL+coach.getIcon().replaceAll("/app/test","");
        coach.setIcon(icon);
        logger.info(coach.toString());

        return coachService.getCoachById(coach_id);
    }

    @RequestMapping("/update")
    public boolean updateCoach(
            @RequestParam("coach_id") String coach_id,
            @RequestParam("nickName") String nickName,
            @RequestParam("sex") String sex,
            @RequestParam("birthday")  String birth,
            @RequestParam("height") BigDecimal height
    ) {

        logger.info("coach_id===" + coach_id);
        Coach coach=coachService.getCoachById(coach_id);
        if(nickName!=null){
            coach.setNickName(nickName);
        }
        if(sex!=null){
            coach.setSex(sex);
        }
        if(birth!=null){
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date date = null;
            try {
                date = sdf.parse(birth);

            } catch (ParseException e) {
                logger.info("抛出异常");
            }
            coach.setBirthday(date);
        }

        if(height!=null){
            coach.setHeight(height);
        }
        return coachService.updateCoach(coach);
    }

    /**
     *
     * @param coach_id
     * @param try_flag
     * @return 教练的课程信息（售课）
     */
    @RequestMapping("/qryCourse")
    public List<Course> qryCourse(
            @RequestParam("coach_id") String coach_id,
            @RequestParam("try_flag") String try_flag

    ) {
        logger.info("coach_id===" + coach_id+"try_flag===="+try_flag);
        if(!try_flag.equals("")) {
            List<Course> list=coachService.getCourseByCoachId(coach_id,try_flag);
            logger.info("here");
            return list;
        }else{
            List<Course> list=coachService.getCourseByCoachId(coach_id,null);
            logger.info("there");
            return list;
        }
    }

    @RequestMapping("/delCourse")
    public boolean delCourse(
            @RequestParam("course_id") String course_id

    ) {
        logger.info("coach_id===" + course_id);
        return courseService.delCourse(course_id);
    }

    /**
     * 新增课程信息（售课）
     * @param coach_id
     * @param try_flag
     * @param type
     * @param name
     * @param price
     * @param discount
     * @param status
     * @param club_id
     * @return
     */
    @RequestMapping("/addCourse")
    public boolean addCourse(
            @RequestParam("coach_id") String coach_id,
            @RequestParam("try_flag") String try_flag,
            @RequestParam("type") String type,
            @RequestParam("name") String name,
            @RequestParam("price") BigDecimal price,
            @RequestParam("discount") BigDecimal discount,
            @RequestParam("status") String status,
            @RequestParam("club_id") String club_id

    ) {
        logger.info("coach_id===" + coach_id+"try_flag===="+try_flag);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        Course course= new Course();
        Date date=new Date();
        String course_id=new DecimalFormat("0000").format(Integer.parseInt(courseService.getMaxId().substring(8))+1);

        course_id=sdf.format(date)+course_id;
        logger.info("insert course ----- course_id=="+course_id);
        course.setCourseId(course_id)
                .setCoachId(coach_id)
                .setTry_flag(try_flag)
                .setType(type)
                .setName(name)
                .setStatus(Byte.parseByte(status))
                .setClubId(club_id)
                .setPrice(price)
                .setDiscount(discount);
        return courseService.addCourse(course);
    }

    /**
     * 修改课程信息（售课）
     * @param course_id
     * @param coach_id
     * @param try_flag
     * @param type
     * @param name
     * @param price
     * @param discount
     * @param status
     * @param club_id
     * @return
     */
    @RequestMapping("/updateCourse")
    public boolean updateCourse(
            @RequestParam("course_id") String course_id,
            @RequestParam("coach_id") String coach_id,
            @RequestParam("try_flag") String try_flag,
            @RequestParam("type") String type,
            @RequestParam("name") String name,
            @RequestParam("price") BigDecimal price,
            @RequestParam("discount") BigDecimal discount,
            @RequestParam("status") String status,
            @RequestParam("club_id") String club_id

    ) {
        logger.info("coach_id===" + coach_id+"try_flag===="+try_flag);
        Course course= new Course();
        logger.info("update course ----- course_id=="+course_id);
        course.setCourseId(course_id)
                .setCoachId(coach_id)
                .setTry_flag(try_flag)
                .setType(type)
                .setName(name)
                .setStatus(Byte.parseByte(status))
                .setClubId(club_id)
                .setPrice(price)
                .setDiscount(discount);
        return courseService.updateCourse(course);
    }

    @RequestMapping("/qryLesson")
    public List<Map<Object, Object>> qryLesson(
            @RequestParam("coach_id") String coach_id,
            @RequestParam("reg_date") String reg_date,
            @RequestParam("status") Integer status
    ) {
        logger.info("mem_id ====" + coach_id + "status=====" + status);

//        if (status != null) {
//            return coachService.getCoachLessByView(coach_id, status);
//        }
//        else {
//            return coachService.getCoachLessByView_id(coach_id);
//        }
        logger.info("reg_date===="+reg_date);

        if (status != null) {
            return memberService.getMemberLessByViewDate("",coach_id, status.toString(),reg_date);
        }
        else {
            return memberService.getMemberLessByCoachIdDate(coach_id, "",reg_date);
        }



    }

    /**
     *
     * @param coach_id
     * @param reg_date
     * @return 教练日汇总结果
     */
    @RequestMapping("/qryLessReg")
    public List<Map<Object, Object>> qryLesson(
            @RequestParam("coach_id") String coach_id,
            @RequestParam("reg_date") String reg_date
    ) {
        logger.info("mem_id ====" + coach_id + "reg_date=====" + reg_date);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = sdf.parse(reg_date);

        } catch (ParseException e) {
            logger.info("抛出异常");
        }
        return coachService.getCoachIncomeById(coach_id, date);

    }

    @RequestMapping("/qrySum")
    public Map<Object, Object> qrySum(
            @RequestParam("coach_id") String coach_id,
            @RequestParam("reg_date") String reg_date
    ) {
        logger.info("mem_id ====" + coach_id + "reg_date=====" + reg_date);

        Map<Object,Object> map= new HashMap<Object,Object>();
        Map<Object,Object> resMap= new HashMap<Object,Object>();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = sdf.parse(reg_date);

        } catch (ParseException e) {
            logger.info("抛出异常");
        }

        map=coachService.getCoachIncomeSumById(coach_id, date);
        if(map!=null){
            logger.info("map.get(\"les_count_sum\");==========="+map.get("les_count_sum"));

            int les_count_sum=Integer.parseInt(map.get("les_count_sum").toString());
            int sold_count_sum=Integer.parseInt(map.get("les_count_sum").toString());

            float les_total_amt=Float.parseFloat(map.get("les_total_amt").toString());
            float sold_total_amt=Float.parseFloat(map.get("sold_total_amt").toString());

            float les_pct=coachService.getCoachIncomePct(les_count_sum,"JL_KT");
            float sold_pct=coachService.getCoachIncomePct(sold_count_sum,"JL_XT");

            float les_amt=les_total_amt*les_pct;
            float sold_amt=sold_total_amt*sold_pct;

            resMap.put("les_total_amt",les_amt);
            resMap.put("sold_total_amt",sold_amt);

            logger.info("les_count_sum=["+les_count_sum+"]\t les_pct=["+les_pct+"]\t les_amt=["+les_amt+"]\t les_total_amt=["+les_total_amt+"]");
            logger.info("sold_count_sum=["+sold_count_sum+"]\t sold_pct=["+sold_pct+"]\t sold_amt=["+sold_amt+"]\t sold_total_amt=["+sold_total_amt+"]");
            return resMap;
        }else{
            resMap.put("errorNo","-1");
            resMap.put("errorMsg","所查月份无记录");
            return resMap;
        }


    }

    @RequestMapping("/qryMyMember")
    public List<Object> qryMyMember(
            @RequestParam("coach_id") String coach_id
    ) {
        logger.info("mem_id ====" + coach_id );
        List<Map<Object,Object>> list = new ArrayList<>();

        List<Object> resList = new ArrayList<>();
        list=coachService.getMyMemberId(coach_id);
        Iterator it = list.iterator();
        while(it.hasNext()) {
            String mem_id=it.next().toString();
            mem_id=mem_id.substring(mem_id.indexOf("=")+1).replaceAll("}","");
            Map<Object,Object> map=new HashMap<>();
            map.put("MemInfo",memberService.getMemberById(mem_id));
            map.put("LessInfo",qryMyMemSum(mem_id));
//            resList.add(memberService.getMemberById(mem_id));
//            map=qryMyMemSum(mem_id);
            resList.add(map);
            logger.info(mem_id);
        }
        return resList;

    }

    @RequestMapping("/qryMyMemLess")
    public List<Map<Object,Object>> qryMemLesson(
            @RequestParam("mem_id") String mem_id,
            @RequestParam("coach_id") String coach_id,
            @RequestParam("status") String status
    ){
        logger.info("mem_id ===="+mem_id+"coachId==="+coach_id+"status===="+status);

        List<Map<Object,Object>> list = new ArrayList<>();
        list= memberService.getMemberLessByView(mem_id,coach_id,status);
        Map<Object,Object> map=qryMyMemSum(mem_id);
        list.add(map);
        return list;
    }

    /**
     * 获取会员总课时、已完成、已预约、待预约
     * @param mem_id
     * @return
     */
    @RequestMapping("/qryMyMemSum")
    public Map<Object,Object> qryMyMemSum(
            @RequestParam("mem_id") String mem_id
    ){
        logger.info("mem_id ===="+mem_id);
        Map<Object,Object> map = new HashMap<>();
        map=memberService.getMemCourseSum(mem_id);
        logger.info("map.get(rem)==="+map.get("rem"));

        //获取已预约的课时数
        List<Map<Object,Object>> list=new ArrayList<>();
        list=memberService.getMemberLessByView(mem_id,"","0");
        logger.info("list.size()"+list.size());
        map.put("ordered",list.size());
        map.put("ordering",Integer.parseInt(map.get("rem").toString())-list.size());
        return map;
    }

    @ResponseBody
    @RequestMapping("/getMemberInfo")
    public List<Map<Object,Object>> list(@RequestParam("coachid") String coachid, HttpServletResponse response){
        logger.info("coachid is :"+coachid);
        List<Map<Object,Object>> list = memberService.selectMemberInfo_1(coachid);
        return  list;
    }

    @ResponseBody
    @RequestMapping("/course_info")
    public Map<String,List<String>> selectLessonList(@RequestParam("mem_id") String mem_id, HttpServletResponse response){
        List<Map<String,String>> list = memberService.selectLessonList(mem_id);
        logger.info(list.size()+"");
        List<String> list1 = new ArrayList<>();
        List<String> list2 = new ArrayList<>();
        List<String> list3 = new ArrayList<>();
        for(int i=0;i<list.size();i++){
            list1.add(list.get(i).get("name"));
            list2.add(list.get(i).get("course_id"));
            list3.add(list.get(i).get("kc_id"));

        }
        Map<String,List<String>> map = new HashMap<>();
        map.put("course_name",list1);
        map.put("course_id",list2);
        map.put("kc_id",list3);
        return map;
    }
    @ResponseBody
    @RequestMapping("/lesson")
    public List<Map<Object,String>> load(@RequestParam("select_type") String select_type,
                                         @RequestParam("mem_id") String mem_id,
                                         @RequestParam("course_id") String course_id,
                                         @RequestParam("club_id") String club_id,
                                         @RequestParam("coach_id") String coach_id,
                                         HttpServletResponse response){
        logger.info("传入查询类型为："+select_type);
        //查询类型暂前端处理
        //Map map = new HashMap();
//        map.put("select_type",select_type);

        List<Map<Object,String>> userIconsList = memberService.selectByLesson(mem_id,course_id,club_id,coach_id);
        return userIconsList;
    }
    @ResponseBody
    @RequestMapping("/getClubInfo")
    public Map<String,List<String>> getClubInfo(@RequestParam("course_id") String course_id, HttpServletResponse response){
        logger.info("course_id :" +course_id);
        List<Map<String,String>> list = memberService.selectClubList(course_id);
        logger.info(list.size()+"");
        List<String> list1 = new ArrayList<>();
        List<String> list2 = new ArrayList<>();
        for(int i=0;i<list.size();i++){
            list1.add(list.get(i).get("club_name"));
            list2.add(list.get(i).get("club_id"));
        }
        Map<String,List<String>> map = new HashMap<>();
        map.put("club_name",list1);
        map.put("club_id",list2);
        return map;
    }
    /**
     * 根据coach_id获取教练个人信息
     * @param coach_id 教练ID
     */
    @RequestMapping("/getCoachInfo")
    public Coach getCoachInfo(@RequestParam("coach_id") String coach_id,HttpServletResponse response){
        logger.info("coach_id :"+ coach_id);
        Coach coachInfo = coachService.getCoachInfo(coach_id);
        return coachInfo;
    }
}