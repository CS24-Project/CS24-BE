package com.example.csdaily.quiz.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class QuizChoice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn
    @ManyToOne
    private Quiz quiz;

    @Column
    private Integer choiceNumber;

    @Column
    private String content;

    @Column
    private Boolean isCorrect;

    @Column
    private String commentary;

    public boolean isCorrect() {
        return this.isCorrect;
    }
}
