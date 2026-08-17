package com.ecommerce.web.jpa.e_commerce_web_jpa.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
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
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class LoginIDController {

    private final MemberService memberService;

    private final StaffService staffService;

    @GetMapping(path = "/loginbyid")
    @ResponseStatus(value = HttpStatus.OK)
    public ModelAndView getPage() {
        return new ModelAndView("loginIdPage");
    }

    @PostMapping(path = "/findbyid")
    public ModelAndView postMethodName(@RequestParam String id, HttpServletRequest req, HttpServletResponse response) {

        Member member = memberService.findById(id);
        Staff staff = staffService.findById(id);

        if (member != null && staff == null) {

            HttpSession session = req.getSession();
            session.setAttribute("idMember", id);

            Cookie cookie = new Cookie("id", id);
            cookie.setPath("/");
            response.addCookie(cookie);

            return new ModelAndView("redirect:/promo");

        } else if (member == null && staff != null) {

            HttpSession session = req.getSession();
            session.setAttribute("idEmployee", id);

            Cookie cookie = new Cookie("id", id);
            cookie.setPath("/");
            response.addCookie(cookie);

            return new ModelAndView("redirect:/dashboard");

        } else {
            return new ModelAndView("redirect:/loginbyid");
        }
    }

}
