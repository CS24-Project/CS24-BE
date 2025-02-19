package com.example.csdaily.quiz.controller;

import com.example.csdaily.quiz.dto.QuizResponseDto;
import com.example.csdaily.quiz.service.QuizService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class QuizController {
    private final QuizService quizService;

    @GetMapping
    public ResponseEntity<QuizResponseDto> get() {
        return ResponseEntity.ok(quizService.foo());
    }
}
