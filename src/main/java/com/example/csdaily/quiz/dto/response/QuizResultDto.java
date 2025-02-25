package com.example.csdaily.quiz.dto.response;

import java.util.List;

import com.example.csdaily.quiz.entity.Quiz;
import com.example.csdaily.quiz.entity.UserAnswer;

public record QuizResultDto(long id, boolean isSolved, List<QuizChoiceDetailDto> quizChoiceDetailDtos,
							String extraInfo, String conclusion) {
	public static QuizResultDto fromUserAnswer(UserAnswer userAnswer) {
		boolean isSolved = userAnswer.getChoice().getIsCorrect();
		Quiz quiz = userAnswer.getQuiz();
		List<QuizChoiceDetailDto> quizChoiceDetailDtos = quiz.getChoices()
			.stream()
			.map(QuizChoiceDetailDto::fromQuizChoice)
			.toList();

		return new QuizResultDto(userAnswer.getId(), isSolved, quizChoiceDetailDtos, quiz.getExtraInfo(),
			quiz.getConclusion());
	}
}
