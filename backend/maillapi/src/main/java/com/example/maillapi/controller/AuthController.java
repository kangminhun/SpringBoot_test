package com.example.maillapi.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.maillapi.dto.LoginRequest;
import com.example.maillapi.dto.LoginResponse;
import com.example.maillapi.dto.RegisterRequest;
import com.example.maillapi.service.AuthService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController // JSON 응답을 반환하는 컨트롤러
@RequestMapping("/api/auth") // 기본 주소: /api/auth
@CrossOrigin(origins = "http://localhost:3000") // React(3000번)의 요청 허용
@RequiredArgsConstructor
@Tag(name = "회원관리", description = "회원가입, 로그인 API")
public class AuthController {
  private final AuthService authService;

  @PostMapping("/register")
  @Operation(summary = "회원가입",description = "아이디,비밀번호, 역할을 입력해서 가입")
  public String register(@RequestBody RegisterRequest request) {
    return authService.register(request);
  }

  @PostMapping("/login")
  @Operation(summary = "로그인",description = "아이디,비밀번호로 로그인")
  public LoginResponse login(@RequestBody LoginRequest request) {
    return authService.login(request);
  }
}
