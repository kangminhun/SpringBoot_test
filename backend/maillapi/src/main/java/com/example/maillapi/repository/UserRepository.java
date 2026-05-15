package com.example.maillapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.maillapi.entity.User;

public interface  UserRepository extends JpaRepository<User, Long>{
    // JpaRepository를 상속하면 save(), findAll(), findById() 등 기본 제공
    
    // 아이디 중복 체크용 (Spring이 이름 보고 자동으로 쿼리 생성)
    boolean existsByUsername(String username);

    // 로그인할 때 아이디로 유저 찾기
    User findByUsername(String username);
}
