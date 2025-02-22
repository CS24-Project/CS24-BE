package com.example.csdaily.gpt.service;

import java.net.URI;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.csdaily.gpt.dto.GPTMessageDto;
import com.example.csdaily.gpt.dto.request.QuizGenerationDto;
import com.example.csdaily.gpt.dto.response.GPTResponse;

@Service
public class QuizGenerationService {
	private final Logger logger = LoggerFactory.getLogger(QuizGenerationService.class);

	private final RestTemplate restTemplate;
	private GPTResponse gptResponse;

	@Value("${gpt.url}")
	private String url;

	@Value("${gpt.model}")
	private String model;

	@Value("${gpt.api-key}")
	private String apiKey;

	private final String cronSyntax = "0 * * * * *";

	public QuizGenerationService(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
		this.restTemplate.getInterceptors().add((request, body, execution) -> {
			request.getHeaders().add("Authorization", "Bearer " + apiKey);
			return execution.execute(request, body);
		});
	}

	private QuizGenerationDto generateRequest() {
		GPTMessageDto gptMessageDto = new GPTMessageDto("user", "안녕");
		return new QuizGenerationDto(model, List.of(gptMessageDto));
	}

	// @Scheduled(cron = cronSyntax)
	public void generateQuizUsingGPT() {
		// todo: 로그를 aop로 하는 방법?
		logger.info("{} :: Start generation quiz.", this.getClass());
		sendRequest();
		gptResponse.choices().forEach(gptChoice -> System.out.println(gptChoice.message()));
		logger.info("{} :: End generation quiz.", this.getClass());
	}

	private void sendRequest() {
		logger.info("{} :: Start sending message to GPT.", this.getClass());
		QuizGenerationDto quizGenerationDto = generateRequest();
		gptResponse = restTemplate.postForEntity(URI.create(url), quizGenerationDto, GPTResponse.class).getBody();
		logger.info("{} :: End sending message to GPT", this.getClass());
	}
}
