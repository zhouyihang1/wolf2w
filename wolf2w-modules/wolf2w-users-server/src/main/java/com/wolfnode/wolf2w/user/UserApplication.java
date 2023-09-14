package com.wolfnode.wolf2w.user;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author zhouyihang
 * @version 1.0
 * @description: TODO
 * @date 2023/9/13 23:10
 */
@MapperScan("com.wolfnode.wolf2w.user.mapper")
@SpringBootApplication
public class UserApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserApplication.class, args);
    }
}
