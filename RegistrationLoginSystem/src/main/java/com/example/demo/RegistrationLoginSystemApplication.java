package com.example.demo; // Your base package

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.example.demo") // Include your package here
public class RegistrationLoginSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(RegistrationLoginSystemApplication.class, args);
    }
}

