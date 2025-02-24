package com.example.csdaily.quiz.entity;

import com.example.csdaily.quiz.util.QuizDifficultyConverter;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

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
    private LocalDate createdAt;

    @Column
    private String content;

    @Column
    private String hint;

    @Column(name="difficulty")
    @Convert(converter= QuizDifficultyConverter.class)
    private QuizDifficulty difficulty;

    @OneToMany(mappedBy = "quiz")
    private List<QuizChoice> choices;
}
