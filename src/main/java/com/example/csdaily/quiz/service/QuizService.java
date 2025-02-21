package com.example.csdaily.quiz.service;

import com.example.csdaily.quiz.entity.Quiz;
import com.example.csdaily.quiz.repository.QuizRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class QuizService {
    private final QuizRepository quizRepository;

    public List<Quiz> getDailyQuiz() {
        return quizRepository.findAllInCreatedAtEquals(LocalDate.now());
    }
}

