package com.sparta.todo.controller;

import com.sparta.todo.dto.SuccessMessageDto;
import com.sparta.todo.dto.requestDto.PostRequestDto;
import com.sparta.todo.dto.responseDto.PostResponseDto;
import com.sparta.todo.entity.Category;
import com.sparta.todo.security.UserDetailsImpl;
import com.sparta.todo.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/posts")
public class PostController {

    private final PostService postService;
     // 일정 전체 조회
    @GetMapping("/communities")
    public List<PostResponseDto> readAllPost(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        return postService.getAllPosts(userDetails.getUser());
    }

     // 카테고리별 일정 조회
    @GetMapping("/communities/category")
    public List<PostResponseDto> readCategoryAllPost(@RequestParam("category")Category category, @AuthenticationPrincipal UserDetailsImpl userDetails){

        return postService.getCategoriesPosts(category, userDetails.getUser());
    }

    // 공개 비공개
    @PutMapping("/open")
    public ResponseEntity<SuccessMessageDto> publicSwitchToDo(@Valid @RequestBody PostRequestDto postRequestDto, @AuthenticationPrincipal UserDetailsImpl userDetails){
        return postService.openCheck(postRequestDto);
    }
}
