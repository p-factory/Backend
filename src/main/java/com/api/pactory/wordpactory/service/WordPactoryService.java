package com.api.pactory.wordpactory.service;


import com.api.pactory.global.utill.response.CustomApiResponse;
import com.api.pactory.wordpactory.dto.WordPactoryReq;
import org.springframework.http.ResponseEntity;

public interface WordPactoryService {
   ResponseEntity<CustomApiResponse<?>> create(WordPactoryReq wordPactoryReq);
}
