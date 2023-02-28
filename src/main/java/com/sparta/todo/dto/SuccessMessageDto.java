package com.sparta.todo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class SuccessMessageDto {
    private String msg;
    private int statusCode;
}
