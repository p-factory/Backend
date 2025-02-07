package com.api.pactory.global.security;

import com.api.pactory.Member.repository.MemberRepository;
import com.api.pactory.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LoginService implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String loginId) throws UsernameNotFoundException {

        Member member = memberRepository.findByMemberId(loginId)
                .orElseThrow(() -> new UsernameNotFoundException("해당 아이디가 존재하지 않습니다."));

        return org.springframework.security.core.userdetails.User.builder()
                .username(member.getMemberId())
                .authorities((GrantedAuthority) member.getMemberRoleList()
                        .stream()
                        .map(authority -> new SimpleGrantedAuthority(authority.getRole().getAuthority().toString()))
                        .collect(
                                Collectors.toSet()))
                .password(member.getPassword())
                .build();
    }
}