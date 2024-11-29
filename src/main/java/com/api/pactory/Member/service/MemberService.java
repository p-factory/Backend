package com.api.pactory.Member.service;

import com.api.pactory.Member.dto.SigninResponseDto;
import com.api.pactory.Member.dto.SignupRequestDto;
import com.api.pactory.global.utill.response.CustomApiResponse;
import org.springframework.http.ResponseEntity;

public interface MemberService {
    ResponseEntity<CustomApiResponse<?>> signUp(SignupRequestDto dto);
    ResponseEntity<CustomApiResponse<?>> signIn(SigninResponseDto dto);
}
