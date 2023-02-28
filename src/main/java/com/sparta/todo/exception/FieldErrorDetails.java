package com.sparta.todo.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.MessageSource;
import org.springframework.validation.FieldError;

import java.util.Locale;

@Getter
@Setter
@AllArgsConstructor
public class FieldErrorDetails {

    private String field;
    private String code;
    private Object rejectedValue;
    private String message;

    public static FieldErrorDetails create(FieldError fieldError, MessageSource messageSource, Locale locale) {
        return new FieldErrorDetails(
                fieldError.getField(),
                fieldError.getCode(),
                fieldError.getRejectedValue(),
                messageSource.getMessage(fieldError, locale));
    }

}