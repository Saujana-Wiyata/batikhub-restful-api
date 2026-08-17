package com.ecommerce.web.jpa.e_commerce_web_jpa.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommerce.web.jpa.e_commerce_web_jpa.Entities.Member;

public interface MemberRepository extends JpaRepository<Member, String> {

    Member findByEmailAndPassword(String email, String password);

}
