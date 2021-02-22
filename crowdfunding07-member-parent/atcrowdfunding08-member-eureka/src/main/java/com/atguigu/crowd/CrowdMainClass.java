package com.atguigu.crowd;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @author BobJiang
 * @version 1.0
 * @date 2021-02-20 15:17
 */

@EnableEurekaServer
@SpringBootApplication
public class CrowdMainClass {
    public static void main(String[] args){
        SpringApplication.run(CrowdMainClass.class,args);
    }
}
