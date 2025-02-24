package com.example.csdaily.quiz.repository;

import com.example.csdaily.quiz.entity.QuizChoice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuizChoiceRepository extends JpaRepository<QuizChoice, Long> {
}
