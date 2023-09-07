package com.nhnacademy.jpa.service;

import com.nhnacademy.jpa.dto.MemberCreateRequest;
import com.nhnacademy.jpa.dto.MemberId;
import com.nhnacademy.jpa.entity.Authority;
import com.nhnacademy.jpa.entity.Member;
import com.nhnacademy.jpa.repository.MemberRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    public MemberService(MemberRepository memberRepository, PasswordEncoder passwordEncoder) {
        this.memberRepository = memberRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public MemberId createMember(MemberCreateRequest memberCreateRequest){
        Member member = new Member();
        member.setId(memberCreateRequest.getId());
        member.setName(memberCreateRequest.getName());
        member.setPwd(passwordEncoder.encode(memberCreateRequest.getPwd()));

        Authority authority = new Authority();
        authority.setMember(member);
        authority.setAuthority(memberCreateRequest.getAuthority());

        member.setAuthority(authority);

        memberRepository.saveAndFlush(member);

        MemberId memberId = new MemberId();
        memberId.setId(member.getId());

        return memberId;
    }
}
