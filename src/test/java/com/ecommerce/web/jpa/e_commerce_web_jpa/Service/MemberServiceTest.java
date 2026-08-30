package com.ecommerce.web.jpa.e_commerce_web_jpa.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.server.ResponseStatusException;

import com.ecommerce.web.jpa.e_commerce_web_jpa.entities.embed.Alamat;
import com.ecommerce.web.jpa.e_commerce_web_jpa.model.member.AlamatInsert;
import com.ecommerce.web.jpa.e_commerce_web_jpa.model.member.MemberRequest;
import com.ecommerce.web.jpa.e_commerce_web_jpa.model.member.MemberResponse;
import com.ecommerce.web.jpa.e_commerce_web_jpa.model.member.MemberUpdateRequest;
import com.ecommerce.web.jpa.e_commerce_web_jpa.service.member.MemberService;

import jakarta.validation.ConstraintViolationException;

@SpringBootTest
public class MemberServiceTest {

    @Autowired
    private MemberService memberService;

    @Test
    void testInsert() {

        MemberRequest member = new MemberRequest();
        member.setName("Aidil Syahmi");
        member.setEmail("aidillongoi@gmail.com");
        member.setPassword("rahasia");
        member.setAlamatDto(new AlamatInsert("jln abc", "Tegalluar",
                "Jawa Barat"));

        memberService.insert(member);
    }

    @Test
    void testInsertFail() {

        MemberRequest member = new MemberRequest();
        member.setName("Syakir Jamil");
        member.setEmail("syakir@gmail.com");
        member.setPassword("syakir");
        member.setAlamatDto(new AlamatInsert("",
                "Surakarta", "Jawa Tengah"));

        Assertions.assertThrows(ConstraintViolationException.class, () -> {
            memberService.insert(member);
        });
    }

    @Test
    void testUpdateSuccess() {

        MemberUpdateRequest member = new MemberUpdateRequest();
        member.setName("");
        member.setEmail("");
        member.setPassword("");
        member.setAlamat(new Alamat("", "",
                "Riau"));

        MemberResponse update = memberService.update("e9804b", member);

        Assertions.assertEquals(update.getEmail(), "syakirlongoi@gmail.com"); // not change
        Assertions.assertEquals(update.getAlamat().getProvinsi(), "Riau"); // change
    }

    @Test
    void testUpdateIdNotFound() {
        MemberUpdateRequest member = new MemberUpdateRequest();
        member.setName("");
        member.setEmail("");
        member.setPassword("");
        member.setAlamat(new Alamat("", "",
                "Riau"));

        Assertions.assertThrows(Exception.class, () -> {
            memberService.update("jjj123", member);

        });
    }

    @Test
    void testDeleteSuccess() {
        memberService.delete("58efe8");
    }

    @Test
    void testDeleteFail() {
        Assertions.assertThrows(ResponseStatusException.class, () -> {
            memberService.delete("M01");
        });
    }
}
