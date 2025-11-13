package com.schedule.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "users")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserEntity extends BaseEntity {
    // 유저 DB
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    private String userName;
    private String email;

    public UserEntity(String userName, String email) {
        this.userName = userName;
        this.email = email;
    }

    // 업데이트 구현시 반환
    public void update(String userName, String email) {
        this.userName = userName;
        this.email = email;
    }
}
