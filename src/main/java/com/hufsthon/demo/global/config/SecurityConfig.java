package com.hufsthon.demo.global.config;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hufsthon.demo.auth.member.repository.MemberJPARepository;
import com.hufsthon.demo.auth.refreshtoken.service.RefreshTokenCommandService;
import com.hufsthon.demo.global.security.jwt.CustomLogoutFilter;
import com.hufsthon.demo.global.security.jwt.JwtFilter;
import com.hufsthon.demo.global.security.jwt.JwtUtil;
import com.hufsthon.demo.global.security.jwt.LoginFilter;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

	//Swagger 경로
	private static final String[] SWAGGER_PATHS = {
		"/swagger-ui.html",
		"/swagger-ui/**",
		"/api-docs/**",
		"/api-docs",
		"/v3/api-docs/**",
		"/v3/api-docs",
		"/swagger-resources/**",
		"/webjars/**"
	};

	private final AuthenticationConfiguration authenticationConfiguration;
	private final JwtUtil jwtUtil;
	private final ObjectMapper objectMapper;
	private final RefreshTokenCommandService refreshTokenService;
	private final JwtFilter jwtFilter;

	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
		return configuration.getAuthenticationManager();
	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http, AuthenticationConfiguration authenticationConfiguration,
		MemberJPARepository memberJPARepository, JwtFilter jwtFilter) throws Exception {

		// 로그인 필터 설정
		LoginFilter loginFilter = new LoginFilter(
			authenticationManager(authenticationConfiguration),
			objectMapper,
			jwtUtil,
			refreshTokenService);
		
		// 로그아웃 필터 생성
		CustomLogoutFilter logoutFilter = new CustomLogoutFilter(
			jwtUtil,
			refreshTokenService,
			objectMapper
		);

		http
			.csrf((auth) -> auth.disable())
			.formLogin((auth) -> auth.disable())
			.httpBasic((auth) -> auth.disable())
			.authorizeHttpRequests((auth) -> auth
				.requestMatchers("/", "/health", "/api/v1/auth/**")
				.permitAll()
				.requestMatchers("/swagger-ui/**", "/api-docs/**", "/swagger-ui.html", "/v3/api-docs/**")
				.permitAll() // 이 줄 수정
				.requestMatchers("/admin")
				.hasRole("ADMIN")
				.anyRequest()
				.authenticated())
			.addFilterBefore(jwtFilter, LoginFilter.class)
			.addFilterAt(loginFilter, UsernamePasswordAuthenticationFilter.class)
			.addFilterBefore(logoutFilter, LogoutFilter.class)
			.sessionManagement((session) -> session
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

		// CORS 설정
		http.cors(cors -> cors.configurationSource(corsConfigurationSource()));

		return http.build();
	}

	@Bean
	public CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration configuration = new CorsConfiguration();
		configuration.setAllowedOrigins(
			Arrays.asList("http://localhost:3000", "http://127.0.0.1:3000", "https://dev.morningbuddies.shop"));
		configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
		configuration.setAllowCredentials(true);
		configuration.setAllowedHeaders(Arrays.asList("*"));
		configuration.setExposedHeaders(Arrays.asList("access"));
		configuration.setMaxAge(3600L);
		configuration.setExposedHeaders(Arrays.asList("Authorization"));

		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration);
		return source;
	}
}