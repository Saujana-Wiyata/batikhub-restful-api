package com.ecommerce.web.jpa.e_commerce_web_jpa.Controller.WebFilter;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Component
public class WebInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {

        HttpSession session = request.getSession(false);

        if (session == null) {
            response.sendRedirect("/login");
            return false;
        } else {

            String attrMember = (String) session.getAttribute("idMember");
            String attrEmployee = (String) session.getAttribute("idEmployee");

            if (attrMember != null || attrEmployee != null) {

                return true;
            } else {
                response.sendRedirect("/login");
                return false;
            }
        }
    }

}
