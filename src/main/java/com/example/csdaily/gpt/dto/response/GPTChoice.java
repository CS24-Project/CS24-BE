package com.example.csdaily.gpt.dto.response;

import java.io.IOException;

import com.example.csdaily.gpt.dto.GPTMessageDto;
import com.fasterxml.jackson.databind.ObjectMapper;

public record GPTChoice(int index, GPTMessageDto message) {
	public GeneratedQuizDto toGeneratedQuizDto(ObjectMapper objectMapper) {
		String contentByte = message().content().replaceAll("\n", "");
		try {
			return objectMapper.readValue(contentByte, GeneratedQuizDto.class);
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException("퀴즈 생성에 오류가 발생했습니다.");
		}
	}
}
