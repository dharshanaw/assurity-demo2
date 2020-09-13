package com.assurity.demo2.service;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Collections;

@SpringBootApplication
public class EmployeeApplication {

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(EmployeeApplication.class);
        app.setDefaultProperties(Collections
                .singletonMap("server.port", "8083"));
        app.run(args);
    }
}
