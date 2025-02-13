package com.api.pactory.domain;

import com.api.pactory.Member.dto.SignupRequestDto;
import com.api.pactory.Member.dto.SignupResponseDto;
import com.api.pactory.global.utill.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name="MEMBERS")
@Getter
@Setter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name="nickname", nullable=false, unique=true)
    private String nickname;

    @Column(name="memberId",nullable=false, unique=true)
    private String memberId;

    @Column(name="password", nullable=false)
    private String password;

    @Column(name = "refresh_token")
    private String refreshToken;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MemberRole> memberRoleList = new ArrayList<>();
    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Wordbook> wordbookList = new ArrayList<>();
    

    public void changeRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }
    public static Member toMember(SignupRequestDto request, String encodedPassword,
                                  String refreshToken) {
        return Member.builder()
                .memberId(request.getMemberId())
                .password(encodedPassword)
                .refreshToken(refreshToken)
                .nickname(request.getName())
                .memberRoleList(new ArrayList<>())
                .build();

    }

    public static SignupResponseDto toSignupResponseDto(Member member) {
        return SignupResponseDto.builder()
                .id(member.getId())
                .createdAt(member.getCreatedAt())
                .build();
    }


    public void addMemberRole(MemberRole memberRole) {
        memberRoleList.add(memberRole);
        memberRole.setMember(this);
    }
    // 단어장 교환 메서드 추가
    public void exchangeWordbook(Member targetMember, Wordbook wordbook) {
        // 현재 사용자의 단어장을 상대방에게 전달
        targetMember.addWordbook(wordbook);

        // 현재 사용자의 단어장에서 해당 단어장 삭제
        this.wordbookList.remove(wordbook);
        wordbook.setMember(targetMember); // 단어장의 소유자를 변경
    }

    public void addWordbook(Wordbook wordbook) {
        this.wordbookList.add(wordbook);
        wordbook.setMember(this); // 단어장의 소유자를 현재 사용자로 설정
    }
}
