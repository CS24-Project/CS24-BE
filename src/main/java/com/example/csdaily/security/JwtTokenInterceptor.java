package com.example.csdaily.security;

import java.util.Optional;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.example.csdaily.auth.service.AuthService;
import com.example.csdaily.user.entity.User;
import com.example.csdaily.user.repository.UserRepository;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@AllArgsConstructor
public class JwtTokenInterceptor implements HandlerInterceptor {
	private final UserRepository userRepository;
	private final JwtTokenProvider jwtTokenProvider;

	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		String accessToken = request.getHeader("Authorization").replace("Bearer ", "");
		log.info("Access token : {}", accessToken);

		Long userId = jwtTokenProvider.getUserIdFromAccessToken(accessToken);
		Optional<User> maybeUser = userRepository.findById(userId);
		request.setAttribute("user", maybeUser);
		log.info("User id in token : {}", maybeUser.orElse(null));

		return true;
	}
}
