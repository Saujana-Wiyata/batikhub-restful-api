package com.ecommerce.web.jpa.e_commerce_web_jpa.service.auth;

import java.util.Optional;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.server.ResponseStatusException;

import com.ecommerce.web.jpa.e_commerce_web_jpa.model.auth.AuthRequest;
import com.ecommerce.web.jpa.e_commerce_web_jpa.model.auth.AuthResponse;
import com.ecommerce.web.jpa.e_commerce_web_jpa.repositories.MemberRepository;
import com.ecommerce.web.jpa.e_commerce_web_jpa.repositories.StaffRepository;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;

@Service
@Validated
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final MemberRepository memberRepository;

    private final StaffRepository staffRepository;

    private Optional<AuthResponse> memberLoginByEmailAndPassword(AuthRequest authRequest) {
        return memberRepository.findByEmailAndPassword(authRequest.getEmail(), authRequest.getPassword())
                .map(t -> {
                    t.setToken(UUID.randomUUID().toString().substring(0, 6));
                    t.setTokenExpired("123123123123");
                    return new AuthResponse(t.getToken(), t.getTokenExpired());
                });
    }

    private Optional<AuthResponse> staffLoginByEmailAndPassword(AuthRequest authRequest) {
        return staffRepository.findByEmailAndPassword(authRequest.getEmail(), authRequest.getPassword())
                .map(staff -> {
                    staff.setToken(UUID.randomUUID().toString().substring(0, 6));
                    staff.setTokenExpired("123123123123");
                    return new AuthResponse(staff.getToken(), staff.getTokenExpired());
                });
    }

    private Optional<AuthResponse> memberLoginById(String id) {

        return memberRepository.findById(id).map(member -> {
            member.setToken(UUID.randomUUID().toString().substring(0, 6));
            member.setTokenExpired("123123123123");
            return new AuthResponse(member.getToken(), member.getTokenExpired());
        });
    }

    private Optional<AuthResponse> staffLoginById(String id) {

        return staffRepository.findById(id).map(staff -> {
            staff.setToken(UUID.randomUUID().toString().substring(0, 6));
            staff.setTokenExpired("123123123123");
            return new AuthResponse(staff.getToken(), staff.getTokenExpired());
        });
    }

    private Optional<AuthResponse> memberLogout(String token) {

        return memberRepository.findByToken(token).map(member -> {
            member.setToken(null);
            member.setTokenExpired(null);
            return new AuthResponse(member.getToken(), member.getTokenExpired());
        });
    }

    private Optional<AuthResponse> staffLogout(String token) {

        return staffRepository.findByToken(token).map(staff -> {
            staff.setToken(null);
            staff.setTokenExpired(null);
            return new AuthResponse(staff.getToken(), staff.getTokenExpired());
        });
    }

    @Override
    @Transactional
    public AuthResponse loginByEmailAndPassword(@Valid AuthRequest authRequest) {

        return memberLoginByEmailAndPassword(authRequest)
                .or(() -> staffLoginByEmailAndPassword(authRequest))
                .orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.UNAUTHORIZED,
                                "Unauthorized"));
    }

    @Override
    @Transactional
    public AuthResponse loginById(@NotBlank String id) {

        return memberLoginById(id)
                .or(() -> staffLoginById(id))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Unauthorized"));
    }

    @Override
    @Transactional
    public AuthResponse logout(String token) {

        return memberLogout(token)
                .or(() -> staffLogout(token))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Unauthorized"));
    }
}
