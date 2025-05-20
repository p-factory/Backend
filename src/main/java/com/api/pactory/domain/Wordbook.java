package com.api.pactory.domain;

import com.api.pactory.global.utill.init.Shared;
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

    @Column(name = "nickname")
    private String memberName;

    @Column(name = "favorite")
    private boolean favorite; // 즐겨찾기 여부

    @Enumerated(EnumType.STRING)
    private Shared shared;


}
