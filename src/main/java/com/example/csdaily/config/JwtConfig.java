package com.example.csdaily.config;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.example.csdaily.auth.service.AuthService;
import com.example.csdaily.security.JwtTokenInterceptor;
import com.example.csdaily.security.JwtTokenProvider;
import com.example.csdaily.security.LoginUserArgumentResolver;
import com.example.csdaily.user.repository.UserRepository;

import lombok.AllArgsConstructor;

@Configuration
@AllArgsConstructor
public class JwtConfig implements WebMvcConfigurer {
	private final JwtTokenInterceptor jwtTokenInterceptor;
	private final LoginUserArgumentResolver loginUserArgumentResolver;

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(jwtTokenInterceptor);
	}

	@Override
	public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
		resolvers.add(loginUserArgumentResolver);
	}
}
