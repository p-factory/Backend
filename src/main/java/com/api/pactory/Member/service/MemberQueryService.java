package com.api.pactory.Member.service;

import com.api.pactory.domain.Member;

import java.util.Optional;

public interface MemberQueryService {
    Optional<Member> getMemberWithAuthorities(String loginId);
}
