package com.sparta.todo.dto.requestDto;

import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SignUpRequestDto {
    @NotBlank(message = "이메일은 필수 입력 값입니다.")
    @Email(message = "이메일 형식에 맞지 않습니다.")
    private String email;

    @NotBlank(message = "닉네임은 필수 입력 값입니다.")
    @Pattern(regexp= "[a-zA-Z|0-9]{2,10}", message = "닉네임은 영문 대,소문자와 숫자로 구성된 2자 ~ 10자리여야 합니다.")
    private String nickname;

    @NotBlank(message = "비밀번호는 필수 입력 값입니다.")
    @Pattern(regexp="[a-zA-Z|0-9]{4,10}",
            message = "비밀번호는 영문 대,소문자와 숫자로 구성된 4자 ~ 12자리여야 합니다.")
    private String password;

    @NotBlank(message = "비밀번호는 필수 입력 값입니다.")
    @Pattern(regexp="[a-zA-Z|0-9]{4,10}",
            message = "비밀번호는 영문 대,소문자와 숫자로 구성된 4자 ~ 12자리여야 합니다.")
    private String passwordCheck;
}
