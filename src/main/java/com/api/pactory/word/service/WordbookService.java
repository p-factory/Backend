package com.api.pactory.word.service;

import com.api.pactory.global.utill.response.CustomApiResponse;
import org.springframework.http.ResponseEntity;

public interface WordbookService {
    ResponseEntity<CustomApiResponse> create(String name);
    ResponseEntity<CustomApiResponse> delete(Long id);
    ResponseEntity<CustomApiResponse> update(Long id, String name);
    ResponseEntity<CustomApiResponse> favorite(Long id);
    ResponseEntity<CustomApiResponse> gets(Long id,int page);
    ResponseEntity<CustomApiResponse> getAll();

}
