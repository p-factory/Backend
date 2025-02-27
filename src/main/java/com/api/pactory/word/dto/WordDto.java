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
    private boolean highlight;
    private boolean check;

    public WordDto(Long id, String word, List<String> meanings, boolean highlight, boolean check) {
        this.id = id;
        this.word = word;
        this.meanings = meanings;
        this.highlight = highlight;
        this.check = check;

    }
}
