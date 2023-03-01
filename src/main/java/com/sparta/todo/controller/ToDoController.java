package com.sparta.todo.controller;

import com.sparta.todo.dto.SuccessMessageDto;
import com.sparta.todo.dto.requestDto.Request;
import com.sparta.todo.dto.requestDto.ToDoRequestDto;
import com.sparta.todo.dto.responseDto.ToDoResponseDto;
import com.sparta.todo.security.UserDetailsImpl;
import com.sparta.todo.service.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/posts")
public class ToDoController {

    private final TodoService todoService;


    // 포스트 작성
    @PostMapping("/todo")
    public ResponseEntity createToDoFirst(@RequestBody Request request, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return todoService.createToDo(request, userDetails.getUser().getId());
    }

    // 일정 조회
    @GetMapping("/todo")
    public List<ToDoResponseDto> readToDo(@RequestParam String date, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return todoService.readToDo(date, userDetails.getUser());
    }

    // 일정 완료 체크
    @PutMapping("/todo/done/{todoId}")
    public ToDoResponseDto doneCheck(@PathVariable Long todoId, @AuthenticationPrincipal UserDetailsImpl userDetails){
        return todoService.doneCheck(todoId);
    }

    // 일정 수정
    @PutMapping("/todo/{todoId}")
    public ToDoResponseDto updateToDo(@PathVariable Long todoId, @RequestBody ToDoRequestDto toDoRequestDto, @AuthenticationPrincipal UserDetailsImpl userDetails){
        return todoService.updateToDo(todoId,toDoRequestDto);
    }

    // 일정 삭제
    @DeleteMapping("/todo/{todoId}")
    public ResponseEntity<SuccessMessageDto> deleteToDo(@PathVariable Long todoId, @AuthenticationPrincipal UserDetailsImpl userDetails){
        return todoService.deleteToDo(todoId);
    }

}
