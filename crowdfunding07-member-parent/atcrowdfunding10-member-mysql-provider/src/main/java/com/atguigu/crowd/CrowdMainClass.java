package com.atguigu.crowd;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author BobJiang
 * @version 1.0
 * @date 2021-02-20 15:17
 */

@MapperScan("com.atguigu.crowd.mapper")
@SpringBootApplication
public class CrowdMainClass {
    public static void main(String[] args){
        SpringApplication.run(CrowdMainClass.class,args);
    }
}
