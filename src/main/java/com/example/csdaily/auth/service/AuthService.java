package com.example.csdaily.auth.service;

import com.example.csdaily.user.entity.User;
import com.example.csdaily.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final RestTemplate restTemplate;

    @Value("${kakao.client-id}")
    private String kakaoClientId;

    @Value("${kakao.client-secret}")
    private String kakaoClientSecret;

    @Value("${kakao.redirect-uri}")
    private String kakaoRedirectUri;

    // 카카오 API로 액세스 토큰 요청
    public String getKakaoAccessToken(String code) {
        String tokenUrl = "https://kauth.kakao.com/oauth/token?"
                + "grant_type=authorization_code"
                + "&client_id=" + kakaoClientId
                + "&client_secret=" + kakaoClientSecret
                + "&redirect_uri=" + kakaoRedirectUri
                + "&code=" + code;

        Map<String, Object> response = restTemplate.postForObject(tokenUrl, null, Map.class);
        return response.get("access_token").toString();
    }

    // 카카오 API로 유저 정보 요청 및 DB 저장
    public Map<String, Object> getKakaoUserInfo(String accessToken) {
        String userInfoUrl = "https://kapi.kakao.com/v2/user/me";

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + accessToken);
        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<Map> response = restTemplate.exchange(userInfoUrl, HttpMethod.GET, entity, Map.class);
        Map<String, Object> userInfo = response.getBody();

        // 카카오 ID, 이메일, 닉네임 추출
        String kakaoId = userInfo.get("id").toString();
        Map<String, Object> kakaoAccount = (Map<String, Object>) userInfo.get("kakao_account");
        Map<String, Object> properties = (Map<String, Object>) userInfo.get("properties");

        String email = kakaoAccount.get("email") != null ? kakaoAccount.get("email").toString() : "no-email";
        String nickname = properties.get("nickname").toString();

        // DB에 사용자 저장 (신규 사용자 등록)
        Optional<User> existingUser = userRepository.findByKakaoId(kakaoId);
        if (existingUser.isEmpty()) {
            User newUser = new User(kakaoId, email, nickname);
            userRepository.save(newUser);
        }

        return userInfo;
    }
}
