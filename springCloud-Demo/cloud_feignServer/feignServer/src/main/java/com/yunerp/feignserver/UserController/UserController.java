package com.yunerp.feignserver.UserController;

import com.yunerp.feignserver.Services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    UserServices userClient;

    @RequestMapping(value = "/addtest", method = RequestMethod.GET)
    public Integer add() {
        return userClient.add(10, 20);
    }

}