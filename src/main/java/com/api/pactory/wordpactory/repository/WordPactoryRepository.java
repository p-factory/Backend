package com.api.pactory.wordpactory.repository;

import com.api.pactory.domain.WordPactory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WordPactoryRepository extends JpaRepository<WordPactory, Long> {
}
