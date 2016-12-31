package com.chequer.ax5.api.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Ax5ApiAppDemoApplicationInitializer extends SpringBootServletInitializer {

    public static final Object[] APPLICATION_SOURCES = new Object[]{Ax5ApiDemoApplication.class};

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(APPLICATION_SOURCES);
    }

    public static void main(String[] args) {
        SpringApplication.run(APPLICATION_SOURCES, args);
    }
}
