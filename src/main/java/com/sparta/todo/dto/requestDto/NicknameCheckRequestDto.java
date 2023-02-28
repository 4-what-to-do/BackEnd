package com.sparta.todo.dto.requestDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NicknameCheckRequestDto {
    @NotBlank(message = "닉네임은 필수 입력 값입니다.")
    @Pattern(regexp= "[a-zA-Z|0-9]{2,10}", message = "닉네임은 영문 대,소문자와 숫자로 구성된 2자 ~ 10자리여야 합니다.")
    private String nickname;
}
