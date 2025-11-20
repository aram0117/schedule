package com.schedule.controller;

import com.schedule.logindto.LoginRequest;
import com.schedule.logindto.SessionUser;
import com.schedule.service.LoginService;
import com.schedule.userdto.*;
import com.schedule.service.UserService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
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
    // 로그인 서비스 필드
    private final LoginService loginService;

    // 응답시 ResponseEntity클래스 의 상태 메서드에서 enum을 사용하여 상태코드를 반환

    // 유저 생성
    @PostMapping("/users")
    public ResponseEntity<CreateUserResponse> createUser(@Valid @RequestBody CreateUserRequest request) {
       CreateUserResponse result = userService.createUser(request);
       return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    // 유저 로그인
    @PostMapping("/login")
    public ResponseEntity<SessionUser> login(@RequestBody LoginRequest request, HttpSession httpSession) {
        try {
            SessionUser sessionUser = loginService.login(request);
            // 세션에 세션키와 유저정보 저장
            httpSession.setAttribute("userId", sessionUser);
            return ResponseEntity.status(HttpStatus.OK).body(sessionUser);
        }
        // 이메일과 비밀번호가 일치하지 않을 때 401 상태코드
        catch(Exception e){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    // 유저 로그아웃
    @PostMapping("logout")
    public ResponseEntity<Void> logout(HttpSession httpSession) {
        loginService.logout(httpSession);
        return ResponseEntity.status(HttpStatus.OK).build();
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
            @Valid @RequestBody UpdateUserRequest request) {
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

