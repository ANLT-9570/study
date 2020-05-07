package com.micro;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync    //开启多线程
public class app {
    public static void main(String[] args) {
        SpringApplication.run(app.class,args);
    }
}
