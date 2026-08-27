package com.ecommerce.web.jpa.e_commerce_web_jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommerce.web.jpa.e_commerce_web_jpa.entities.Member;

public interface MemberRepository extends JpaRepository<Member, String> {

    Member findByEmailAndPassword(String email, String password);

}
