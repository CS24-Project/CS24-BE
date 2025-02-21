package com.example.csdaily.quiz.dto;

import com.example.csdaily.quiz.entity.QuizChoice;

public record QuizChoiceResponseDto(long id, long choiceNumber, String content) {
    public static QuizChoiceResponseDto fromQuizChoice(QuizChoice quizChoice) {
        return new QuizChoiceResponseDto(quizChoice.getId(), quizChoice.getChoiceNumber(), quizChoice.getContent());
    }
}
