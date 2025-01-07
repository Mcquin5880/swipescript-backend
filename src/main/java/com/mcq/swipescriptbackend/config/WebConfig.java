package com.mcq.swipescriptbackend.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    private final LastActiveInterceptor lastActiveInterceptor;

    @Autowired
    public WebConfig(LastActiveInterceptor lastActiveInterceptor) {
        this.lastActiveInterceptor = lastActiveInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(lastActiveInterceptor)
                .excludePathPatterns("/login", "/register");
    }
}
