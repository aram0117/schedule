package com.schedule.service;

import com.schedule.dto.*;
import com.schedule.entity.UserEntity;
import com.schedule.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
// 파이널 필드를 매개변수로 받는 생성자를 자동생성하는 어노테이션
@RequiredArgsConstructor
public class UserService {
    // 유저 레포지토리 필드
    private final UserRepository userRepository;


    // 유저 생성
    @Transactional
    public CreateUserResponse save(CreateUserRequest request) {
        // 유저 요청
        UserEntity user = new UserEntity(
                request.getUserName(),
                request.getEmail()
        );
        // 유저 저장
        UserEntity saveUser = userRepository.save(user);
        return new CreateUserResponse(
                saveUser.getUserId(),
                saveUser.getUserName(),
                saveUser.getEmail(),
                saveUser.getCreatedAt(),
                saveUser.getModifiedAt()
        );
    }

    // 유저 다 건 조회
    @Transactional(readOnly = true)
    public List<GetUserResponse> getAll() {
        // 유저 전체 찾기
        List<UserEntity> users = userRepository.findAll();
        // 전체조회를 위한 리스트 선언
        List<GetUserResponse> userDtoList = new ArrayList<>();
        for (UserEntity user : users) {
            GetUserResponse userDto = new GetUserResponse(
                    user.getUserId(),
                    user.getUserName(),
                    user.getEmail(),
                    user.getCreatedAt(),
                    user.getModifiedAt()
            );
            // 반환할 유저들을 리스트에 추가
            userDtoList.add(userDto);
        }
        // 유저 리스트 반환
        return userDtoList;
    }

    // 유저 단 건 조회
    @Transactional(readOnly = true)
    public GetUserResponse getOne(Long userId) {
        // 유저 아이디로 유저를 찾고 없으면 예외처리
        UserEntity user = userRepository.findById(userId).orElseThrow(
                () -> new IllegalStateException("유저가 존재하지 않습니다")
        );
        return new GetUserResponse(
                user.getUserId(),
                user.getUserName(),
                user.getEmail(),
                user.getCreatedAt(),
                user.getModifiedAt()
        );
    }
}
