package com.winter.controller;

import com.winter.model.UserDomain;
import com.winter.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * @ClassName UserController
 * @Description TODO
 * @Author ZHENZHEN.ZHANG
 * @Date 2018/10/29 13:39
 * @Version 1.0
 */

@Controller
@RequestMapping(value = "/user")
public class UserController {


    @Autowired
    private UserService userService;

    @RequestMapping(value = "/getUserInfo")
    @ResponseBody
    public Object  getUserInfo() {
        Object  user = userService.findAllUser(1,10);
        System.out.println(user.toString());
        return user;
    }

    @RequestMapping(value = "/selectOne")
    @ResponseBody
    public UserDomain  selectOne() {
        UserDomain  user = userService.selectOne(1);
        System.out.println(user);
        return user;
    }

    @RequestMapping(value = "/addUserInfo")
    @ResponseBody
    public String addUserInfo() {
        UserDomain user = new UserDomain();
        user.setUserName("李静");
        user.setPassword("1abcd6");
        user.setPhone("156111111111");
        user.setAddTime(new Date());
        userService.addUser(user);
        System.out.println(user);
        return "success:"+user;
    }

    @RequestMapping(value = "/updateUserInfo")
    public String updateUserInfo() {
        UserDomain user = new UserDomain();
        user.setUserId(1);
        user.setPhone("15688888888");
        userService.updateUser(user);
        System.out.println(user);
        return "success:"+user.toString();
    }


    @RequestMapping(value = "/delUserInfo")
    public String delUserInfo(Integer id) {
        userService.delUser(id);
        System.out.println(id);
        return "success:"+id;
    }
}
