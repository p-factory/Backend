package com.api.pactory.Member.service;

import com.api.pactory.Member.dto.SigninResponseDto;
import com.api.pactory.Member.dto.SignupRequestDto;
import com.api.pactory.domain.Member;
import com.api.pactory.global.utill.response.CustomApiResponse;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

public interface MemberService {
    ResponseEntity<CustomApiResponse<?>> signUp(SignupRequestDto dto);
    ResponseEntity<CustomApiResponse<?>> signIn(SigninResponseDto dto);

    Optional<Member> getMemberWithAuthorities(String loginId);
}
