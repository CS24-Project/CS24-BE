package com.example.csdaily.quiz.dto.response;

import com.example.csdaily.quiz.entity.QuizChoice;

public record QuizChoiceDto(long id, long choiceNumber, String content) {
    public static QuizChoiceDto fromQuizChoice(QuizChoice quizChoice) {
        return new QuizChoiceDto(quizChoice.getId(), quizChoice.getChoiceNumber(), quizChoice.getContent());
    }
}
