package com.example.notesbackend.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Web MVC configuration to register custom interceptors.
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    private final ApiDocsLoggingInterceptor apiDocsLoggingInterceptor;

    public WebMvcConfig(ApiDocsLoggingInterceptor apiDocsLoggingInterceptor) {
        this.apiDocsLoggingInterceptor = apiDocsLoggingInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // Register interceptor for API docs endpoints
        registry.addInterceptor(apiDocsLoggingInterceptor)
                .addPathPatterns("/api-docs", "/api-docs/**", "/v3/api-docs", "/v3/api-docs/**");
    }
}
