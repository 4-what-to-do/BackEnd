package com.sparta.todo.controller;

import com.sparta.todo.dto.SuccessMessageDto;
import com.sparta.todo.dto.response.PostResponseDto;
import com.sparta.todo.entity.User;
import com.sparta.todo.service.LikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/posts/communities")
public class LikeController {
    private final LikeService likeService;
    @PutMapping("/like/{postId}")
    public void LikePost(@PathVariable Long postId){
        likeService.likePost(postId);
    }
}
