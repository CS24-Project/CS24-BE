package com.example.csdaily.quiz.service;

import com.example.csdaily.quiz.dto.QuizChoiceResponseDto;
import com.example.csdaily.quiz.dto.QuizResponseDto;
import com.example.csdaily.quiz.entity.Quiz;
import com.example.csdaily.quiz.entity.QuizChoice;
import com.example.csdaily.quiz.entity.QuizDifficulty;
import com.example.csdaily.quiz.repository.QuizChoiceRepository;
import com.example.csdaily.quiz.repository.QuizRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class QuizService {
    private final QuizRepository quizRepository;
    private final QuizChoiceRepository quizChoiceRepository;

    public QuizResponseDto foo() {
        // todo: 응답 예시를 위한 임시 코드입니다. 실제 로직으로 교체해야합니다.
        Quiz quiz = Quiz
                .builder()
                .content("다음 중 운영체제(OS)에서 멀티프로세싱(Multiprocessing)에 대한 설명으로 올바른 것은?")
                .hint("이 방식은 여러 개의 코어를 활용하는 것이므로, 단일 코어에서 여러 프로세스를 실행하는 방식은 아닙니다.")
                .createdAt(LocalDate.now())
                .difficulty(QuizDifficulty.NORMAL)
                .build();
        quizRepository.save(quiz);

        List<QuizChoice> quizChoices = List.of(
                QuizChoice.builder().quiz(quiz).choiceNumber(1).isCorrect(false).content("멀티프로세싱은 하나의 CPU에서 여러 개의 스레드를 실행하는 기법이다.").build(),
                QuizChoice.builder().quiz(quiz).choiceNumber(2).isCorrect(false).content("멀티프로세싱은 하나의 프로세스 내에서 여러개의 스레드를 실행하는 방식이다.").build(),
                QuizChoice.builder().quiz(quiz).choiceNumber(3).isCorrect(true).content("멀티프로세싱은 여러 개의 CPU 또는 코어에서 여러 개의 프로세스를 실행하는 방식이다.").build(),
                QuizChoice.builder().quiz(quiz).choiceNumber(4).isCorrect(false).content("멀티프로세싱은 프로세스 간의 메모리 공유가 불가능하다.").build());
        quizChoiceRepository.saveAll(quizChoices);

        List<QuizChoiceResponseDto> quizChoiceResponseDtos =
                quizChoices
                        .stream()
                        .map(quizChoice -> new QuizChoiceResponseDto(quizChoice.getChoiceNumber(), quizChoice.getContent()))
                        .toList();
        return new QuizResponseDto(quiz.getId(), quiz.getContent(), quiz.getHint(), quiz.getDifficulty(), quizChoiceResponseDtos);
    }

    public List<Quiz> getDailyQuiz() {
        return quizRepository.findAllInCreatedAtEquals(LocalDate.now());
    }
}

