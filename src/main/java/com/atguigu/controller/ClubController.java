package com.atguigu.controller;


import com.alibaba.fastjson.JSONObject;
import com.atguigu.entity.Club;
import com.atguigu.entity.Coach;
import com.atguigu.entity.Course;
import com.atguigu.entity.Member;
import com.atguigu.service.ClubService;
import com.atguigu.service.CoachService;
import com.atguigu.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.Map.Entry;

@RestController
//@RequestMapping("/mydb")
@RequestMapping("/club")
public class ClubController {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Resource
    private ClubService clubService;
    @Resource
    private CourseService courseService;
    @Resource
    private CoachService coachService;

    @RequestMapping("/getClub")
//    @RequestMapping("/qry")
    public List<Map<String, Object>> getDbType() {
        String sql = "select * from club";
        List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
        for (Map<String, Object> map : list) {
            Set<Entry<String, Object>> entries = map.entrySet();
            if (entries != null) {
                Iterator<Entry<String, Object>> iterator = entries.iterator();
                while (iterator.hasNext()) {
                    Entry<String, Object> entry = (Entry<String, Object>) iterator.next();
                    Object key = entry.getKey();
                    Object value = entry.getValue();
                    System.out.println(key + ":" + value);
                }
            }
        }
        return list;
    }

    @RequestMapping("/showClub")
    @ResponseBody
    public Club getUserById(HttpServletRequest request, Model model){
        String userId = request.getParameter("id");
        System.out.println("id====="+userId);
        Club club = this.clubService.getClubById(userId);
        return club;
    }

    @RequestMapping("/qry")
    public Club qryClub(
            @RequestParam("club_id") String club_id
    ){
        Club c= new Club();

        Map<String, String> param = new HashMap<>();
        JSONObject result= new JSONObject();

        System.out.println("club_id ===="+club_id);
        c = clubService.getClubById(club_id);

//        order=orderService.getOrderById("");

        // 封装返回小程序

        return c;
    }

    @RequestMapping("/qryCourse")
    public List<Course> qryClubCourse(
            @RequestParam("club_id") String club_id
    ){
        List<Course> list = courseService.getCourseByClubId(club_id);
        System.out.println("club_id ===="+club_id);

        // 封装返回小程序

        return list;
    }

    @RequestMapping("/qryCoach")
    public List<Coach> qryCoachCourse(
            @RequestParam("club_id") String club_id
    ){
        List<Coach> list = coachService.getCoachByClubId(club_id);
        System.out.println("club_id ===="+club_id);

        // 封装返回小程序

        return list;
    }
}