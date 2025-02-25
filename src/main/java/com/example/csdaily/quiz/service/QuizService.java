package com.example.csdaily.quiz.service;

import com.example.csdaily.quiz.dto.request.UserAnswerDto;
import com.example.csdaily.quiz.dto.response.QuizResultDto;
import com.example.csdaily.quiz.entity.Quiz;
import com.example.csdaily.quiz.entity.QuizChoice;
import com.example.csdaily.quiz.entity.UserAnswer;
import com.example.csdaily.quiz.repository.QuizRepository;
import com.example.csdaily.quiz.repository.UserAnswerRepository;
import com.example.csdaily.user.entity.User;
import com.example.csdaily.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class QuizService {
	private final QuizRepository quizRepository;
	private final UserAnswerRepository userAnswerRepository;

	private LocalDate lastUpdatedDate = LocalDate.MIN;
	private final Map<Long, Quiz> quizMap = new HashMap<>();
	private final Map<Long, QuizChoice> quizChoiceMap = new HashMap<>();

	public List<Quiz> getDailyQuizzes() {
		return quizRepository.findAllInCreatedAtEquals(LocalDate.now());
	}

	public List<UserAnswer> submitUserAnswer(User user, List<UserAnswerDto> userAnswerDtos) {
		updateQuizMapAndQuizChoiceMapForToday();
		if (userAnswerRepository.existsByQuizEqualsCreatedAtToToday(user, LocalDate.now())) {
			// todo: 적절한 예외 클래스 필요
			throw new RuntimeException("이미 문제를 풀었습니다!");
		}
		return saveUserAnswer(user, userAnswerDtos);
	}

	private void updateQuizMapAndQuizChoiceMapForToday() {
		LocalDate today = LocalDate.now();
		if (lastUpdatedDate.isBefore(today)) {
			quizMap.clear();
			quizChoiceMap.clear();
			lastUpdatedDate = today;

			quizRepository.findAllInCreatedAtEquals(today).forEach(quiz -> {
				quizMap.put(quiz.getId(), quiz);
				quiz.getChoices().forEach(quizChoice -> quizChoiceMap.put(quizChoice.getId(), quizChoice));
			});
		}
	}

	public List<UserAnswer> saveUserAnswer(User user, List<UserAnswerDto> userAnswerDtos) {
		List<UserAnswer> userAnswers = userAnswerDtos.stream().map(userAnswerDto -> {
			Quiz quiz = quizMap.get(userAnswerDto.quizId());
			QuizChoice quizChoice = quizChoiceMap.get(userAnswerDto.quizChoiceId());
			return userAnswerDto.toUserAnswer(user, quiz, quizChoice);
		}).toList();

		return userAnswerRepository.saveAll(userAnswers);
	}
}
