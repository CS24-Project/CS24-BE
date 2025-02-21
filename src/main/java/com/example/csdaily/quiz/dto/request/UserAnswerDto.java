package com.example.csdaily.quiz.dto.request;

import com.example.csdaily.quiz.entity.Quiz;
import com.example.csdaily.quiz.entity.QuizChoice;
import com.example.csdaily.quiz.entity.UserAnswer;
import com.example.csdaily.user.entity.User;

public record UserAnswerDto(long quizId, long quizChoiceId) {
	public UserAnswer toUserAnswer(User user, Quiz quiz, QuizChoice quizChoice) {
		return UserAnswer.builder()
			.user(user)
			.quiz(quiz)
			.choice(quizChoice)
			.build();
	}
}
