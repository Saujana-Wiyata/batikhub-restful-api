package com.ecommerce.web.jpa.e_commerce_web_jpa.service.member;

import com.ecommerce.web.jpa.e_commerce_web_jpa.dto.member.MemberRequest;
import com.ecommerce.web.jpa.e_commerce_web_jpa.dto.member.MemberResponse;
import com.ecommerce.web.jpa.e_commerce_web_jpa.dto.member.MemberUpdateRequest;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;

public interface MemberService {

    void insert(@Valid MemberRequest member);

    MemberResponse update(@NotBlank String id, @Valid MemberUpdateRequest member);

    void delete(@NotBlank String id);

}
