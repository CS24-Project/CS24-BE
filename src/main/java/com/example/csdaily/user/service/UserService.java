package com.example.csdaily.user.service;

import com.example.csdaily.user.entity.User;
import com.example.csdaily.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public Optional<User> getUserByKakaoId(String kakaoId) {
        return userRepository.findByKakaoId(kakaoId);
    }
}
