package com.example.csdaily.user.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "users")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String kakaoId;

    private String email;
    private String nickname;

    public User(String kakaoId, String email, String nickname) {
        this.kakaoId = kakaoId;
        this.email = email;
        this.nickname = nickname;
    }
}

