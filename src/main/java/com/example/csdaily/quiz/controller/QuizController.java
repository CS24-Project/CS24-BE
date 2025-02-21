package com.example.csdaily.quiz.controller;

import com.example.csdaily.quiz.dto.QuizResponseDto;
import com.example.csdaily.quiz.entity.Quiz;
import com.example.csdaily.quiz.service.QuizService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/quiz")
@RequiredArgsConstructor
public class QuizController {
    private final QuizService quizService;

    @GetMapping
    public ResponseEntity<List<QuizResponseDto>> getDailyQuiz() {
        List<Quiz> quizzes = quizService.getDailyQuiz();
        List<QuizResponseDto> quizResponseDtos = quizzes.stream().map(QuizResponseDto::fromQuiz).toList();
        return ResponseEntity.ok(quizResponseDtos);
    }
}
