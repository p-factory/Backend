package com.api.pactory.Member.service;


import com.api.pactory.Member.dto.ChangePasswordReq;
import com.api.pactory.Member.dto.SignupRequestDto;
import com.api.pactory.domain.Member;
import com.api.pactory.global.utill.response.CustomApiResponse;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

public interface MemberService {
    ResponseEntity<CustomApiResponse<?>> signUp(SignupRequestDto dto);

    void updateRefreshToken(Member member, String reIssuedRefreshToken);
    ResponseEntity<CustomApiResponse<?>> getMyPage(Member member);

    //마이페이지 정보 수정 비밀번호 파트
    ResponseEntity<CustomApiResponse<?>> updatePassword(Member loginMember, ChangePasswordReq changePasswordReq);
}
