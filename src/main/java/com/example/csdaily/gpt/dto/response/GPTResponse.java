package com.example.csdaily.gpt.dto.response;

import java.util.List;

public record GPTResponse(List<GPTChoice> choices) {
}
