package com.ecommerce.web.jpa.e_commerce_web_jpa.Controller.WebFilter;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class WebInterceptorConfig implements WebMvcConfigurer {

    private final WebInterceptor webInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        registry.addInterceptor(webInterceptor).addPathPatterns("/**")
                .excludePathPatterns(
                        "/signup", "/login", "/loginbyid",
                        "/promo", "/findproduct", "/findbyname",
                        "/findbyid", "/loginemailandpassword",
                        "/insertmember");
    }
}
