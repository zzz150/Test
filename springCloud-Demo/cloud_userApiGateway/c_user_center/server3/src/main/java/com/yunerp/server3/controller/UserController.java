package com.yunerp.server3.controller;


import com.yunerp.server3.service.Server4Service;
import com.yunerp.server3.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {


    @Autowired
    private Server4Service server4Service;

    @Autowired
    private UserService userService;


    @RequestMapping(value = "/getServer4", method = RequestMethod.GET)
    public String getServer4() {
        return server4Service.add();
    }


    @RequestMapping(value = "/add3", method = RequestMethod.GET)
    public Object add3() {


        return "你好  我是server3";
    }

    @RequestMapping(value = "/getList", method = RequestMethod.GET)
    public Object getList() {
        return userService.SelectList();
    }

}