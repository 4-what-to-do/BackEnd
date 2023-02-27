package com.sparta.todo.controller;

import com.sparta.todo.dto.response.PostResponseDto;
import com.sparta.todo.entity.Category;
import com.sparta.todo.entity.Post;
import com.sparta.todo.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/posts")
public class PostController {

    private final PostService postService;
     /*일정 전체 조회 */
    @GetMapping("/communities")
    public List<PostResponseDto> readAllPost() {
        return postService.getAllPosts();
    }

     /*카테고리별 일정 조회 */
    @GetMapping("/communities/likes")
    public List<PostResponseDto> readCategoryAllPost(@RequestParam("category")Category category){

        return postService.getCategoriesPosts(category);
    }
}
