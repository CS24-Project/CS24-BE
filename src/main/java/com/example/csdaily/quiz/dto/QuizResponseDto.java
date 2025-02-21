package com.example.csdaily.quiz.dto;

import com.example.csdaily.quiz.entity.Quiz;
import com.example.csdaily.quiz.entity.QuizDifficulty;

import java.util.List;

public record QuizResponseDto(long id, String content, String hint, QuizDifficulty difficulty,
                              List<QuizChoiceResponseDto> choices) {

    public static QuizResponseDto fromQuiz(Quiz quiz) {
        return new QuizResponseDto(quiz.getId(), quiz.getContent(), quiz.getHint(), quiz.getDifficulty(),
                quiz.getChoices().stream().map(QuizChoiceResponseDto::fromQuizChoice).toList());
    }
}
