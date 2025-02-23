package com.api.pactory.word.controller;

import com.api.pactory.global.utill.response.CustomApiResponse;
import com.api.pactory.word.dto.WordDto;
import com.api.pactory.word.service.WordService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/word")
@RequiredArgsConstructor
public class WordController {
    private final WordService wordService;


    @PostMapping("/create")
    public ResponseEntity<CustomApiResponse> create(@RequestBody WordDto dto) {
        ResponseEntity<CustomApiResponse> response = wordService.createWord(dto);
        return response;
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<CustomApiResponse> delete(@PathVariable("id") Long id) {
        ResponseEntity<CustomApiResponse> response=wordService.deleteWord(id);
        return response;
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<CustomApiResponse> update(@PathVariable("id") Long id, @RequestBody WordDto dto ) {
        ResponseEntity<CustomApiResponse> response=wordService.updateWord(dto);
        return response;
    }
    @PutMapping("/highlight/{id}")
    public ResponseEntity<CustomApiResponse> highlight(@PathVariable("id") Long id) {
        ResponseEntity<CustomApiResponse> response=wordService.addHighlight(id);
        return response;
    }
}
