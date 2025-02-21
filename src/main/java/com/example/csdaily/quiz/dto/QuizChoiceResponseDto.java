package com.example.csdaily.quiz.dto;

import com.example.csdaily.quiz.entity.QuizChoice;

public record QuizChoiceResponseDto(int number, String content) {
    public static QuizChoiceResponseDto fromQuizChoice(QuizChoice quizChoice) {
        return new QuizChoiceResponseDto(quizChoice.getChoiceNumber(), quizChoice.getContent());
    }
}
