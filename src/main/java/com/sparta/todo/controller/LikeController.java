package com.sparta.todo.controller;

import com.sparta.todo.dto.SuccessMessageDto;
import com.sparta.todo.dto.response.LikeResponseDto;
import com.sparta.todo.entity.User;
import com.sparta.todo.security.UserDetailsImpl;
import com.sparta.todo.service.LikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/posts/communities")
public class LikeController {
//    private final LikeService likeService;
//    //@PutMapping("/like/{postId}")
////    public ResponseEntity<SuccessMessageDto> LikePost(@PathVariable Long postId, @AuthenticationPrincipal UserDetailsImpl userDetails){
////        return likeService.likePost(postId,userDetails.getUser());
////    }
//
//    @PutMapping("/like/{postId")
//    public ResponseEntity<LikePostResponseDto> LikePost(@PathVariable Long postId, @AuthenticationPrincipal UserDetailsImpl userDetails){
//        return likeService.likePost(postId, userDetails.getUser());
//    }

}
