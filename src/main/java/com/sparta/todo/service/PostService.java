package com.sparta.todo.service;

import com.sparta.todo.dto.SuccessMessageDto;
import com.sparta.todo.dto.request.PostRequestDto;
import com.sparta.todo.dto.response.PostResponseDto;
import com.sparta.todo.dto.response.ToDoResponseDto;
import com.sparta.todo.entity.Category;
import com.sparta.todo.entity.Post;
import com.sparta.todo.entity.ToDo;
import com.sparta.todo.entity.User;
import com.sparta.todo.repository.PostRepository;
import com.sparta.todo.repository.ToDoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final ToDoRepository toDoRepository;



    // 포스트 추가
    @Transactional
    public void createPost(String date, Boolean open, User user){
        Optional<Post> post = postRepository.findByDate(date);
        if(post.isEmpty()){
            new PostResponseDto(postRepository.save(new Post(date,open,user)));
        }
    }

    // 포스트 공개 비공개
    @Transactional
    public ResponseEntity<SuccessMessageDto> openCheck(PostRequestDto postRequestDto){
        Post post = postRepository.findByDate(postRequestDto.getDate()).orElseThrow(
                () -> new IllegalArgumentException("게시글이 없습니다.")
        );

        post.update(postRequestDto.getOpen());

        return ResponseEntity.ok()
                .body(SuccessMessageDto.builder()
                        .statusCode(HttpStatus.OK.value())
                        .msg("상태 변경")
                        .build());
    }


    // 커뮤니티 일정 전체 조회
    // 커뮤니티 일정 전체 조회
    @Transactional(readOnly = true)
    public List<PostResponseDto> getAllPosts(){

        List<Post> postsList = postRepository.findAll();
        List<PostResponseDto> postResponseDtoList = new ArrayList<>();

        for(Post post : postsList){

            List<ToDo> toDoList = toDoRepository.findAllByPostOrderById(post);
            List<ToDoResponseDto> toDoResponseDtoList = new ArrayList<>();
            for(ToDo toDo : toDoList){
                toDoResponseDtoList.add(new ToDoResponseDto(toDo));
            }
            if(post.getOpen().equals(false)){
                continue;
            }
            postResponseDtoList.add(new PostResponseDto(post,toDoResponseDtoList));
        }

        Collections.reverse(postResponseDtoList);
        return postResponseDtoList;
    }

    // 카테고리별 일정 조회
    @Transactional
    public List<PostResponseDto> getCategoriesPosts(Category category){
        List<ToDo> toDoList = toDoRepository.findAllByCategory(category);
        List<ToDoResponseDto> toDoResponseDtoList = new ArrayList<>();
        for(ToDo toDo : toDoList){
            toDoResponseDtoList.add(new ToDoResponseDto(toDo));
        }

        List<PostResponseDto> postResponseDtoList = new ArrayList<>();
        for (ToDo toDo : toDoList) {
            if(toDo.getPost().getOpen().equals(false)){
                continue;
            }
            if(toDo.getPost().getOpen().equals(true) && postResponseDtoList.contains(toDo)){
                postResponseDtoList.add(new PostResponseDto(toDo.getPost(),toDoResponseDtoList));
            }
        }
        return postResponseDtoList;
    }
}
