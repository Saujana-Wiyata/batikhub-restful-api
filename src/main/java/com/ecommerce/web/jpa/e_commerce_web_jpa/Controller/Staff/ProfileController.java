package com.ecommerce.web.jpa.e_commerce_web_jpa.Controller.Staff;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ecommerce.web.jpa.e_commerce_web_jpa.Entities.Staff;
import com.ecommerce.web.jpa.e_commerce_web_jpa.Entities.Enum.Role;
import com.ecommerce.web.jpa.e_commerce_web_jpa.Service.Staff.StaffService;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class ProfileController {

    private final StaffService staffService;

    @GetMapping(path = "/profileemployee")
    public ModelAndView getPageProfile(@CookieValue String id) {

        Staff byId = staffService.findById(id);

        if (!byId.getRole().equals(Role.STAFF) && !byId.getRole().equals(Role.FINANCE)) {

            return new ModelAndView("staff/Hr/profilePage", Map.of(
                    "staff", byId));
        } else {
            return new ModelAndView("staff/profilePage", Map.of(
                    "staff", byId));
        }
    }

    @PostMapping(path = "/logoutstaff")
    public ModelAndView postLogout(HttpServletRequest req, HttpServletResponse res) {

        HttpSession session = req.getSession();
        if (session != null) {
            session.invalidate();
        }

        Cookie cookie = new Cookie("id", null);
        cookie.setPath("/");
        cookie.setMaxAge(0);
        res.addCookie(cookie);

        return new ModelAndView("redirect:/login");
    }

}
