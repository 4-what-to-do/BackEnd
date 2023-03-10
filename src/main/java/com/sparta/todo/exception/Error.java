package com.sparta.todo.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public enum Error {

    // 400 BAD_REQUEST 잘못된 요청
    NOT_FOUND_USER(HttpStatus.NOT_FOUND, "400", "아이디 비밀번호 오류입니다."),
    WRONG_TOKEN(HttpStatus.BAD_REQUEST, "400", "토큰 오류"),
    WRONG_PASSWORD_CHECK(HttpStatus.BAD_REQUEST, "400", "비밀번호, 비밀번호 확인 값 서로 불일치"),
    VALIDATE_EMAIL_ERROR(HttpStatus.BAD_REQUEST, "400", "이메일 형식이 아닙니다."),

    NOT_FOUND_DATE(HttpStatus.BAD_REQUEST, "400", "일정을 찾을 수 없습니다."),

    VALIDATE_NICKNAME_ERROR(HttpStatus.BAD_REQUEST, "400", "닉네임은 알파벳 대, 소문자, 숫자로 구성된 2-12자리여야 한다."),

    NOT_EXIST_USER(HttpStatus.BAD_REQUEST, "400", "회원을 찾을 수 없습니다."),

    // 401 UNAUTHORIZED 비인증
    INVALID_AUTHORIZED(HttpStatus.UNAUTHORIZED, "401", "사용자 미인증"),

    WRONG_LIKE_REQUEST(HttpStatus.BAD_REQUEST, "400", "잘못된 값을 요청하였습니다."),




    // 409 CONFLICT 중복된 리소스
    DUPLICATED_EMAIL(HttpStatus.CONFLICT, "409", "이미 존재하는 이메일입니다."),
    DUPLICATED_NICKNAME(HttpStatus.CONFLICT, "409", "이미 존재하는 닉네임입니다.");

    private final HttpStatus httpStatus;
    private final String errorCode;
    private final String errorMessage;
}

