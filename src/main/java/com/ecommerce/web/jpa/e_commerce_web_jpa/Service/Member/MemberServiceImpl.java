package com.ecommerce.web.jpa.e_commerce_web_jpa.Service.Member;

import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.ecommerce.web.jpa.e_commerce_web_jpa.Dto.Member.MemberInputDTO;
import com.ecommerce.web.jpa.e_commerce_web_jpa.Dto.Member.MemberUpdateDTO;
import com.ecommerce.web.jpa.e_commerce_web_jpa.Entities.Member;
import com.ecommerce.web.jpa.e_commerce_web_jpa.Entities.Embed.Alamat;
import com.ecommerce.web.jpa.e_commerce_web_jpa.Repositories.MemberRepository;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;

@Service
@Validated
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

    @Override
    public void insert(@Valid MemberInputDTO member) {

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
    public Member findByEmailAndPassword(@NotBlank String email, @NotBlank String password) {
        return memberRepository.findByEmailAndPassword(email, password);
    }

    @Override
    public Member findById(@NotBlank String id) {
        return memberRepository.findById(id).orElse(null);
    }

    @Override
    public Member update(@NotBlank String id, @Valid MemberUpdateDTO member) {
        Member findById = memberRepository.findById(id).orElse(null);

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

        return memberRepository.save(findById);
    }

    @Override
    public void delete(@NotBlank String id) {
        Member member = memberRepository.findById(id).orElse(null);
        memberRepository.delete(member);
    }

}
