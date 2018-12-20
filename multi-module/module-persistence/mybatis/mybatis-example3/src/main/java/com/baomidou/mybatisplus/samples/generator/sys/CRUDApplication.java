package com.baomidou.mybatisplus.samples.generator.sys;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.baomidou.mybatisplus.samples.generator.sys.mapper")
public class CRUDApplication {

    public static void main(String[] args) {
        SpringApplication.run(CRUDApplication.class, args);
    }

}
