package com.api.pactory.Member.controller;


import com.api.pactory.Member.dto.SignupRequestDto;
import com.api.pactory.Member.service.MemberService;
import com.api.pactory.global.utill.response.CustomApiResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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

}
