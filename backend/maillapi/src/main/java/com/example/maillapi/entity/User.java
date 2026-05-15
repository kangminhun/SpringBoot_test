package com.example.maillapi.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;

@Entity                    // 이 클래스가 DB 테이블이라고 선언
@Table(name = "users")     // 연결할 테이블 이름
@Getter @Setter            // Lombok이 자동으로 get/set 메서드 생성
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // AUTO_INCREMENT
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;   // 아이디

    @Column(nullable = false)
    private String password;   // 비밀번호 (암호화해서 저장)

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;         // CUSTOMER or SELLER

    private LocalDateTime createdAt;

    @PrePersist               // DB 저장 직전에 자동 실행
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
    }

    public enum Role {
        CUSTOMER, SELLER
    }
}
