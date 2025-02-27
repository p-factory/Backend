package com.api.pactory.word.service;

import com.api.pactory.global.utill.response.CustomApiResponse;
import com.api.pactory.word.dto.WordDto;
import org.springframework.http.ResponseEntity;

public interface WordService {
    ResponseEntity<CustomApiResponse> createWord(WordDto wordDto);
    ResponseEntity<CustomApiResponse> updateWord(Long id,WordDto wordDto);
    ResponseEntity<CustomApiResponse> deleteWord(Long Id);
    ResponseEntity<CustomApiResponse> addHighlight(Long Id);
    ResponseEntity<CustomApiResponse> addCheck(Long Id);
}