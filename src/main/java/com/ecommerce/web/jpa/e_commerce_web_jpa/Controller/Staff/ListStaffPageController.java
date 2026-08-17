package com.ecommerce.web.jpa.e_commerce_web_jpa.Controller.Staff;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ecommerce.web.jpa.e_commerce_web_jpa.Entities.Enum.Role;
import com.ecommerce.web.jpa.e_commerce_web_jpa.Service.Staff.StaffService;
import com.ecommerce.web.jpa.e_commerce_web_jpa.Utilities.UtilityCookieName;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class ListStaffPageController {

    private final StaffService staffService;

    private List<Map<String, Object>> unpackedAllStaffData(String idCookie) {

        List<Map<String, Object>> list = staffService.findAll().stream()
                .map(staff -> {

                    Map<String, Object> temp = new HashMap<>();

                    temp.put("id", staff.getId());
                    temp.put("name", staff.getName());
                    temp.put("email", staff.getEmail());
                    temp.put("password", staff.getPassword());
                    temp.put("role", staff.getRole());

                    temp.put("inisial", staff.getName()
                            .substring(0, 1).toUpperCase());

                    Role role = staff.getRole();
                    temp.put("isCEO", role.equals(Role.CEO));
                    temp.put("isFinance", role.equals(Role.FINANCE));
                    temp.put("isHR", role.equals(Role.HR));
                    temp.put("isStaff", role.equals(Role.STAFF));

                    temp.put("ownAccount", staff.getId().equals(idCookie));

                    return temp;
                }).toList();

        return list;
    }

    @GetMapping(path = "/liststaff")
    public ModelAndView getPage(@CookieValue(value = "id") String idCookie) {

        UtilityCookieName.setId(idCookie);

        return new ModelAndView("staff/Hr/staffListPage", Map.of(
                "staff", unpackedAllStaffData(idCookie),
                "totalStaff", staffService.findAll().size()));
    }

    @PostMapping(path = "/removestaff")
    public ModelAndView postDeleteStaff(@RequestParam String idStaff) {

        staffService.delete(idStaff);

        return new ModelAndView("redirect:/liststaff");
    }

    @PostMapping(path = "/otherstaffprofile")
    public ModelAndView postMethodName(@RequestParam String idStaff) {

        String id = UtilityCookieName.getId();

        if (idStaff.equals(id)) {
            return new ModelAndView("redirect:/profileemployee");
        } else {
            return new ModelAndView("staff/Hr/otherStaffProfile", Map.of(
                    "staff", staffService.findById(idStaff)));
        }
    }

}
