package com.example.csdaily.gpt.dto.request;

import java.util.List;

import com.example.csdaily.gpt.dto.GPTMessageDto;

public record QuizGenerationDto(String model, int n, List<GPTMessageDto> messages) {
}
