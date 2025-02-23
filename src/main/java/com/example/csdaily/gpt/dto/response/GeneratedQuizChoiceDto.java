package com.example.csdaily.gpt.dto.response;

import com.example.csdaily.quiz.entity.Quiz;
import com.example.csdaily.quiz.entity.QuizChoice;

public record GeneratedQuizChoiceDto(int index, String content, String commentary, boolean isCorrect) {
	public QuizChoice toQuizChoice(Quiz quiz) {
		return QuizChoice.builder()
			.quiz(quiz)
			.choiceNumber(index)
			.content(content)
			.commentary(commentary)
			.isCorrect(isCorrect)
			.build();
	}
}
