package com.example.csdaily.quiz.dto.response;

import com.example.csdaily.quiz.entity.QuizChoice;

public record QuizChoiceDetailDto(long id, int choiceNumber, boolean isCorrect, String content, String commentary) {
	public static QuizChoiceDetailDto fromQuizChoice(QuizChoice quizChoice) {
		return new QuizChoiceDetailDto(quizChoice.getId(), quizChoice.getChoiceNumber(), quizChoice.isCorrect(),
			quizChoice.getContent(), quizChoice.getCommentary());
	}
}
