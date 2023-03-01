package com.sparta.todo.service;

import com.sparta.todo.dto.SuccessMessageDto;
import com.sparta.todo.dto.requestDto.EmailCheckRequestDto;
import com.sparta.todo.dto.requestDto.LoginRequestDto;
import com.sparta.todo.dto.requestDto.NicknameCheckRequestDto;
import com.sparta.todo.dto.requestDto.SignUpRequestDto;
import com.sparta.todo.dto.responseDto.LoginResponseDto;
import com.sparta.todo.entity.User;
import com.sparta.todo.entity.UserRoleEnum;
import com.sparta.todo.exception.CustomException;
import com.sparta.todo.jwt.JwtUtil;
import com.sparta.todo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.regex.Pattern;

import static com.sparta.todo.exception.Error.*;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    private final JwtUtil jwtUtil;

    @Transactional
    public ResponseEntity<SuccessMessageDto> addUser(SignUpRequestDto requestDto) {
        String email = requestDto.getEmail();
        String nickname = requestDto.getNickname();
        String password = requestDto.getPassword();
        String passwordCheck = requestDto.getPasswordCheck();

        // email 중복 확인
        Optional<User> foundByEmail = userRepository.findByEmail(email);
        if (foundByEmail.isPresent())
            throw new CustomException(DUPLICATED_EMAIL);
        // 닉네임 중복 확인
        Optional<User> foundByNickname = userRepository.findByNickname(nickname);
        if (foundByNickname.isPresent())
            throw new CustomException(DUPLICATED_NICKNAME);
        // 비밀번호 일치 여부 확인
        if (!passwordCheck.equals(password))
            throw new CustomException(WRONG_PASSWORD_CHECK);

//        password = passwordEncoder.encode(requestDto.getPassword());

        User user = User.builder().email(email).nickname(nickname).password(password).build();
        userRepository.save(user);

        return ResponseEntity.ok().body(new SuccessMessageDto("회원 가입 성공", HttpStatus.OK.value()));
    }

    // 이메일 검사(중복 및 유효성)
    @Transactional(readOnly = true)
    public ResponseEntity checkEmail(String email) {
        if(!Pattern.matches("^[_a-zA-Z0-9-\\.]+@[\\.a-zA-Z0-9-]+\\.[a-zA-Z]+$", email))
            throw new CustomException(VALIDATE_EMAIL_ERROR);


        // 이메일 중복 확인
        Optional<User> found = userRepository.findByEmail(email);
        if (found.isPresent())
            throw new CustomException(DUPLICATED_EMAIL);

        return ResponseEntity.ok().body(new SuccessMessageDto("사용 가능한 이메일입니다.", HttpStatus.OK.value()));
    }

    // 닉네임 검사(중복 및 유효성)
    @Transactional(readOnly = true)
    public ResponseEntity checkNickname(String nickname) {
        if(!Pattern.matches("[a-zA-Z|0-9]{2,10}", nickname))
            throw new CustomException(VALIDATE_NICKNAME_ERROR);

        // 닉네임 중복 확인
        Optional<User> found = userRepository.findByNickname(nickname);
        if (found.isPresent())
            throw new CustomException(DUPLICATED_NICKNAME);

        return ResponseEntity.ok().body(new SuccessMessageDto("사용 가능한 닉네임입니다.", HttpStatus.OK.value()));
    }

    // 로그인
    @Transactional(readOnly = true)
    public ResponseEntity loginUser(LoginRequestDto requestDto) {
        String email = requestDto.getEmail();
        String password = requestDto.getPassword();

//        password = passwordEncoder.encode(password);

        // 사용자 확인
        Optional<User> found = userRepository.findByEmail(email);
        if (!found.isPresent())
            throw new CustomException(NOT_FOUND_USER);

        System.out.println("found.get().getPassword() = " + found.get().getPassword());
        System.out.println("password = " + password);

        // 비밀번호 확인
        if(!found.get().getPassword().equals(password))
            throw new CustomException(NOT_FOUND_USER);
        String token = jwtUtil.createToken(found.get().getEmail(), UserRoleEnum.USER);


        return ResponseEntity.ok().header(JwtUtil.AUTHORIZATION_HEADER, token.split(" ")[1])
                .body(new LoginResponseDto("로그인 성공", HttpStatus.OK.value(), token.split(" ")[1]));
    }

}
