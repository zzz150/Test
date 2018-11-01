package com.yunerp.ribbonserver.controller;

import com.yunerp.ribbonserver.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by mmm on 2016/11/6.
 */
@RestController
public class UserController {


    @Autowired
    private UserService computeService;  // 断路由方式调用申明

    //@Autowired
    //RestTemplate restTemplate;   // 普通方式调用申明

    @RequestMapping(value = "/addtest", method = RequestMethod.GET)
    public String addtest() {
        //普通方式调用
        //return restTemplate.getForEntity("http://LoadBalancedServer/addtest?a=10&b=20", String.class).getBody();
        //断路由方式调用
        return computeService.addService();
    }


}
