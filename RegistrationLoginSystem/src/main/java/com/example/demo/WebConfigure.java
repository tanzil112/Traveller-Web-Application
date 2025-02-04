package com.example.demo;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfigure implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/**")  // Allow CORS for all /api/** endpoints
               .allowedOrigins("http://localhost:5173")  // Allow requests from frontend
               .allowedMethods("GET", "POST", "PUT", "DELETE")  // Allow specific HTTP methods
               .allowedHeaders("*");  // Allow all headers
    }
}
