package com.ecommerce.web.jpa.e_commerce_web_jpa.Controller.Staff;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;

import com.ecommerce.web.jpa.e_commerce_web_jpa.Dto.Staff.StaffRequestDTO;
import com.ecommerce.web.jpa.e_commerce_web_jpa.Service.Staff.StaffService;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class AddStaffController {

    private final StaffService staffService;

    @GetMapping(path = "/addemployee")
    public ModelAndView getPage() {
        return new ModelAndView("staff/Hr/addStaffPage");
    }

    @PostMapping(path = "/insertemployee")
    public ModelAndView postDataEmployee(@ModelAttribute StaffRequestDTO staff) {
        staffService.insert(staff);

        return new ModelAndView("redirect:/profileemployee");
    }

}
