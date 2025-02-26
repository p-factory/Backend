package com.api.pactory.word.dto;

import com.api.pactory.domain.Wordbook;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class WordbookDto {
    private String bookName;
    private Long wordbookId;
    private boolean favorite;

    public WordbookDto(Wordbook wordbook) {
        this.wordbookId = wordbook.getId();
        this.bookName=wordbook.getBookName();
        this.favorite=wordbook.isFavorite();
    }
}
