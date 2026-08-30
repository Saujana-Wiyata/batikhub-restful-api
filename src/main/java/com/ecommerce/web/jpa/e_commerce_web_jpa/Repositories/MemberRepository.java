package com.ecommerce.web.jpa.e_commerce_web_jpa.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommerce.web.jpa.e_commerce_web_jpa.entities.Member;

public interface MemberRepository extends JpaRepository<Member, String> {

    Optional<Member> findByEmailAndPassword(String email, String password);

    Optional<Member> findByToken(String token);

}
