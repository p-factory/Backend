package com.api.pactory.Member.service;

import com.api.pactory.Member.dto.SigninResponseDto;
import com.api.pactory.Member.dto.SignupRequestDto;
import com.api.pactory.Member.repository.MemberRepository;
import com.api.pactory.domain.Member;
import com.api.pactory.global.utill.response.CustomApiResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class MemberServiceImp implements MemberService, UserDetailsService {
    private final MemberRepository memberRepository;
    @Override
    public ResponseEntity<CustomApiResponse<?>> signUp(SignupRequestDto dto) {
        return null;
    }

    @Override
    public ResponseEntity<CustomApiResponse<?>> signIn(SigninResponseDto dto) {

        return
    }

    @Override
    public UserDetails loadUserByUsername(String memberId) throws UsernameNotFoundException {
        Member member = memberRepository.findByMemberId(memberId)
                .orElseThrow(() -> new UsernameNotFoundException("해당 아이디가 존재하지 않습니다."));

        return org.springframework.security.core.userdetails.User.builder()
                .username(member.getMemberId())
                .authorities(member.getMemberRoleList()
                        .stream()
                        .map(authority -> new SimpleGrantedAuthority(authority.getRole().getAuthority().toString()))
                        .collect(
                                Collectors.toSet()))
                .password(member.getPassword())
                .build();
    }
    }

