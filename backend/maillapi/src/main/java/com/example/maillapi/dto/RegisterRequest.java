package com.example.maillapi.dto;

import com.example.maillapi.entity.User;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class RegisterRequest {
  private String username;  // 프론트에서 보내는 아이디
  private String password;  // 프론트에서 보내는 비밀번호
  private User.Role role;      // "CUSTOMER" or "SELLER"
}
