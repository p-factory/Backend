package com.api.pactory.word.repository;

import com.api.pactory.domain.Word;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WordRepository extends JpaRepository<Word, Long> {

    @Query("SELECT w FROM Word w WHERE w.wordbook.id = :wordbookId")
    Page<Word> findWordsByWordbookId(@Param("wordbookId") Long wordbookId, Pageable pageable);

    List<Word> findByWordbookId(Long id);
}

