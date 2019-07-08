package com.tensquare.base;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.CrossOrigin;
import util.IdWorker;

/**
 * @author: create by  Raphaelkxy
 * @version: v1.0
 * @description: com.tensquare.com.tensquare.base
 * @date:2019/6/17
 */
@SpringBootApplication
public class Applcation {
    public static void main(String[] args) {
        SpringApplication.run(Applcation.class,args);
    }

    @Bean
    public IdWorker idWorker(){
        return new IdWorker();
    }
}
