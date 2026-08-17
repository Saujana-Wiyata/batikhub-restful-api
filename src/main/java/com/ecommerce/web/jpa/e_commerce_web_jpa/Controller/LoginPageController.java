package com.ecommerce.web.jpa.e_commerce_web_jpa.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import com.ecommerce.web.jpa.e_commerce_web_jpa.Entities.Member;
import com.ecommerce.web.jpa.e_commerce_web_jpa.Entities.Staff;
import com.ecommerce.web.jpa.e_commerce_web_jpa.Service.Member.MemberService;
import com.ecommerce.web.jpa.e_commerce_web_jpa.Service.Staff.StaffService;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class LoginPageController {

    private final MemberService member;

    private final StaffService staff;

    @GetMapping(path = "/login")
    @ResponseStatus(value = HttpStatus.OK)
    public ModelAndView getPageLogin() {
        return new ModelAndView("loginPage");
    }

    @PostMapping(path = "/loginemailandpassword")
    public ModelAndView postEmailAndPassword(
            @RequestParam String email,
            @RequestParam String password,
            HttpServletRequest request,
            HttpServletResponse response) {

        Member memberResult = member.findByEmailAndPassword(email, password);
        Staff staffResult = staff.findByEmailAndPassword(email, password);

        if (memberResult != null && staffResult == null) {

            String id = memberResult.getId();

            HttpSession session = request.getSession();
            session.setAttribute("idMember", id);

            Cookie cookie = new Cookie("id", id);
            cookie.setPath("/");
            response.addCookie(cookie);

            return new ModelAndView("redirect:/findproduct");

        } else if (memberResult == null && staffResult != null) {

            String id = staffResult.getId();

            HttpSession session = request.getSession();
            session.setAttribute("idEmployee", id);

            Cookie cookie = new Cookie("id", id);
            cookie.setPath("/");
            response.addCookie(cookie);

            return new ModelAndView("redirect:/");

        } else {
            ModelAndView modelAndView = new ModelAndView("/login");
            modelAndView.setStatus(HttpStatus.BAD_GATEWAY);

            return new ModelAndView("/login");
        }
    }

}
