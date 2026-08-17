package com.ecommerce.web.jpa.e_commerce_web_jpa.Global;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.ecommerce.web.jpa.e_commerce_web_jpa.Entities.Member;
import com.ecommerce.web.jpa.e_commerce_web_jpa.Entities.Staff;
import com.ecommerce.web.jpa.e_commerce_web_jpa.Service.Member.MemberService;
import com.ecommerce.web.jpa.e_commerce_web_jpa.Service.Staff.StaffService;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@ControllerAdvice
@RequiredArgsConstructor
public class GlobalNavbar {

    private final StaffService staff;
    private final MemberService member;

    @ModelAttribute
    public void addNavbar(HttpSession session, Model model) {

        if (session != null && session.getAttribute("idEmployee") != null) {

            String staffSession = (String) session.getAttribute("idEmployee");

            Staff staffId = staff.findById(staffSession);

            model.addAttribute("inisialGlobal", staffId.getName()
                    .substring(0, 1).toUpperCase());

            model.addAttribute("staffNameGlobal", staffId.getName()
                    .substring(0, 6));

            model.addAttribute("roleGlobal", staffId.getRole());

        } else if (session != null && session.getAttribute("idMember") != null) {

            String memberSession = (String) session.getAttribute("idMember");

            Member memberId = member.findById(memberSession);

            model.addAttribute("isLoginGlobal", true);

            model.addAttribute("inisialGlobal", memberId.getName()
                    .substring(0, 1).toUpperCase());

            model.addAttribute("memberNameGlobal", memberId.getName()
                    .substring(0, 6));

        } else {
            model.addAttribute("isLoginGlobal", false);
        }

    }

}
