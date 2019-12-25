package com.example.quartz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class QuartzApplication {


    public static void main(String[] args) {
        SpringApplication.run(QuartzApplication.class, args);
    }

}
