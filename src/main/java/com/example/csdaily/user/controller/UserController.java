package com.example.csdaily.user.controller;

import com.example.csdaily.user.entity.User;
import com.example.csdaily.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Optional;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/{kakaoId}")
    public Optional<User> getUser(@PathVariable String kakaoId) {
        return userService.getUserByKakaoId(kakaoId);
    }
}
