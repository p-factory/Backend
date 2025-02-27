package com.api.pactory.Member.service;

import com.api.pactory.Member.repository.MemberRepository;
import com.api.pactory.domain.Member;
import com.api.pactory.global.utill.exception.CustomException;
import com.api.pactory.global.utill.init.ErrorCode;
import com.api.pactory.global.utill.response.CustomApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
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

            Member member = memberRepository.findByMemberId(loginId)
                    .orElseThrow(() -> new CustomException(ErrorCode.MEMBER_NOT_FOUND));
            System.out.println(Optional.ofNullable(member));
            member.getMemberRoleList().size();
            return Optional.ofNullable(member);
        }

}
