package com.sparta.todo.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ExceptionDto {
    private int status;
    private String message;
    private String code;

}
