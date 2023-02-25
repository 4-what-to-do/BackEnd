package com.sparta.todo.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RestApiExceptionHandler {
    @ExceptionHandler(value = { CustomException.class })
    public ResponseEntity<ExceptionDto> handleApiRequestException(CustomException ex) {

        return ResponseEntity.badRequest().body(new ExceptionDto(ex.getError().getHttpStatus().value(), ex.getError().getErrorCode(), ex.getError().getErrorMessage())

        );
    }
}