package com.example.csdaily.gpt.service;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class QuizGenerationTestController {
	private final QuizGenerationService quizGenerationService;

	@GetMapping("/api/v1/quiz/generate")
	public void foo() {
		quizGenerationService.generate();
	}
}
