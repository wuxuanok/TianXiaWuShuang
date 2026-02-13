package com.tianxiawushuang;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan("com.tianxiawushuang.mapper")
public class TianXiaWuShuangApplication {
    public static void main(String[] args) {
        SpringApplication.run(TianXiaWuShuangApplication.class, args);
    }
}
