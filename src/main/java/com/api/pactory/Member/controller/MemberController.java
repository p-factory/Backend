package com.api.pactory.Member.controller;


import com.api.pactory.Member.dto.ChangeNicknameReq;
import com.api.pactory.Member.dto.ChangePasswordReq;
import com.api.pactory.Member.dto.SignupRequestDto;
import com.api.pactory.Member.service.MemberService;
import com.api.pactory.domain.Member;
import com.api.pactory.global.security.LoginMember;
import com.api.pactory.global.utill.response.CustomApiResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class MemberController {
    private final MemberService memberService;

    @PostMapping("/signup")
    public ResponseEntity<CustomApiResponse<?>> signUp(@RequestBody SignupRequestDto dto) {
        ResponseEntity<CustomApiResponse<?>> response = memberService.signUp(dto);
        return response;
    }
    @GetMapping("/mypage")
    public ResponseEntity<CustomApiResponse<?>> getMyPage(@LoginMember Member loginMember) {
        ResponseEntity<CustomApiResponse<?>> response =memberService.getMyPage(loginMember);
        return response;
    }


    //마이페이지 비밀번호 수정 부분
    @PutMapping("/mypage/password")
    public ResponseEntity<CustomApiResponse<?>> changePassword(@LoginMember Member loginMember,
                                                               @RequestBody ChangePasswordReq changePasswordReq){
        //updatePassword에 로그인한 회원정보와, RequestBody로 받아온 비밀번호들을 넘겨준다.
        ResponseEntity<CustomApiResponse<?>> response = memberService.updatePassword(loginMember, changePasswordReq);
        return response;
    }

    //마이페이지 닉네임 수정 부분
    @PutMapping("/mypage/nickname")
    public ResponseEntity<CustomApiResponse<?>> changeNickname(@LoginMember Member loginMember,
                                                               @RequestBody ChangeNicknameReq changeNicknameReq){
        return null;
    }


}
