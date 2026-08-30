package com.ecommerce.web.jpa.e_commerce_web_jpa.service.member;

import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.server.ResponseStatusException;

import com.ecommerce.web.jpa.e_commerce_web_jpa.entities.Member;
import com.ecommerce.web.jpa.e_commerce_web_jpa.entities.embed.Alamat;
import com.ecommerce.web.jpa.e_commerce_web_jpa.model.member.MemberRequest;
import com.ecommerce.web.jpa.e_commerce_web_jpa.model.member.MemberResponse;
import com.ecommerce.web.jpa.e_commerce_web_jpa.model.member.MemberUpdateRequest;
import com.ecommerce.web.jpa.e_commerce_web_jpa.repositories.MemberRepository;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;

@Service
@Validated
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

    @Override
    @Transactional
    public void insert(@Valid MemberRequest member) {

        Member memberEntity = new Member();
        memberEntity.setId(UUID.randomUUID().toString()
                .substring(0, 6));
        memberEntity.setName(member.getName());
        memberEntity.setEmail(member.getEmail());
        memberEntity.setPassword(member.getPassword());
        memberEntity.setAlamat(new Alamat(member.getAlamatDto().getJalan(),
                member.getAlamatDto().getKota(), member.getAlamatDto().getProvinsi()));

        memberRepository.save(memberEntity);
    }

    @Override
    @Transactional
    public MemberResponse update(@NotBlank String token, @Valid MemberUpdateRequest member) {
        Member findById = memberRepository.findByToken(token)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Fail to update your data"));

        Alamat alamatMember = member.getAlamat();
        Alamat alamatFindById = findById.getAlamat();

        if (!member.getName().isBlank())
            findById.setName(member.getName());

        if (!member.getEmail().isBlank())
            findById.setEmail(member.getEmail());

        if (!member.getPassword().isBlank())
            findById.setPassword(member.getPassword());

        if (!alamatMember.getJalan().isBlank())
            alamatFindById.setJalan(alamatMember.getJalan());

        if (!alamatMember.getKota().isBlank())
            alamatFindById.setKota(alamatMember.getKota());

        if (!alamatMember.getProvinsi().isBlank())
            alamatFindById.setProvinsi(alamatMember.getProvinsi());

        Member memberSave = memberRepository.save(findById);

        return new MemberResponse(memberSave.getName(), memberSave.getEmail(), memberSave.getAlamat());
    }

    @Override
    @Transactional
    public void delete(@NotBlank String token) {
        Member member = memberRepository.findByToken(token).orElseThrow(() -> {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Fail to delete your account");
        });
        memberRepository.delete(member);
    }

}
