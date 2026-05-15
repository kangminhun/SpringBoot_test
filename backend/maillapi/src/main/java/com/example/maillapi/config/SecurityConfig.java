package com.example.maillapi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration        // 이 클래스가 설정 파일이라고 Spring에게 알려줌
public class SecurityConfig {

    @Bean             // Spring이 이 메서드를 실행해서 객체를 관리하게 함
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            // ↓ CORS 설정 (어디서 오는 요청을 허용할지)
            .cors(cors -> cors.configurationSource(corsConfigurationSource()))

            // ↓ CSRF 비활성화
            // CSRF는 악의적인 사이트가 로그인된 유저 대신 요청을 보내는 공격을 막는 것
            // REST API는 토큰으로 인증하기 때문에 비활성화해도 괜찮아요
            .csrf(csrf -> csrf.disable())

            // ↓ 어떤 요청을 허용/차단할지 설정
            .authorizeHttpRequests(auth -> auth
                // /api/auth/ 로 시작하는 주소는 누구나 접근 가능 (회원가입, 로그인)
                .requestMatchers("/api/auth/**").permitAll()
                // 나머지 모든 요청은 로그인한 사람만 가능
                .anyRequest().authenticated()
            );

        return http.build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration config = new CorsConfiguration();

        // ↓ 허용할 프론트 주소 (React)
        config.setAllowedOrigins(List.of("http://localhost:3000"));

        // ↓ 허용할 HTTP 메서드
        // GET   → 데이터 조회
        // POST  → 데이터 생성
        // PUT   → 데이터 수정
        // DELETE → 데이터 삭제
        config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE"));

        // ↓ 모든 헤더 허용 (* = 전부)
        config.setAllowedHeaders(List.of("*"));

        // ↓ 쿠키/인증정보 허용
        config.setAllowCredentials(true);

        // ↓ 위 설정을 모든 경로(**)에 적용
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return source;
    }
}
