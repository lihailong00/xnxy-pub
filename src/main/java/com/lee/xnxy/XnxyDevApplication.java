package com.lee.xnxy;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author 晓龙coding
 */
@MapperScan("com.lee.xnxy.mapper")
@SpringBootApplication
public class XnxyDevApplication {

    public static void main(String[] args) {
        SpringApplication.run(XnxyDevApplication.class, args);
    }

}
