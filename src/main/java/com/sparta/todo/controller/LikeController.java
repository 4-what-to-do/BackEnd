package com.sparta.todo.controller;

import com.sparta.todo.dto.requestDto.PostRequestDto;
import com.sparta.todo.dto.responseDto.PostResponseDto;
import com.sparta.todo.security.UserDetailsImpl;
import com.sparta.todo.service.LikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/posts/communities")
public class LikeController {
    private final LikeService likeService;
    @PutMapping("/like/{postId}")
    public PostResponseDto LikePost(@PathVariable Long postId, @RequestBody PostRequestDto postRequestDto, @AuthenticationPrincipal UserDetailsImpl userDetails){
        return likeService.likePost(postId,postRequestDto, userDetails.getUser());
    }


}
