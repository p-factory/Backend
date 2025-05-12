package com.api.pactory.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@RequiredArgsConstructor
@AllArgsConstructor
@Table(name = "WordPactory")
@Builder
public class WordPactory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nickname")
    private String nickname;

    @Column(name = "book_name", nullable = false)
    private String bookName;

    @OneToMany(mappedBy = "wordpactory",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private Set<Word> words = new HashSet<>();

    @Column(name = "favorite")
    private boolean favorite;


}
