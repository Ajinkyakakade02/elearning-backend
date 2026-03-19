package com.elearning;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;

@SpringBootApplication
public class ElearningBackendApplication {
    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(ElearningBackendApplication.class);
        Environment env = app.run(args).getEnvironment();

        System.out.println("🚀 E-Learning Backend Started on http://localhost:" + env.getProperty("server.port", "8080"));
        System.out.println("📚 API Documentation: http://localhost:" + env.getProperty("server.port", "8080") + "/swagger-ui.html");
        System.out.println("🌍 Active Profile: " + String.join(", ", env.getActiveProfiles()));
    }
}