package com.api.pactory.word.controller;

import com.api.pactory.domain.Member;
import com.api.pactory.domain.Wordbook;
import com.api.pactory.global.security.LoginMember;
import com.api.pactory.global.utill.response.CustomApiResponse;
import com.api.pactory.word.dto.WordbookDto;
import com.api.pactory.word.service.WordbookService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Pageable;

@RestController
@RequestMapping("/api/wordbook")
@RequiredArgsConstructor
public class WordbookController {
    private final WordbookService wordbookService;


    @PostMapping("/create")
    public ResponseEntity<CustomApiResponse> create(@RequestBody WordbookDto dto,@LoginMember Member loginMember) {
        ResponseEntity<CustomApiResponse> response = wordbookService.create(dto.getBookName(),loginMember);
        return response;
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<CustomApiResponse> delete(@PathVariable("id") Long id) {
        ResponseEntity<CustomApiResponse> response=wordbookService.delete(id);
        return response;
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<CustomApiResponse> update(@PathVariable("id") Long id, @RequestBody WordbookDto dto) {
        ResponseEntity<CustomApiResponse> response=wordbookService.update(id,dto.getBookName());
        return response;
    }
    @PostMapping("/favorite/{id}")
    public ResponseEntity<CustomApiResponse> favorite(@PathVariable("id") Long id) {
        ResponseEntity<CustomApiResponse> response=wordbookService.favorite(id);
        return response;
    }
    @GetMapping
    public ResponseEntity<CustomApiResponse> gets(
            @RequestParam("id") Long wordbookId,
            @RequestParam("page") int page) {
        ResponseEntity<CustomApiResponse> response = wordbookService.gets(wordbookId, page);
        return response;
    }
    @GetMapping("/all")
    public ResponseEntity<CustomApiResponse> getAll(@LoginMember Member loginMember) {
        ResponseEntity<CustomApiResponse> response=wordbookService.getAll(loginMember);
        return response;
    }
    @PostMapping("/export/{wordbookID}")
    public ResponseEntity<CustomApiResponse> export(@LoginMember Member loginMember, @PathVariable("wordbookID") Long wordbookID, HttpServletResponse response) {
        ResponseEntity<CustomApiResponse> responseq=wordbookService.export(loginMember,wordbookID,response);
        return responseq;
    }
    @PostMapping("/shared/{wordbookId}")
    public ResponseEntity<CustomApiResponse> share(@LoginMember Member loginMember, @PathVariable("wordbookId") Long wordbookId) {
        ResponseEntity<CustomApiResponse> response = wordbookService.share(loginMember,wordbookId);
        return response;
    }
    @GetMapping("/shared/all/{page]")
    public ResponseEntity<CustomApiResponse> getAllShared(@LoginMember Member loginMember,@PathVariable int page) {
        ResponseEntity<CustomApiResponse>response = wordbookService.getShared(loginMember,page);
        return response;
    }
}
