package com.api.pactory.word.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class WordDto {
    private Long id;
    private String word;
    private List<String> meanings;

    public WordDto(Long id, String word, List<String> meanings) {
        this.id = id;
        this.word = word;
        this.meanings = meanings;

    }
}
