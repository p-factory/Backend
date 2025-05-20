package com.api.pactory.Member.service;


import com.api.pactory.Member.dto.SigninResponseDto;
import com.api.pactory.Member.dto.SignupRequestDto;
import com.api.pactory.Member.enums.Authority;
import com.api.pactory.Member.repository.MemberRepository;
import com.api.pactory.Member.repository.RoleRepository;
import com.api.pactory.domain.Member;
import com.api.pactory.domain.MemberRole;
import com.api.pactory.domain.Role;
import com.api.pactory.global.security.JwtService;
import com.api.pactory.global.utill.response.CustomApiResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Optional;

import static com.api.pactory.Member.enums.Authority.ROLE_ADMIN;
import static com.api.pactory.Member.enums.Authority.ROLE_USER;


@Service
@RequiredArgsConstructor
@Transactional
public class MemberServiceImp implements MemberService {
    private final MemberRepository memberRepository;
    private final JwtService jwtService;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public ResponseEntity<CustomApiResponse<?>> signUp(SignupRequestDto dto) {
        if (memberRepository.existsByMemberId(dto.getMemberId())) {
            CustomApiResponse<?> response = CustomApiResponse.createFailWithout(HttpStatus.BAD_REQUEST.value(), "중복되는 아이디가 존재합니다");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }

        // 비밀번호 암호화
        String encodedPassword = passwordEncoder.encode(dto.getPassword());
        Member member = Member.toMember(dto, encodedPassword, jwtService.createRefreshToken());
        // 역할 확인
        Optional<Role> optionalRole = roleRepository.findById(ROLE_USER.getId());
        if (!optionalRole.isPresent()) {
            // 역할이 존재하지 않는 경우 처리
            CustomApiResponse<?> response = CustomApiResponse.createFailWithout(HttpStatus.BAD_REQUEST.value(), "존재하지 않는 역할입니다");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
        Role role = optionalRole.get();

        MemberRole memberRole = MemberRole.builder()
                .role(role) // 역할을 설정
                .member(member) // 회원 설정
                .build();

        member.addMemberRole(memberRole);

        // 저장
        memberRepository.save(member);

        CustomApiResponse<?> response = CustomApiResponse.createSuccessWithoutData(HttpStatus.OK.value(), "회원가입에 성공하였습니다");
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @Override
    public void updateRefreshToken(Member member, String reIssuedRefreshToken) {
        member.changeRefreshToken(reIssuedRefreshToken);
        memberRepository.saveAndFlush(member);
    }


    private static boolean isWithinFiveMinute(LocalDateTime timeToCheck) {
        LocalDateTime now = LocalDateTime.now();
        Duration duration = Duration.between(timeToCheck, now);
        return Math.abs(duration.toMinutes()) <= 5;
    }

    @Override
    public ResponseEntity<CustomApiResponse<?>> getMyPage(Member member) {
        SigninResponseDto signinResponseDto =new SigninResponseDto(member.getMemberId(), member.getNickname(), member.getPassword());
        CustomApiResponse<?> response = CustomApiResponse.createSuccess(HttpStatus.OK.value(), signinResponseDto,"회원 조회에 성공하였습니다.");
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }


    }

