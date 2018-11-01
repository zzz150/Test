package com.yunerp.ribbonserver.Services;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class UserService {   //断路由方式调用类

    @Autowired
    RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "addServiceFallback")
    public String addService() {
        return restTemplate.getForEntity("http://LoadBalancedServer/addtest?a=10&b=20", String.class).getBody();
    }

    public String addServiceFallback() { // 断路由后执行的方法
        return "连接失败，处理错误，然后关闭掉这个坏掉的路由";
    }

}