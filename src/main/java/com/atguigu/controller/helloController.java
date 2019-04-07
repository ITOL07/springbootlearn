package com.atguigu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


import javax.servlet.http.HttpServletRequest;

/**
 * 万能的helloWorld，入门
 */
@Controller
public class helloController {

    @ResponseBody
    @RequestMapping("/payResult")
    public void getUserById(HttpServletRequest request, Model model){
        //int userId = Integer.parseInt(request.getParameter("id"));
        System.out.println("request====="+request.getQueryString());

    }
}

