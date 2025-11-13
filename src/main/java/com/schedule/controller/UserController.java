package com.schedule.controller;

import com.schedule.dto.*;
import com.schedule.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
// 파이널 필드를 매개변수로 받는 생성자를 자동생성하는 어노테이션
@RequiredArgsConstructor
public class UserController {
    // 유저 서비스 필드
    private final UserService userService;

    // 유저 생성
    @PostMapping("/users")
    public CreateUserResponse createUser(@RequestBody CreateUserRequest request) {
        return userService.save(request);
    }

    // 유저 다 건 조회
    @GetMapping("/users")
    public List<GetUserResponse> getUsers() {
        return userService.getAll();
    }

    // 유저 단 건 조회
    @GetMapping("/users/{userId}")
    public GetUserResponse getUser(@PathVariable Long userId) {
        return userService.getOne(userId);
    }

    // 유저
}
