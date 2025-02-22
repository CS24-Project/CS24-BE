package com.example.csdaily.gpt.dto.response;

import com.example.csdaily.gpt.dto.GPTMessageDto;

public record GPTChoice(int index, GPTMessageDto message) {
}
