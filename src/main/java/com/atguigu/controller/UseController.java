package com.atguigu.controller;

import com.atguigu.entity.User;
import com.atguigu.service.UserService1;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/user")
public class UseController {
    @Resource
    private UserService1 userService;

    @RequestMapping("/showUser")
    @ResponseBody
    public User getUserById(HttpServletRequest request, Model model){
        String userId = request.getParameter("id");
        System.out.println("id====="+userId);
        User user = this.userService.getUserById(userId);
        return user;
    }
    @RequestMapping("/updateUser")
    @ResponseBody
    public User toIndex2(HttpServletRequest request, Model model){
        System.out.println("request.getParameterMap()"+request.getParameterMap());
        String userId = request.getParameter("id");
        int age=Integer.parseInt(request.getParameter("age"));
        String username=request.getParameter("username");
        String passwd= request.getParameter("passwd");
        System.out.println("id====="+userId+"username"+username);
        User user= new User();
        user.setId(userId);
//        user.setAge(age);
        user.setUserName(username);
        user.setPassword(passwd);
        boolean bool = this.userService.addUser(user);
        return user;
    }

}
