package com.schedule.userdto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class UpdateUserRequest {

    @NotBlank(message = "유저 이름을 입력하세요.")
    @Size(max = 4, message = "4글자 이내로 입력하세요.")
    private String userName;
    @NotBlank(message = "이메일을 입력하세요.")
    @Pattern(
            regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$",
            message = "유효한 이메일 형식이 아닙니다."
    )
    private String email;
    @NotBlank(message = "비밀번호를 입력하세요.")
    @Pattern(
            regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[!@#$%^&*])[A-Za-z\\d!@#$%^&*]{8,}$",
            message = "비밀번호는 최소 8자, 대문자 1개, 소문자 1개, 숫자 1개, 특수문자 1개를 포함해야 합니다."
    )
    private String password;
}
