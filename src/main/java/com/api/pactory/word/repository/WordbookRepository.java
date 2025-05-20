package com.api.pactory.word.repository;

import com.api.pactory.domain.Wordbook;
import com.api.pactory.global.utill.init.Shared;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface WordbookRepository extends JpaRepository<Wordbook, Long> {
    List<Wordbook> findByMemberName(String nickname);

    Page<Wordbook> findByShared(Shared shared, Pageable pageable);
}