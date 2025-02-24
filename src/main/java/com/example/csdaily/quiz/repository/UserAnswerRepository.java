package com.example.csdaily.quiz.repository;

import java.time.LocalDate;

import com.example.csdaily.quiz.entity.UserAnswer;
import com.example.csdaily.user.entity.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserAnswerRepository extends JpaRepository<UserAnswer, Long> {
    @Query("SELECT count(*) > 0 FROM UserAnswer ua JOIN Quiz q ON ua.quiz = q WHERE q.createdAt = :today and ua.user = :user")
    boolean existsByQuizEqualsCreatedAtToToday(@Param("user") User user, @Param("today") LocalDate today);
}
