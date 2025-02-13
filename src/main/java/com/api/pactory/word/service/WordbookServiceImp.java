package com.api.pactory.word.service;

import com.api.pactory.Member.repository.MemberRepository;
import com.api.pactory.domain.Member;
import com.api.pactory.domain.Wordbook;
import com.api.pactory.global.security.LoginMember;
import com.api.pactory.global.utill.Member.AuthenticationMemberUtils;
import com.api.pactory.global.utill.response.CustomApiResponse;
import com.api.pactory.word.dto.WordDto;
import com.api.pactory.word.dto.WordbookDtoWithWords;
import com.api.pactory.word.repository.WordRepository;
import com.api.pactory.word.repository.WordbookRepository;
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
public class WordbookServiceImp implements WordbookService {
    private final WordbookRepository wordbookRepository;
    private final WordRepository wordRepository;


    @Override
    public ResponseEntity<CustomApiResponse> create(String name) {

        Wordbook wordbook = Wordbook.builder()
                .bookName(name)
                .favorite(false)
                .build();
        wordbook = wordbookRepository.save(wordbook);
        CustomApiResponse<?> response = CustomApiResponse.createSuccess(200, wordbook, "단어장 생성에 성공하였습니다");
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @Override
    public ResponseEntity<CustomApiResponse> delete(Long id) {
        if (wordbookRepository.existsById(id)) {
            wordbookRepository.deleteById(id);
            CustomApiResponse<?> response = CustomApiResponse.createSuccessWithoutData(200, "단어장 삭제에 성공하였습니다.");
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }

        CustomApiResponse<?> response = CustomApiResponse.createFailWithout(404, "단어장이 존재하지 않습니다.");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @Override
    public ResponseEntity<CustomApiResponse> update(Long id, String name) {
        if (wordbookRepository.existsById(id)) {
            Wordbook wordbook = wordbookRepository.findById(id).get();
            wordbook.setBookName(name);
            wordbookRepository.save(wordbook);
            CustomApiResponse<?> response = CustomApiResponse.createSuccess(200, wordbook, "단어장 이름변경에 성공하였습니다.");
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }
        CustomApiResponse<?> response = CustomApiResponse.createFailWithout(404, "단어장이 존재하지 않습니다.");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @Override
    public ResponseEntity<CustomApiResponse> favorite(Long id) {
        if (wordbookRepository.existsById(id)) {
            Wordbook wordbook = wordbookRepository.findById(id).get();
            wordbook.setFavorite(true);
            CustomApiResponse<?> response = CustomApiResponse.createSuccess(200, wordbook, "단어장 이름변경에 성공하였습니다.");
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }
        CustomApiResponse<?> response = CustomApiResponse.createFailWithout(404, "단어장이 존재하지 않습니다.");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @Override
    public ResponseEntity<CustomApiResponse> gets(Long id, int page){
        if (wordbookRepository.existsById(id)) {
            Wordbook wordbook = wordbookRepository.findById(id).get();
            Pageable pageable = PageRequest.of(page, 10, Sort.by("id").ascending());
            var wordPage = wordRepository.findWordsByWordbookId(id, pageable);
            if (wordPage.hasContent()) {
                List<WordDto> wordDtos = wordPage.getContent().stream()
                        .map(word -> new WordDto(word.getId(), word.getWord(), word.getWordMeaning().getMeanings()))
                        .collect(Collectors.toList());
                // 단어장 이름과 즐겨찾기 상태 포함
                var responseData = new WordbookDtoWithWords(
                        wordbook.getId(),
                        wordbook.getBookName(),
                        wordbook.isFavorite(),
                        wordDtos,
                        wordPage.getTotalElements(),
                        wordPage.getTotalPages()
                );
                CustomApiResponse<?> response = CustomApiResponse.createSuccess(200, responseData, "해당 단어장을 가져왔습니다.");
                return ResponseEntity.status(HttpStatus.OK).body(response);
            } else {
                // 단어장이 비어있으면 비어있다는 응답을 반환
                CustomApiResponse<?> response = CustomApiResponse.createSuccess(200, wordbook, "해당 단어장을 가져왔습니다.");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }
        }
        // 단어장 존재하지 않으면 실패 응답 반환
        CustomApiResponse<?> response = CustomApiResponse.createFailWithout(404, "단어장이 존재하지 않습니다.");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }
    @Override
    public ResponseEntity<CustomApiResponse>getAll(){
        return null;
    }


}
