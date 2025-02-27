package com.api.pactory.word.service;

import com.api.pactory.domain.Word;
import com.api.pactory.domain.WordMeaning;
import com.api.pactory.domain.Wordbook;
import com.api.pactory.global.utill.response.CustomApiResponse;
import com.api.pactory.word.dto.WordDto;
import com.api.pactory.word.repository.WordRepository;
import com.api.pactory.word.repository.WordbookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class WordServiceImp implements WordService {

    private final WordRepository wordRepository;
    private final WordbookRepository wordbookRepository;

    // 단어 생성
    @Override
    public ResponseEntity<CustomApiResponse> createWord(WordDto wordDto) {
        Optional<Wordbook> wordbookOpt = wordbookRepository.findById(wordDto.getId());
        if (wordbookOpt.isPresent()) {
            Wordbook wordbook = wordbookOpt.get();

            // 단어와 뜻을 저장
            WordMeaning wordMeaning = new WordMeaning(wordDto.getMeanings());
            Word newWord = Word.builder()
                    .word(wordDto.getWord())
                    .wordMeaning(wordMeaning)
                    .wordbook(wordbook)
                    .highlight(false)
                    .build();

            wordRepository.save(newWord);
            CustomApiResponse response = CustomApiResponse.createSuccessWithoutData(200,"단어 생성에 성공하였습니다.");
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        }

        CustomApiResponse<?> response = CustomApiResponse.createFailWithout(404, "단어장이 존재하지 않습니다.");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    // 단어 수정
    @Override
    public ResponseEntity<CustomApiResponse> updateWord(Long id,WordDto wordDto) {
        Optional<Word> wordOpt = wordRepository.findById(id);
        if (wordOpt.isPresent()) {
            Word word = wordOpt.get();
            // 단어 수정
            word.setWord(wordDto.getWord());
            word.setWordMeaning(new WordMeaning(wordDto.getMeanings()));
            wordRepository.save(word);
            CustomApiResponse response = CustomApiResponse.createSuccessWithoutData(200,"단어 수정에 성공하였습니다.");
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }

        CustomApiResponse<?> response = CustomApiResponse.createFailWithout(404, "단어가 존재하지 않습니다.");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    // 단어 삭제
    @Override
    public ResponseEntity<CustomApiResponse> deleteWord(Long wordId) {
        Optional<Word> wordOpt = wordRepository.findById(wordId);
        if (wordOpt.isPresent()) {
            wordRepository.deleteById(wordId);
            CustomApiResponse response = CustomApiResponse.createSuccessWithoutData(200,"단어 삭제에 성공하였습니다.");
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }

        CustomApiResponse<?> response = CustomApiResponse.createFailWithout(404, "단어가 존재하지 않습니다.");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    // 형광펜 추가
    @Override
    public ResponseEntity<CustomApiResponse> addHighlight(Long wordId) {
        Optional<Word> wordOpt = wordRepository.findById(wordId);
        if (wordOpt.isPresent()) {
            Word word = wordOpt.get();
            word.setHighlight(!word.isHighlight());// 뜻에 형광펜 추가
            wordRepository.save(word);
            WordDto wordDto = new WordDto(word.getId(),word.getWord(),word.getWordMeaning().getMeanings(),word.isHighlight(),word.isCheck());
            CustomApiResponse response = CustomApiResponse.createSuccess(200,wordDto,"형광펜 설정에 성공하였습니다.");
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }

        CustomApiResponse<?> response = CustomApiResponse.createFailWithout(404, "단어가 존재하지 않습니다.");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }
    @Override
    public ResponseEntity<CustomApiResponse> addCheck(Long wordId) {
        Optional<Word> wordOpt = wordRepository.findById(wordId);
        if (wordOpt.isPresent()) {
            Word word = wordOpt.get();
            word.setCheck(!word.isCheck());
            wordRepository.save(word);
            WordDto wordDto = new WordDto(word.getId(),word.getWord(),word.getWordMeaning().getMeanings(),word.isHighlight(),word.isCheck());
            CustomApiResponse response = CustomApiResponse.createSuccess(200,wordDto,"단어체크 설정에 성공하였습니다.");
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }
        CustomApiResponse<?> response = CustomApiResponse.createFailWithout(404, "단어가 존재하지 않습니다.");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }
}
