package com.ecommerce.web.jpa.e_commerce_web_jpa.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ecommerce.web.jpa.e_commerce_web_jpa.Dto.Member.MemberInputDTO;
import com.ecommerce.web.jpa.e_commerce_web_jpa.Service.Member.MemberService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class SignUpController {

    private final MemberService memberService;

    @GetMapping(path = "/signup")
    public ModelAndView getPage() {
        return new ModelAndView("signUpPage");
    }

    @PostMapping(path = "/insertmember")
    public ModelAndView postPageSignup(@ModelAttribute MemberInputDTO member) {

        memberService.insert(member);

        return new ModelAndView("redirect:/login");
    }

}
