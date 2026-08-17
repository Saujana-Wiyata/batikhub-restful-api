package com.ecommerce.web.jpa.e_commerce_web_jpa.Service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.ecommerce.web.jpa.e_commerce_web_jpa.Dto.Member.AlamatDTO;
import com.ecommerce.web.jpa.e_commerce_web_jpa.Dto.Member.MemberInputDTO;
import com.ecommerce.web.jpa.e_commerce_web_jpa.Dto.Member.MemberUpdateDTO;
import com.ecommerce.web.jpa.e_commerce_web_jpa.Entities.Member;
import com.ecommerce.web.jpa.e_commerce_web_jpa.Entities.Embed.Alamat;
import com.ecommerce.web.jpa.e_commerce_web_jpa.Service.Member.MemberService;

import jakarta.validation.ConstraintViolationException;

@SpringBootTest
public class MemberServiceTest {

    @Autowired
    private MemberService memberService;

    @Test
    void testInsert() {

        MemberInputDTO member = new MemberInputDTO();
        member.setName("Aidil Syahmi");
        member.setEmail("aidillongoi@gmail.com");
        member.setPassword("rahasia");
        member.setAlamatDto(new AlamatDTO("jln abc", "Tegalluar",
                "Jawa Barat"));

        memberService.insert(member);
    }

    @Test
    void testInsertFail() {

        MemberInputDTO member = new MemberInputDTO();
        member.setName("Syakir Jamil");
        member.setEmail("syakir@gmail.com");
        member.setPassword("syakir");
        member.setAlamatDto(new AlamatDTO(" ",
                "Surakarta", "Jawa Tengah"));

        Assertions.assertThrows(ConstraintViolationException.class, () -> {
            memberService.insert(member);
        });
    }

    @Test
    void testFindByEmailAndPassword() {
        Member member = memberService
                .findByEmailAndPassword("aidillongoi@gmail.com",
                        "rahasia");

        Assertions.assertNotNull(member);
        Assertions.assertEquals(member.getName(), "Aidil Syahmi");
    }

    @Test
    void testFindByEmailAndPasswordButEmailBlank() {

        Assertions.assertThrows(ConstraintViolationException.class, () -> {
            memberService
                    .findByEmailAndPassword(" ", "rahasia");
        });
    }

    @Test
    void testFindByEmailAndPasswordButPasswordBlank() {
        Assertions.assertThrows(ConstraintViolationException.class, () -> {
            memberService
                    .findByEmailAndPassword("aidillongoi@gmail.com",
                            " ");
        });
    }

    @Test
    void testFindByEmailAndPasswordButBothBlank() {
        Assertions.assertThrows(ConstraintViolationException.class, () -> {
            memberService
                    .findByEmailAndPassword(" ", " ");
        });
    }

    @Test
    void testFindByEmailAndPasswordNotMatch() {
        Member member = memberService
                .findByEmailAndPassword("test@gmail.com",
                        "hihihi");

        Assertions.assertNull(member);
    }

    @Test
    void testFindByIdSuccess() {
        Assertions.assertNotNull(memberService.findById("a757eb"));
    }

    @Test
    void testFindByIdBlank() {
        Assertions.assertThrows(ConstraintViolationException.class, () -> {
            memberService.findById("  ");
        });
    }

    @Test
    void testFindByIdNotFound() {
        Assertions.assertNull(memberService.findById("jasalaj"));
    }

    @Test
    void testUpdateSuccess() {

        MemberUpdateDTO member = new MemberUpdateDTO();
        member.setName("");
        member.setEmail("");
        member.setPassword("");
        member.setAlamat(new Alamat("", "",
                "Riau"));

        Member update = memberService.update("80a542", member);

        Assertions.assertEquals(update.getEmail(), "taufiq@gmail.com");
        Assertions.assertEquals(update.getAlamat().getJalan(), "jln abc");
    }

    @Test
    void testUpdateIdNotFound() {
        MemberUpdateDTO member = new MemberUpdateDTO();
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
        memberService.delete("M01");
    }

    @Test
    void testDeleteFail() {
        Assertions.assertThrows(Exception.class, () -> {
            memberService.delete("M01");
        });
    }
}
