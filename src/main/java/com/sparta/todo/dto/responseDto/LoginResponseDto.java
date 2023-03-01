package com.sparta.todo.dto.responseDto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginResponseDto {

    private String msg;
    private int statusCode;

    private String token;

    public LoginResponseDto(String msg, int statusCode, String token) {
        this.msg = msg;
        this.statusCode = statusCode;
        this.token = token;
    }

}
