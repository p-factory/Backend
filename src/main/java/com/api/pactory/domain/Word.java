package com.api.pactory.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "WORD")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Word {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "word", nullable = false)
    private String word; // 단어

    @Embedded
    private WordMeaning wordMeaning; // 뜻 (List<String> 포함)
    @Column(name = "highlight")
    private boolean highlight;

    @ManyToOne
    @JoinColumn(name = "wordbook_id")
    private Wordbook wordbook; // 이 단어가 속한 단어장
}


