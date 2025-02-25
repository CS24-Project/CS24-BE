package com.example.csdaily.quiz.controller;

import com.example.csdaily.quiz.annotation.LoginUser;
import com.example.csdaily.quiz.dto.request.UserAnswerDto;
import com.example.csdaily.quiz.dto.response.QuizDto;
import com.example.csdaily.quiz.dto.response.QuizResultDto;
import com.example.csdaily.quiz.entity.UserAnswer;
import com.example.csdaily.quiz.service.QuizService;
import com.example.csdaily.user.entity.User;

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
    public ResponseEntity<List<QuizDto>> getDailyQuizzes() {
        return ResponseEntity.ok(quizService.getDailyQuizzes().stream().map(QuizDto::fromQuiz).toList());
    }

    @PostMapping
    public ResponseEntity<List<QuizResultDto>> submitQuizChoices(@LoginUser User user, @RequestBody List<UserAnswerDto> userAnswerDtos) {
        List<UserAnswer> userAnswers = quizService.submitUserAnswer(user, userAnswerDtos);
        return ResponseEntity.ok(userAnswers.stream().map(QuizResultDto::fromUserAnswer).toList());
    }
}
