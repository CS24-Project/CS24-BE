package com.example.csdaily.quiz.util;

import com.example.csdaily.quiz.entity.QuizDifficulty;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class QuizDifficultyConverter implements AttributeConverter<QuizDifficulty, String> {
    @Override
    public String convertToDatabaseColumn(QuizDifficulty quizDifficulty) {
        return "easy";
    }

    @Override
    public QuizDifficulty convertToEntityAttribute(String s) {
        return QuizDifficulty.EASY;
    }
}
