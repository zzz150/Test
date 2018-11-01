package com.yunerp.feignserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;

@EnableDiscoveryClient
@SpringBootApplication
@EnableFeignClients
public class FeignApplication {
    // Feign接口方式实现负载均衡服务，通过下面链接访问
    // http://localhost:1118/addtest
    public static void main(String[] args) {
        SpringApplication.run(FeignApplication.class, args);
    }

}
