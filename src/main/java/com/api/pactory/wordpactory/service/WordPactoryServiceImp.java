package com.api.pactory.wordpactory.service;

import com.api.pactory.domain.Word;
import com.api.pactory.domain.Wordbook;
import com.api.pactory.global.utill.response.CustomApiResponse;
import com.api.pactory.word.dto.WordbookDto;
import com.api.pactory.word.repository.WordRepository;
import com.api.pactory.word.repository.WordbookRepository;
import com.api.pactory.wordpactory.dto.WordPactoryReq;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class WordPactoryServiceImp implements WordPactoryService {

    private final WordbookRepository wordbookRepository;
    private final WordRepository wordRepository;


    @Override
    public ResponseEntity<CustomApiResponse<?>> create(WordPactoryReq wordPactoryReq) {
        Optional<Wordbook> wordbooks = wordbookRepository.findById(wordPactoryReq.getWordBookId());
        return null;
    }
}
