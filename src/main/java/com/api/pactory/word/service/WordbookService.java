package com.api.pactory.word.service;

import com.api.pactory.domain.Member;
import com.api.pactory.global.security.LoginMember;
import com.api.pactory.global.utill.response.CustomApiResponse;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.awt.print.Pageable;

public interface WordbookService {
    ResponseEntity<CustomApiResponse> create(String name,Member member);
    ResponseEntity<CustomApiResponse> delete(Long id);
    ResponseEntity<CustomApiResponse> update(Long id, String name);
    ResponseEntity<CustomApiResponse> favorite(Long id);
    ResponseEntity<CustomApiResponse> gets(Long id,int page);
    ResponseEntity<CustomApiResponse> getAll(Member member);
    ResponseEntity<CustomApiResponse> export(Member member, Long id, HttpServletResponse response);
    ResponseEntity<CustomApiResponse> share(Member member, Long id);
    ResponseEntity<CustomApiResponse> getShared(Member member,int page);

}
