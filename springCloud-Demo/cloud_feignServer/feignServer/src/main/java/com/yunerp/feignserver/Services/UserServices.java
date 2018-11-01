package com.yunerp.feignserver.Services;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

//@FeignClient(LoadBalancedServer)  //非断路由方式调用
@FeignClient(value = "LoadBalancedServer", fallback = UserClientHystrix.class)
public interface UserServices {

    @RequestMapping(method = RequestMethod.GET, value = "/addtest")
    Integer add(@RequestParam(value = "a") Integer a, @RequestParam(value = "b") Integer b);

}