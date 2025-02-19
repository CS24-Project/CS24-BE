package com.example.csdaily.quiz.entity;

import com.example.csdaily.quiz.util.QuizDifficultyConverter;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Quiz {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private LocalDateTime createdAt;

    @Column
    private String content;

    @Column
    private String hint;

    @Column(name="difficulty")
    @Convert(converter= QuizDifficultyConverter.class)
    private QuizDifficulty difficulty;

    @Column
    private Integer correctAnswerNumber;
}
