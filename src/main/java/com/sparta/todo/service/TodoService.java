package com.sparta.todo.service;

import com.sparta.todo.dto.request.Request;
import com.sparta.todo.dto.request.ToDoRequestDto;
import com.sparta.todo.dto.response.ToDoResponseDto;
import com.sparta.todo.entity.Category;
import com.sparta.todo.entity.Post;
import com.sparta.todo.entity.ToDo;
import com.sparta.todo.entity.User;
import com.sparta.todo.repository.PostRepository;
import com.sparta.todo.repository.ToDoRepository;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class TodoService {
    private final ToDoRepository toDoRepository;
    private final PostRepository postRepository;
    private final PostService postService;

    /*@Transactional
    public ToDoResponseDto createToDo(String date, String content, Boolean done, Category category, Boolean open){
        // 하루일정 생성
        postService.createPost(date, open);

        Optional<Post> post = postRepository.findByDate(date);

        return new ToDoResponseDto(toDoRepository.save(new ToDo(content,done,category,post.get())));
    }*/

    @Transactional
    public ToDoResponseDto createToDo(Request request){
        // 하루일정 생성
        postService.createPost(request.getDate(), request.getOpen());

        Optional<Post> post = postRepository.findByDate(request.getDate());

        return new ToDoResponseDto(toDoRepository.save(new ToDo(request.getContent(),request.getDone(),request.getCategory(),post.get())));
    }

    // 일정 완료 체크
    @Transactional
    public ToDoResponseDto doneCheck(Long toDoId){
        ToDo toDo = toDoRepository.findById(toDoId).orElseThrow(
                () -> new IllegalArgumentException("일정이 존재하지 않습니다.")
        );

        toDo.updateDone(true);

        return new ToDoResponseDto(toDo);
    }

    // 일정 수정


    // 일정 삭제

}
