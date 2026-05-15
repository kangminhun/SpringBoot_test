package com.example.maillapi.service;

import com.example.maillapi.dto.LoginRequest;
import com.example.maillapi.dto.LoginResponse;
import com.example.maillapi.dto.RegisterRequest;
import com.example.maillapi.entity.User;
import com.example.maillapi.repository.UserRepository;
import com.example.maillapi.util.JwtUtil;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service // 이 클래스가 서비스 역할이라고 선언
@RequiredArgsConstructor // Lombok이 생성자 자동 생성
public class AuthService {
  private final UserRepository userRepository;
  private final BCryptPasswordEncoder passwordEncoder;

  public String register(RegisterRequest request) {
    // 아이디 중복 체크
    if (userRepository.existsByUsername(request.getUsername())) {
      return "이미 존재하는 ID입니다";
    }
    // 비번 암호화
    String encodedPassword = passwordEncoder.encode(request.getPassword());

    User user = User.builder()
        .username(request.getUsername())
        .password(encodedPassword)
        .role(request.getRole())
        .build();

    userRepository.save(user);

    return "회원가입 완료";
  }

  private final JwtUtil jwtUtil;

  public LoginResponse login(LoginRequest request) {

    // 1. 아이디로 유저 조회
    User user = userRepository.findByUsername(request.getUsername());
    if (user == null) {
      throw new RuntimeException("존재하지 않는 아이디입니다.");
    }

    // 2. 비밀번호 확인 (입력한 비번 vs DB 암호화된 비번 비교)
    if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
      throw new RuntimeException("비밀번호가 틀렸습니다.");
    }

    // 3. JWT 토큰 발급
    String token = jwtUtil.generateToken(user.getUsername(), user.getRole().name());

    // 4. 응답 반환
    return new LoginResponse(token, user.getRole().name(), user.getUsername());
  }
}
