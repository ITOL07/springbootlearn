package com.atguigu.controller;

import com.atguigu.entity.Course;
import com.atguigu.service.CourseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/course")
public class CourseController {

    private static final Logger logger = LoggerFactory.getLogger(CourseController.class);

    @Resource
    private CourseService courseService;

    /***
     * @param coach_id 教练id
     * getCourseInfo 查询课程信息
     */
    @ResponseBody
    @RequestMapping("/getCourseInfo")
    public Map<String,List<String>> queryCoachInfoIcons(
            @RequestParam("coach_id") String coach_id) {
        System.out.println("coach_id ====" + coach_id);

        return null;

    }
    /***
     * @param coach_id 教练id
     * @param club_id 场地id
     * @param course_type 课程类型
     * getCourseInfo 查询课程信息
     */
    @ResponseBody
    @RequestMapping("/getClubOrCoach")
    public Map<String,List<String>> getClubOrCoach(@RequestParam("club_id") String clubId,
                                       @RequestParam("coach_id") String coachId,
                                       @RequestParam("course_type") String courseType){
        logger.info("传入信息中一项参数为空，参数分别为 coach_id："+coachId+",club_id :"+clubId+",course_type :"+courseType);
        //使用标志判断传入参数哪项为空，coach_id=""则flag：0，否则club_id="",falg:1
        int flag = coachId.equals("")?0:1;

        //查询id和name的list集合
        List<Map<String,String>> courseList = courseService.getClubOrCoach(clubId,coachId,courseType);
        logger.info("=======:" +courseList.size());
        Map<String,List<String>> map = new HashMap<>();
        List<String> list1 = new ArrayList<>();
        List<String> list2 = new ArrayList<>();
        for(int i=0;i<courseList.size();i++){
            if(flag==0){
                list1.add(courseList.get(i).get("coach_id"));
                list2.add(courseList.get(i).get("coach_name"));
            }else{
                list1.add(courseList.get(i).get("club_id"));
                list2.add(courseList.get(i).get("club_name"));
            }
        }
        map.put("id_list",list1);
        map.put("name_list",list2);
        return map;
    }

    @ResponseBody
    @RequestMapping("/getCourseId")
    public Course getCourseId(@RequestParam("club_id") String clubId,
                                                   @RequestParam("coach_id") String coachId,
                                                   @RequestParam("course_type") String courseType){
        logger.info("传入信息中一项参数为空，参数分别为 coach_id："+coachId+",club_id :"+clubId+",course_type :"+courseType);
        //使用标志判断传入参数哪项为空，coach_id=""则flag：0，否则club_id="",falg:1
        return courseService.getCourseId(clubId,coachId,courseType);
    }
}
