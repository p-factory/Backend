package com.api.pactory.word.dto;
import com.api.pactory.domain.Word;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class WordbookDtoWithWords {
    private Long wordbookId;
    private String wordbookName;
    private boolean favorite;
    private List<WordDto> words;  // 단어 목록
    private long totalElements; // 전체 단어 수
    private int totalPages;
    public WordbookDtoWithWords(Long wordbookId, String wordbookName, boolean favorite, List<WordDto> words, long totalElements, int totalPages) {
        this.wordbookId = wordbookId;
        this.wordbookName = wordbookName;
        this.favorite = favorite;
        this.words = words;
        this.totalElements = totalElements;
        this.totalPages = totalPages;
    }// 전체 페이지 수
}