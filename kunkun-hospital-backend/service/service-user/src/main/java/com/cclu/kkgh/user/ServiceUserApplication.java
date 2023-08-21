package com.cclu.kkgh.user;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author ChangCheng Lu
 * @date 2023/8/3 16:40
 */
@SpringBootApplication
@MapperScan("com.cclu.kkgh.user.mapper")
public class ServiceUserApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServiceUserApplication.class, args);
    }
}
