package com.api.pactory.word.repository;

import com.api.pactory.domain.Wordbook;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WordbookRepository extends JpaRepository<Wordbook, Long> {
}