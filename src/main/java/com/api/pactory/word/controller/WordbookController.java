package com.api.pactory.word.controller;

import com.api.pactory.domain.Member;
import com.api.pactory.domain.Wordbook;
import com.api.pactory.global.security.LoginMember;
import com.api.pactory.global.utill.response.CustomApiResponse;
import com.api.pactory.word.dto.WordbookDto;
import com.api.pactory.word.service.WordbookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/wordbook")
@RequiredArgsConstructor
public class WordbookController {
    private final WordbookService wordbookService;


    @PostMapping("/create")
    public ResponseEntity<CustomApiResponse> create(@RequestBody WordbookDto dto,@LoginMember Member loginMember) {
        ResponseEntity<CustomApiResponse> response = wordbookService.create(dto.getName(),loginMember);
        return response;
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<CustomApiResponse> delete(@PathVariable("id") Long id) {
        ResponseEntity<CustomApiResponse> response=wordbookService.delete(id);
        return response;
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<CustomApiResponse> update(@PathVariable("id") Long id, @RequestBody WordbookDto dto) {
        ResponseEntity<CustomApiResponse> response=wordbookService.update(id,dto.getName());
        return response;
    }
    @PutMapping("/favorite/{id}")
    public ResponseEntity<CustomApiResponse> favorite(@PathVariable("id") Long id) {
        ResponseEntity<CustomApiResponse> response=wordbookService.favorite(id);
        return response;
    }
    @GetMapping("/{page}")
    public ResponseEntity<CustomApiResponse> gets(@PathVariable("page") int page,@RequestBody WordbookDto dto) {
        ResponseEntity<CustomApiResponse> response=wordbookService.gets(dto.getId(),page);
        return response;
    }
    @GetMapping("/all")
    public ResponseEntity<CustomApiResponse> getAll(@LoginMember Member loginMember) {
        ResponseEntity<CustomApiResponse> response=wordbookService.getAll(loginMember);
        return response;
    }
}
