package com.example.csdaily.quiz.dto;

import com.example.csdaily.quiz.entity.QuizDifficulty;

import java.util.List;

public record QuizResponseDto(long id, String content, String hint, QuizDifficulty difficulty,
                              List<QuizChoiceResponseDto> choices) {
}
