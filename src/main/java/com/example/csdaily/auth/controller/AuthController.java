package com.example.csdaily.auth.controller;

import com.example.csdaily.auth.service.AuthService;
import com.example.csdaily.security.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;
    private final JwtTokenProvider jwtTokenProvider;

    // 프론트에서 인가 코드 전달 → 카카오 토큰 요청 → JWT 발급
    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> kakaoLogin(@RequestParam("code") String code) {
        String accessToken = authService.getKakaoAccessToken(code);
        Map<String, Object> userInfo = authService.getKakaoUserInfo(accessToken);
        String jwtToken = jwtTokenProvider.generateToken(userInfo.get("id").toString());

        return ResponseEntity.ok(Map.of("accessToken", jwtToken));
    }

    // 인가 코드 발급받을 임시 엔드포인트 (프론트 구현되면 삭제 예정)
    @GetMapping("/callback")
    public ResponseEntity<?> kakaoCallback(@RequestParam("code") String code) {
        return ResponseEntity.ok(Map.of(
                "message", "인가 코드 수신 완료",
                "code", code
        ));
    }
}

