package com.api.pactory.Member.repository;

import com.api.pactory.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository  {
    Optional<Member> findByMemberId(String memberId);

    Optional<Member> findByLoginId(String loginId);

    Optional<Member> findByRefreshToken(String refreshToken);
}
