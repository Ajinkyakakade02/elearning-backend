package com.elearning;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ElearningBackendApplication {
    public static void main(String[] args) {
        SpringApplication.run(ElearningBackendApplication.class, args);
        System.out.println("🚀 E-Learning Backend Started on http://localhost:8080");
        System.out.println("📚 API Documentation: http://localhost:8080/swagger-ui.html");
    }
}