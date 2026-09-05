package com.ecommerce.web.jpa.e_commerce_web_jpa.controller.interceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class InterceptorControllerConfig implements WebMvcConfigurer {

    private final InterceptorController interceptorController;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        registry.addInterceptor(interceptorController).addPathPatterns("/**")
                .excludePathPatterns(
                        "/api/v1/auth/login-emailpassword",
                        "/api/v1/auth/login-id",
                        "/api/v1/products",
                        "/api/v1/products/search");
    }
}
