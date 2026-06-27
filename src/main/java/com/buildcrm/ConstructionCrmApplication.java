package com.buildcrm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class ConstructionCrmApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConstructionCrmApplication.class, args);
    }
}
