package com.example.analyzedeveloper;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class AnalyzeDeveloperApplication {

    public static void main(String[] args) {
        SpringApplication.run(AnalyzeDeveloperApplication.class, args);
    }

}
