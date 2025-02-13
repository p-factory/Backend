package com.api.pactory.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "WORDBOOK")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Wordbook {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "book_name", nullable = false)
    private String bookName;

    @OneToMany(mappedBy = "wordbook", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Word> words = new HashSet<>(); // 단어를 관리하는 Set

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name ="memberid")
    private Member member;

    @Column(name = "favorite")
    private boolean favorite; // 즐겨찾기 여부
    // 단어장의 소유자를 설정하는 메서드
    public void setMember(Member member) {
        this.member = member;
    }

}
