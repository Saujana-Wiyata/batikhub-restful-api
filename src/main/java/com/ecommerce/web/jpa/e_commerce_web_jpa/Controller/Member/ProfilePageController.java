package com.ecommerce.web.jpa.e_commerce_web_jpa.Controller.Member;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ecommerce.web.jpa.e_commerce_web_jpa.Entities.Member;
import com.ecommerce.web.jpa.e_commerce_web_jpa.Entities.Embed.Alamat;
import com.ecommerce.web.jpa.e_commerce_web_jpa.Service.Member.MemberService;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class ProfilePageController {

    private final MemberService memberService;

    public Map<String, Object> unpackedDetail(Member member) {
        Map<String, Object> memberDetail = new HashMap<>();

        Alamat alamatMember = member.getAlamat();

        memberDetail.put("id", member.getId());
        memberDetail.put("nama", member.getName());
        memberDetail.put("email", member.getEmail());
        memberDetail.put("password", member.getPassword());
        memberDetail.put("jalan", alamatMember.getJalan());
        memberDetail.put("kota", alamatMember.getKota());
        memberDetail.put("provinsi", alamatMember.getProvinsi());

        return memberDetail;
    }

    @GetMapping(path = "/profile")
    public ModelAndView getPage(@CookieValue(value = "id") String idCookie) {

        Member memberId = memberService.findById(idCookie);
        Map<String, Object> unpackedDetail = unpackedDetail(memberId);

        return new ModelAndView("visitor/profilePage", Map.of(
                "member", unpackedDetail));
    }

    @PostMapping(path = "/logoutmember")
    public ModelAndView postMethodName(HttpServletRequest req, HttpServletResponse res) {

        HttpSession session = req.getSession(false);

        if (session != null) {
            session.invalidate(); // hapus session
        }

        // Hapus cookie
        Cookie cookie = new Cookie("id", null);
        cookie.setMaxAge(0); // timeout cookie
        cookie.setPath("/");
        res.addCookie(cookie);

        return new ModelAndView("redirect:/login");
    }

}
