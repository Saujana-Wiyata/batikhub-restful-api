package com.ecommerce.web.jpa.e_commerce_web_jpa.controller.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class InterceptorController implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {

        Cookie[] cookies = request.getCookies();

        if (cookies == null) {
            response.sendRedirect("/api/v1/auth/login-emailpassword");
            return false;
        } else {
            return true;
        }
    }

}
