package com.example.maillapi.util;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {
  @Value("${jwt.secret}")
  private String secret;

  @Value("${jwt.expiration}")
  private long expiration;

  // 토큰 생성
  public String generateToken(String username, String role) {
    Key key = Keys.hmacShaKeyFor(secret.getBytes());

    return Jwts.builder()
        .setSubject(username) // 토큰 안에 유저 아이디 저장
        .claim("role", role) // 토큰 안에 역할 저장
        .setIssuedAt(new Date()) // 발급 시간
        .setExpiration(new Date(System.currentTimeMillis() + expiration)) // 만료 시간
        .signWith(key, SignatureAlgorithm.HS256) // 암호화 방식
        .compact();
  }

  // 토큰에서 유저 아이디 꺼내기
  public String getUsernameFromToken(String token) {
    Key key = Keys.hmacShaKeyFor(secret.getBytes());
    return Jwts.parserBuilder()
        .setSigningKey(key)
        .build()
        .parseClaimsJws(token)
        .getBody()
        .getSubject();
  }
}
