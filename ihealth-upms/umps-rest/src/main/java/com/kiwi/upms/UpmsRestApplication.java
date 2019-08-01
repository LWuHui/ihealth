package com.kiwi.upms;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author jenny
 * @date 2019/6/4 23:02
 */
@SpringBootApplication
@MapperScan("com.kiwi.upms.mapper")
public class UpmsRestApplication {

    public static void main(String[] args) {
        SpringApplication.run(UpmsRestApplication.class, args);
    }
}
