package com.bng.elastic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class ElasticAnalyticsService extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(ElasticAnalyticsService.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(ElasticAnalyticsService.class);
    }
}
