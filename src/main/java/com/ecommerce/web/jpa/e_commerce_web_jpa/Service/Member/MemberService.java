package com.ecommerce.web.jpa.e_commerce_web_jpa.service.member;

import com.ecommerce.web.jpa.e_commerce_web_jpa.dto.member.MemberInputDTO;
import com.ecommerce.web.jpa.e_commerce_web_jpa.dto.member.MemberUpdateDTO;
import com.ecommerce.web.jpa.e_commerce_web_jpa.entities.Member;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;

public interface MemberService {

    void insert(@Valid MemberInputDTO member);

    Member findByEmailAndPassword(@NotBlank String email, @NotBlank String password);

    Member findById(@NotBlank String id);

    Member update(@NotBlank String id, @Valid MemberUpdateDTO member);

    void delete(@NotBlank String id);

}
