package com.wzz.oa1;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.wzz.oa1.dao")
public class Oa1Application {

    public static void main(String[] args) {
        SpringApplication.run(Oa1Application.class, args);
    }

}
