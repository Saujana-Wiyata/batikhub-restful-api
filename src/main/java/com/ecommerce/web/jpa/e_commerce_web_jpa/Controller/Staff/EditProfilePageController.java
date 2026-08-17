package com.ecommerce.web.jpa.e_commerce_web_jpa.Controller.Staff;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;

import com.ecommerce.web.jpa.e_commerce_web_jpa.Dto.Staff.StaffUpdateDTO;
import com.ecommerce.web.jpa.e_commerce_web_jpa.Entities.Staff;
import com.ecommerce.web.jpa.e_commerce_web_jpa.Service.Staff.StaffService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class EditProfilePageController {

    private final StaffService staffService;

    @GetMapping(path = "/editprofileemployee")
    public ModelAndView getPageEditProfile(@CookieValue String id) {

        Staff byId = staffService.findById(id);

        return new ModelAndView("staff/editProfilePage", Map.of(
                "staff", byId));
    }

    @PostMapping(path = "/updatedataemployee")
    public ModelAndView postMethodName(
            @CookieValue(value = "id") String idCookie,
            @ModelAttribute StaffUpdateDTO staff) {

        staffService.update(idCookie, staff);

        return new ModelAndView("redirect:/profileemployee");
    }

}
