package com.api.pactory.domain;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Embeddable
@Getter
@Setter
@RequiredArgsConstructor
public class WordMeaning {
    @ElementCollection
    private List<String> meanings = new ArrayList<>(); // 여러 개의 뜻 저장

    public WordMeaning(List<String> meanings) {
        this.meanings = meanings;
    }

    public List<String> getMeanings() {
        return meanings;
    }

    public void setMeanings(List<String> meanings) {
        this.meanings = meanings;
    }
}
