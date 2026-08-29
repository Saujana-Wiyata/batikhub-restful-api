package com.ecommerce.web.jpa.e_commerce_web_jpa.controller;

import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.web.jpa.e_commerce_web_jpa.model.WebResponse;
import com.ecommerce.web.jpa.e_commerce_web_jpa.model.member.MemberRequest;
import com.ecommerce.web.jpa.e_commerce_web_jpa.service.member.MemberService;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping(path = "/api/v1/member", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public WebResponse<String> insertNewMember(@RequestBody MemberRequest memberRequest) {

        memberService.insert(memberRequest);
        return WebResponse.<String>builder()
                .code(HttpStatus.OK.value())
                .data("OK")
                .build();
    }

}
