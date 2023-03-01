package com.sparta.todo.service;

import com.sparta.todo.dto.SuccessMessageDto;
import com.sparta.todo.dto.requestDto.PostRequestDto;
import com.sparta.todo.dto.responseDto.PostResponseDto;
import com.sparta.todo.dto.responseDto.ToDoResponseDto;
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
        List<Post> postsList = postRepository.findAll();
        List<PostResponseDto> postResponseDtoList = new ArrayList<>();

        for(Post post : postsList){

            // 포스트가 비공개라면 이 포스트는 추가하지 않음
            if(post.getOpen().equals(false)){
                continue;
            }

            List<ToDo> toDoList = toDoRepository.findAllByPostOrderById(post);
            List<ToDoResponseDto> toDoResponseDtoList = new ArrayList<>();
            // 카테고리가 들어간 코드인지 판별
            for(ToDo toDo : toDoList){
                if(!toDo.getCategory().equals(category)){
                    continue;
                }
                toDoResponseDtoList.add(new ToDoResponseDto(toDo));
            }
            // 카테고리가 들어간 코드라면 같은 post_id를 가진 일정을 전부 저장해서 반환
            if(!toDoResponseDtoList.isEmpty()){
                toDoResponseDtoList.clear();
                for(ToDo toDo : toDoList){
                    toDoResponseDtoList.add(new ToDoResponseDto(toDo));
                }
                postResponseDtoList.add(new PostResponseDto(post,toDoResponseDtoList));
            }
        }
        // 포스트 List reverse. 최신 포스트가 가장 위로 올라옴
        Collections.reverse(postResponseDtoList);
        return postResponseDtoList;
    }
}
