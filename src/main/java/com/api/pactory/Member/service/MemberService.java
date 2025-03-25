package com.api.pactory.Member.service;


import com.api.pactory.Member.dto.SignupRequestDto;
import com.api.pactory.domain.Member;
import com.api.pactory.global.utill.response.CustomApiResponse;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

public interface MemberService {
    ResponseEntity<CustomApiResponse<?>> signUp(SignupRequestDto dto);

    void updateRefreshToken(Member member, String reIssuedRefreshToken);
    ResponseEntity<CustomApiResponse<?>> getMyPage(Member member);

}
