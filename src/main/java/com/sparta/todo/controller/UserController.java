package com.sparta.todo.controller;

import com.sparta.todo.dto.SuccessMessageDto;
import com.sparta.todo.dto.requestDto.EmailCheckRequestDto;
import com.sparta.todo.dto.requestDto.LoginRequestDto;
import com.sparta.todo.dto.requestDto.NicknameCheckRequestDto;
import com.sparta.todo.dto.requestDto.SignUpRequestDto;
import com.sparta.todo.dto.responseDto.FieldErrorDetail;
import com.sparta.todo.jwt.JwtUtil;
import com.sparta.todo.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    // 회원 가입
    @PostMapping("/signup")
    public ResponseEntity userAdd(@Valid @RequestBody SignUpRequestDto requestDto, BindingResult bindingResult) {

        // 유효성 검사(이메일, 비밀번호, 닉네임 형식)
        List<FieldErrorDetail> detailList = new ArrayList<>();
        if (bindingResult.hasErrors()) {
            for (FieldError e : bindingResult.getFieldErrors()) {
                detailList.add(new FieldErrorDetail(e.getField(), e.getDefaultMessage(), e.getRejectedValue().toString()));
            }
            return ResponseEntity.badRequest().body(detailList);
        }
        // 닉네임 중복, 이메일 중복, 비밀번호 일치 여부 확인
        return userService.addUser(requestDto);
    }

    // 이메일 중복 검사
    @GetMapping("/email-check")
    public ResponseEntity emailCheck(@RequestParam String email) {
        // 이메일 중복 및 유효성 검사
        return userService.checkEmail(email);
    }

    // 닉네임 중복 검사
    @GetMapping("/nickname-check")
    public ResponseEntity nicknameCheck(@RequestParam String nickname) {
        // 닉네임 중복 체크
        return userService.checkNickname(nickname);
    }

    // 로그인
    @PostMapping("/login")
    public ResponseEntity<SuccessMessageDto> userLogin(@Valid @RequestBody LoginRequestDto requestDto) {
        return userService.loginUser(requestDto);
    }
}

