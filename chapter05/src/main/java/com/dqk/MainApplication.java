package com.dqk;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
//@ServletComponentScan
//public class MainApplication extends SpringBootServletInitializer {
public class MainApplication {
//    @Override
//    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder){
//        return builder.sources(MainApplication.class);
//    }

    public static void main(String[] args) {
        SpringApplication.run(MainApplication.class, args);
    }
}
