package com.ecommerce.web.jpa.e_commerce_web_jpa.service.auth;

import java.util.Optional;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.server.ResponseStatusException;

import com.ecommerce.web.jpa.e_commerce_web_jpa.model.auth.AuthRequest;
import com.ecommerce.web.jpa.e_commerce_web_jpa.model.auth.AuthResponse;
import com.ecommerce.web.jpa.e_commerce_web_jpa.repositories.MemberRepository;
import com.ecommerce.web.jpa.e_commerce_web_jpa.repositories.StaffRepository;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Service
@Validated
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final MemberRepository memberRepository;

    private final StaffRepository staffRepository;

    private Optional<AuthResponse> memberLogin(AuthRequest authRequest) {
        return memberRepository.findByEmailAndPassword(authRequest.getEmail(), authRequest.getPassword())
                .map(t -> {
                    t.setToken(UUID.randomUUID().toString().substring(0, 6));
                    t.setTokenExpired("123123123123");
                    return new AuthResponse(t.getToken(), t.getTokenExpired());
                });
    }

    private Optional<AuthResponse> staffLogin(AuthRequest authRequest) {
        return staffRepository.findByEmailAndPassword(authRequest.getEmail(), authRequest.getPassword())
                .map(staff -> {
                    staff.setToken(UUID.randomUUID().toString().substring(0, 6));
                    staff.setTokenExpired("123123123123");
                    return new AuthResponse(staff.getToken(), staff.getTokenExpired());
                });
    }

    @Override
    @Transactional
    public AuthResponse loginByEmailAndPassword(@Valid AuthRequest authRequest) {
        return memberLogin(authRequest)
                .or(() -> staffLogin(authRequest))
                .orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.UNAUTHORIZED,
                                "your email and password incorrect"));
    }

    @Override
    public AuthResponse loginById(String id) {
        return null;
    }

    @Override
    public void logout(String token) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'logout'");
    }

}
