package com.yunerp.server4;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;

//@Configuration
//@ComponentScan
//@EnableAutoConfiguration
@EnableEurekaClient
@EnableFeignClients
@SpringBootApplication
public class Server4Application {

    public static void main(String[] args) {
        SpringApplication.run(Server4Application.class, args);
    }
}
/*
 @SpringCloudApplication 注解，之前没有提过，通过源码我们看到，
 它整合了 @SpringBootApplication 、 @EnableDiscoveryClient 、 @EnableCircuitBreaker ，
 主要目的还是简化配置
*/