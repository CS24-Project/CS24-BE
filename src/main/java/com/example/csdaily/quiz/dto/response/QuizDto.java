package com.example.csdaily.quiz.dto.response;

import com.example.csdaily.quiz.entity.Quiz;
import com.example.csdaily.quiz.entity.QuizDifficulty;

import java.util.List;

public record QuizDto(long id, String content, String hint, QuizDifficulty difficulty,
                      List<QuizChoiceDto> choices) {

    public static QuizDto fromQuiz(Quiz quiz) {
        return new QuizDto(quiz.getId(), quiz.getContent(), quiz.getHint(), quiz.getDifficulty(),
                quiz.getChoices().stream().map(QuizChoiceDto::fromQuizChoice).toList());
    }
}
