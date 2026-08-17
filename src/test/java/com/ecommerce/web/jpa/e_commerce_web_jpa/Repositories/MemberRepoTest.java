package com.ecommerce.web.jpa.e_commerce_web_jpa.Repositories;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.ecommerce.web.jpa.e_commerce_web_jpa.Entities.Member;
import com.ecommerce.web.jpa.e_commerce_web_jpa.Entities.Embed.Alamat;

@SpringBootTest
public class MemberRepoTest {

    @Autowired
    private MemberRepository memberRepository;

    @Test
    void insert() {
        Member member = new Member();
        member.setId("M02");
        member.setEmail("test2@gmail.com");
        member.setPassword("rahasia2");
        member.setName("Test2");
        member.setAlamat(new Alamat("jln abc", "Jakarta Selatan", "DKI Jakarta"));

        memberRepository.save(member);
    }

    @Test
    void update() {
        Member member = memberRepository.findById("M01").orElse(null);
        member.setName("Testing");
        memberRepository.save(member);

        Assertions.assertEquals("Testing", memberRepository.findById("M01")
                .orElse(null).getName());

    }

    @Test
    void updateAlamat() {
        Member member = memberRepository.findById("M01").orElse(null);
        Alamat alamat = member.getAlamat();

        alamat.setKota("Badung");
        alamat.setProvinsi("Bali");

        memberRepository.save(member);

        Assertions.assertEquals("Badung", memberRepository.findById("M01")
                .orElse(null).getAlamat().getKota());

        Assertions.assertEquals("Bali", memberRepository.findById("M01")
                .orElse(null).getAlamat().getProvinsi());

        Assertions.assertEquals("jln Semangka", memberRepository.findById("M01")
                .orElse(null).getAlamat().getJalan());

    }

    @Test
    void testFindByIdNotNull() {
        Member member = memberRepository.findById("M02").orElse(null);

        Assertions.assertNotNull(member);
    }

    @Test
    void testFindByIdNull() {
        Member member = memberRepository.findById("2").orElse(null);

        Assertions.assertNull(member);
    }

    @Test
    void testFindByEmailAndPassword() {
        Member member = memberRepository
                .findByEmailAndPassword("test2@gmail.com", "rahasia2");

        Assertions.assertNotNull(member);
    }

    @Test
    void testFindByEmailAndPasswordNull() {
        Member member = memberRepository
                .findByEmailAndPassword("test2@gmail.com", "rahasia");

        Assertions.assertNull(member);
    }

    @Test
    void testFindByEmailAndPasswordNotMatch() {
        Member member = memberRepository
                .findByEmailAndPassword("test@gmail.com", "rahasia2");

        Assertions.assertNull(member);
    }

    @Test
    void testRemove() {
        Member member = memberRepository.findById("M02").orElse(null);

        memberRepository.delete(member);
    }
}
