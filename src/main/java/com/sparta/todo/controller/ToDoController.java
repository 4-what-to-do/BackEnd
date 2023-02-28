package com.sparta.todo.controller;

import com.sparta.todo.dto.request.Request;
import com.sparta.todo.dto.response.PostResponseDto;
import com.sparta.todo.dto.response.ToDoResponseDto;
import com.sparta.todo.entity.Category;
import com.sparta.todo.entity.User;
import com.sparta.todo.service.PostService;
import com.sparta.todo.service.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/posts")
public class ToDoController {

    private final TodoService todoService;

    // 포스트 작성
    @PostMapping("/todo")
    public ToDoResponseDto createToDo(@RequestBody Request request) {
        return todoService.createToDo(request);
    }

    @PutMapping("/todo/{todoId}")
    public ToDoResponseDto doneCheck(@PathVariable Long todoId){
        return todoService.doneCheck(todoId);
    }
}
