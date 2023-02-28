package com.sparta.todo.dto.responseDto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.MessageSource;
import org.springframework.validation.FieldError;

import java.util.Locale;

@Getter
@Setter
public class FieldErrorDetail {
    private String field;

    private Object rejectedValue;
    private String message;

    public FieldErrorDetail(String field, String message, Object rejectedValue) {
        this.field = field;
        this.rejectedValue = rejectedValue;
        this.message = message;
    }
}
