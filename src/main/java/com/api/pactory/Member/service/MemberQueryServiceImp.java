package com.api.pactory.Member.service;

import com.api.pactory.Member.repository.MemberRepository;
import com.api.pactory.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberQueryServiceImp implements MemberQueryService {
    private final MemberRepository memberRepository;
    @Override
    public Optional<Member> getMemberWithAuthorities(String loginId) {
        Member member = memberRepository.findByMemberId(loginId) .orElseThrow(() -> new RuntimeException("해당 회원을 찾을 수 없습니다."));
        System.out.println(Optional.ofNullable(member));
        member.getMemberRoleList().size();
        return Optional.ofNullable(member);
    }
}
