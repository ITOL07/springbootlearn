package com.atguigu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class helloController {

    @ResponseBody
    @RequestMapping("/hello")
    public String hello() {
        return "hello World!";
    }
}

