package com.example.csdaily.gpt.service;

import java.net.URI;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.example.csdaily.gpt.dto.GPTMessageDto;
import com.example.csdaily.gpt.dto.request.QuizGenerationDto;
import com.example.csdaily.gpt.dto.response.GPTResponse;
import com.example.csdaily.gpt.dto.response.GeneratedQuizDto;
import com.example.csdaily.quiz.entity.Quiz;
import com.example.csdaily.quiz.entity.QuizChoice;
import com.example.csdaily.quiz.repository.QuizChoiceRepository;
import com.example.csdaily.quiz.repository.QuizRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class QuizGenerationService {

	private final RestTemplate restTemplate;
	private final QuizRepository quizRepository;
	private final QuizChoiceRepository quizChoiceRepository;
	private final ObjectMapper objectMapper;
	private GPTResponse gptResponse;
	private QuizGenerationDto quizGenerationDto;
	private List<GeneratedQuizDto> generatedQuizDtos;
	private List<Quiz> createdQuizzes;
	private List<QuizChoice> createdQuizChoices;

	@Value("${gpt.url}")
	private String url;

	@Value("${gpt.model}")
	private String model;

	@Value("${gpt.n}")
	private int n;

	@Value("${gpt.api-key}")
	private String apiKey;

	@Value("${gpt.prompt.developer}")
	private String developerMessage;

	@Value("${gpt.prompt.user}")
	private String userMessage;

	public QuizGenerationService(RestTemplate restTemplate, QuizRepository quizRepository,
		QuizChoiceRepository quizChoiceRepository) {
		this.restTemplate = restTemplate;
		this.quizRepository = quizRepository;
		this.quizChoiceRepository = quizChoiceRepository;
		this.objectMapper = new ObjectMapper();

		this.restTemplate.getInterceptors().add((request, body, execution) -> {
			request.getHeaders().add("Authorization", "Bearer " + apiKey);
			return execution.execute(request, body);
		});
	}

	@Scheduled(cron = "0 0 * * * *")
	public void generate() {
		// todo: 로그를 aop로 하는 방법?
		log.info("Quiz generation start.");
		setup();
		if (isTodayQuizAlreadyGenerated()) {
			log.info("오늘의 퀴즈가 이미 생성되었습니다.");
			return;
		}
		sendRequest();
		saveResponse();
		log.info("Quiz generation finished.");
		createdQuizzes.forEach(quiz -> log.info("Generated quiz : {}", quiz.getContent()));
	}

	private boolean isTodayQuizAlreadyGenerated() {
		return quizRepository.existsQuizByCreatedAt(LocalDate.now());
	}

	private void setup() {
		gptResponse = null;
		generatedQuizDtos = null;
		createdQuizzes = new ArrayList<>();
		createdQuizChoices = new ArrayList<>();
		if (quizGenerationDto == null) {
			GPTMessageDto developerMessageDto = new GPTMessageDto("developer", developerMessage);
			GPTMessageDto userMessageDto = new GPTMessageDto("user", userMessage);
			quizGenerationDto = new QuizGenerationDto(model, n, List.of(developerMessageDto, userMessageDto));
		}
	}

	private void sendRequest() {
		try {
			gptResponse = restTemplate.postForEntity(URI.create(url), quizGenerationDto, GPTResponse.class).getBody();
		} catch (RestClientException e) {
			//todo: 에러 메세지 핸들링 필요.
			log.error("GPT API 요청 실패!");
			throw new RuntimeException(e);
		}
	}

	private void saveResponse() {
		convertGPTMessage();
		createQuizzesAndQuizChoices();
		saveQuizzesAndQuizChoices();
	}

	private void convertGPTMessage() {
		generatedQuizDtos = gptResponse.choices()
			.stream()
			.map(gptChoice -> gptChoice.toGeneratedQuizDto(objectMapper))
			.toList();
	}

	private void createQuizzesAndQuizChoices() {
		generatedQuizDtos.forEach(generatedQuizDto -> {
			Quiz quiz = generatedQuizDto.toQuiz();
			createdQuizzes.add(quiz);
			generatedQuizDto.choices().forEach(generatedQuizChoiceDto -> {
				createdQuizChoices.add(generatedQuizChoiceDto.toQuizChoice(quiz));
			});
		});
	}

	private void saveQuizzesAndQuizChoices() {
		quizRepository.saveAll(createdQuizzes);
		quizChoiceRepository.saveAll(createdQuizChoices);
	}
}
