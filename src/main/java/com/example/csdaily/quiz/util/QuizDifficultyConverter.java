package com.example.csdaily.quiz.util;

import java.util.stream.Stream;

import com.example.csdaily.quiz.entity.QuizDifficulty;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class QuizDifficultyConverter implements AttributeConverter<QuizDifficulty, String> {
	@Override
	public String convertToDatabaseColumn(QuizDifficulty quizDifficulty) {
		if (quizDifficulty == null) {
			return null;
		}
		return quizDifficulty.toString();
	}

	@Override
	public QuizDifficulty convertToEntityAttribute(String s) {
		return Stream.of(QuizDifficulty.values())
			.filter(quizDifficulty -> quizDifficulty.toString().equalsIgnoreCase(s))
			.findFirst()
			.orElseThrow(IllegalAccessError::new);
	}
}
