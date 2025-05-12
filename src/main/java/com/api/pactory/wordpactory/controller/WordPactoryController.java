package com.api.pactory.wordpactory.controller;

import com.api.pactory.domain.WordPactory;
import com.api.pactory.global.utill.response.CustomApiResponse;
import com.api.pactory.wordpactory.dto.WordPactoryReq;
import com.api.pactory.wordpactory.service.WordPactoryService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/wordpactory")
@AllArgsConstructor
public class WordPactoryController {
    private WordPactoryService wordPactoryService;

    @PostMapping("/create")
    public ResponseEntity<CustomApiResponse<?>> create(WordPactoryReq wordPactoryReq) {
        ResponseEntity<CustomApiResponse<?>> response= wordPactoryService.create(wordPactoryReq);
        return response;
    }
}
