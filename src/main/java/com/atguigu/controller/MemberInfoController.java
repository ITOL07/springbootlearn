//package com.atguigu.controller;
//
//import com.atguigu.service.MemberService;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//import javax.annotation.Resource;
//import javax.servlet.http.HttpServletResponse;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//
//@Controller
//@RequestMapping(value = "/coach")
//public class MemberInfoController {
//
//    private static final Logger logger = LoggerFactory.getLogger(MemberInfoController.class);
//
//    @Resource
//    private MemberService memberService;
//
//    @ResponseBody
//    @RequestMapping("/getMemberInfo")
//    public List<Map<Object,Object>> list(@RequestParam("coachid") String coachid, HttpServletResponse response){
//        logger.info("coachid is :"+coachid);
//        List<Map<Object,Object>> list = memberService.selectMemberInfo_1(coachid);
//        return  list;
//    }
//    @ResponseBody
//    @RequestMapping("/course_info")
//    public Map<String,List<String>> selectLessonList(@RequestParam("mem_id") String mem_id, HttpServletResponse response){
//        List<Map<String,String>> list = memberService.selectLessonList(mem_id);
//        logger.info(list.size()+"");
//        List<String> list1 = new ArrayList<>();
//        List<String> list2 = new ArrayList<>();
//        List<String> list3 = new ArrayList<>();
//        for(int i=0;i<list.size();i++){
//            list1.add(list.get(i).get("name"));
//            list2.add(list.get(i).get("course_id"));
//            list3.add(list.get(i).get("kc_id"));
//
//        }
//        Map<String,List<String>> map = new HashMap<>();
//        map.put("course_name",list1);
//        map.put("course_id",list2);
//        map.put("kc_id",list3);
//        return map;
//    }
//
//    @ResponseBody
//    @RequestMapping("/lesson")
//    public List<Map<Object,String>> load(@RequestParam("select_type") String select_type,
//                                         @RequestParam("mem_id") String mem_id,
//                                         @RequestParam("course_id") String course_id,
//                                         @RequestParam("club_id") String club_id,
//                                         @RequestParam("coach_id") String coach_id,
//                                         HttpServletResponse response){
//        logger.info("传入查询类型为："+select_type);
//        //查询类型暂前端处理
//        //Map map = new HashMap();
////        map.put("select_type",select_type);
//
//        List<Map<Object,String>> userIconsList = memberService.selectByLesson(mem_id,course_id,club_id,coach_id);
//        return userIconsList;
//    }
//    @ResponseBody
//    @RequestMapping("/getClubInfo")
//    public Map<String,List<String>> getClubInfo(@RequestParam("course_id") String course_id, HttpServletResponse response){
//        logger.info("course_id :" +course_id);
//        List<Map<String,String>> list = memberService.selectClubList(course_id);
//        logger.info(list.size()+"");
//        List<String> list1 = new ArrayList<>();
//        List<String> list2 = new ArrayList<>();
//        for(int i=0;i<list.size();i++){
//            list1.add(list.get(i).get("club_name"));
//            list2.add(list.get(i).get("club_id"));
//        }
//        Map<String,List<String>> map = new HashMap<>();
//        map.put("club_name",list1);
//        map.put("club_id",list2);
//        return map;
//    }
//}
