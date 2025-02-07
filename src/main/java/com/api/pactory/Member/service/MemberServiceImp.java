package com.api.pactory.Member.service;


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
import java.util.Optional;

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
        Role role = roleRepository.findById(ROLE_USER.getId()).orElseThrow(RuntimeException::new);
        MemberRole memberRole = MemberRole.builder().build();
        role.addMemberRole(memberRole);
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


    }

