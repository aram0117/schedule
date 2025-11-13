package com.schedule.controller;

import com.schedule.dto.*;
import com.schedule.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
// 파이널 필드를 매개변수로 받는 생성자를 자동생성하는 어노테이션
@RequiredArgsConstructor
public class UserController {
    // 유저 서비스 필드
    private final UserService userService;

    // 응답시 ResponseEntity클래스 의 상태 메서드에서 enum을 사용하여 상태코드를 반환

    // 유저 생성
    @PostMapping("/users")
    public ResponseEntity<CreateUserResponse> createUser(@RequestBody CreateUserRequest request) {
       CreateUserResponse result =userService.save(request);
       return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    // 유저 다 건 조회
    @GetMapping("/users")
    public ResponseEntity<List<GetUserResponse>> getUsers() {
        List<GetUserResponse> result = userService.getAll();
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    // 유저 단 건 조회
    @GetMapping("/users/{userId}")
    public ResponseEntity<GetUserResponse> getUser(@PathVariable Long userId) {
        GetUserResponse result = userService.getOne(userId);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    // 유저 수정
    @PutMapping("/users/{userId}")
    public ResponseEntity<UpdateUserResponse> updateUser(
            @PathVariable Long userId,
            @RequestBody UpdateUserRequest request) {
        UpdateUserResponse result = userService.update(userId, request);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    // 유저 삭제
    @DeleteMapping("/users/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long userId) {
        userService.deleteUser(userId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}

