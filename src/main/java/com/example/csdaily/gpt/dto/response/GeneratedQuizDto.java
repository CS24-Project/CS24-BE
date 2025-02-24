package com.example.csdaily.gpt.dto.response;

import java.time.LocalDate;
import java.util.List;

import com.example.csdaily.quiz.entity.Quiz;
import com.example.csdaily.quiz.entity.QuizDifficulty;

public record GeneratedQuizDto(String difficulty, String content, String hint, List<GeneratedQuizChoiceDto> choices, String extra, String conclusion) {
	public Quiz toQuiz() {
		return Quiz
			.builder()
			.createdAt(LocalDate.now())
			.content(content)
			.difficulty(QuizDifficulty.valueOf(difficulty.toUpperCase()))
			.hint(hint)
			.build();
	}
}
