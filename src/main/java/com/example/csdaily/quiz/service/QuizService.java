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

import java.time.LocalDateTime;
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
                .createdAt(LocalDateTime.now())
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
}

//### 문제 1:\n컴퓨터 과학에서 \"ALU\"는 무엇을 의미하는가?\n\n**선택지:**\na) Arithmetic Logic Unit  \nb) Algorithmic Language Unit  \nc) Application Logic Unit  \nd) Analog Linking Unit  \n\n**해설:**\n정답: a) Arithmetic Logic Unit\n\n해설: ALU(산술 논리 장치)는 중앙 처리 장치(CPU)의 핵심 컴포넌트로, 산술 및 논리적인 연산을 수행하는 회로를 말합니다. 따라서 \"Arithmetic Logic Unit\"이 ALU의 정확한 의미를 나타냅니다.\n\nb) Algorithmic Language Unit: 컴퓨터 과학 용어나 개념으로 존재하지 않는 용어입니다.\nc) Application Logic Unit: 컴퓨터 과학에서 사용되는 용어가 아닙니다.\nd) Analog Linking Unit: 컴퓨터 과학에서 사용되는 용어가 아닙니다.\n\n\n### 문제 2:\n컴퓨터 과학에서 \"RAM\"은 무엇의 약자인가?\n\n**선택지:**\na) Random Access Memory  \nb) Readable Algorithm Module  \nc) Real-time Arithmetic Machine  \nd) Rapid Application Model  \n\n**해설:**\n정답: a) Random Access Memory\n\n해설: RAM(랜덤 액세스 메모리)은 컴퓨터에서 데이터를 읽고 쓰는 데 사용되는 메모리 유형을 가리키며, \"Random Access Memory\"의 약자입니다.\n\nb) Readable Algorithm Module: 올바른 용어나 개념이 아닙니다.\nc) Real-time Arithmetic Machine: 올바른 용어나 개념이 아닙니다.\nd) Rapid Application Model: 올바른 용어나 개념이 아닙니다.\n\n\n### 문제 3:\n컴퓨터 네트워크에서 다른 컴퓨터로 파일을 전송하기 위해 사용되는 프로토콜은 무엇인가?\n\n**선택지:**\na) HTML  \nb) TCP  \nc) JPEG  \nd) SMTP  \n\n**해설:**\n정답: d) SMTP\n\n해설: SMTP(Simple Mail Transfer Protocol)은 전자 메일을 전송하기 위해 사용되는 표준 프로토콜입니다.\n\na) HTML: 하이퍼텍스트 마크업 언어로, 파일 전송에 직접 사용되지 않습니다.\nb) TCP: 전송 제어 프로토콜로, 데이터 전송을 보장하기 위해 사용되지만 직접적인 파일 전송에는 사용되지 않습니다.\nc) JPEG: 이미지 파일 형식으로, 파일 전송을 위한 프로토콜이 아닙니다.\n\n\n### 문제 4:\n다음 중 객체지향 프로그래밍 언어가 아닌 것은 무엇인가?\n\n**선택지:**\na) Java  \nb) Python  \nc) C  \nd) C++  \n\n**해설:**\n정답: c) C\n\n해설: C는 절차지향 프로그래밍 언어로, 객체지향 프로그래밍의 특징을 갖추고 있지 않습니다. 나머지 세 언어인 Java, Python, C++은 모두 객체지향 프로그래밍 언어입니다.\n\na) Java: 객체지향 프로그래밍 언어입니다.\nb) Python: 객체지향 프로그래밍 언어입니다.\nd) C++: 객체지향 프로그래밍 언어입니다.
