package com.api.pactory.Member.service;

import com.api.pactory.Member.dto.SigninResponseDto;
import com.api.pactory.Member.dto.SignupRequestDto;
import com.api.pactory.global.utill.response.CustomApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class MemberServiceImp implements MemberService {
    @Override
    public ResponseEntity<CustomApiResponse<?>> signUp(SignupRequestDto dto) {
        return null;
    }

    @Override
    public ResponseEntity<CustomApiResponse<?>> signIn(SigninResponseDto dto) {
        return null;
    }
}
