package com.example.csdaily.quiz.repository;

import com.example.csdaily.quiz.entity.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface QuizRepository extends JpaRepository<Quiz, Long> {
    @Query("SELECT DISTINCT q FROM Quiz q JOIN FETCH q.choices qc WHERE q.createdAt = :createdAt")
    List<Quiz> findAllInCreatedAtEquals(@Param("createdAt") LocalDate createdAt);
}
