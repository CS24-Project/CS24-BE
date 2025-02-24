package com.example.csdaily.quiz.entity;

import com.example.csdaily.quiz.dto.request.UserAnswerDto;
import com.example.csdaily.user.entity.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserAnswer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn
    @ManyToOne
    private User user;

    @JoinColumn
    @ManyToOne(fetch = FetchType.EAGER)
    private Quiz quiz;

    @JoinColumn
    @OneToOne(fetch = FetchType.EAGER)
    private QuizChoice choice;
}
