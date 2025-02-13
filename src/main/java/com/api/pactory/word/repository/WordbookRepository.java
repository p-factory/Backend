package com.api.pactory.word.repository;

import com.api.pactory.domain.Wordbook;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface WordbookRepository extends JpaRepository<Wordbook, Long> {
    List<Wordbook> findByMemberName(String nickname);
}