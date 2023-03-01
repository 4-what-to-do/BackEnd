package com.sparta.todo.service;


import com.sparta.todo.dto.SuccessMessageDto;
import com.sparta.todo.dto.request.Request;
import com.sparta.todo.dto.request.ToDoRequestDto;
import com.sparta.todo.dto.response.ToDoResponseDto;
import com.sparta.todo.entity.Category;
import com.sparta.todo.entity.Post;
import com.sparta.todo.entity.ToDo;
import com.sparta.todo.entity.User;
import com.sparta.todo.exception.CustomException;
import com.sparta.todo.exception.Error;
import com.sparta.todo.repository.PostRepository;
import com.sparta.todo.repository.ToDoRepository;
import com.sparta.todo.repository.UserRepository;
import com.sparta.todo.security.UserDetailsImpl;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import static com.sparta.todo.exception.Error.NOT_EXIST_USER;
import static com.sparta.todo.exception.Error.NOT_FOUND_DATE;

@Service
@RequiredArgsConstructor

public class TodoService {
    private final ToDoRepository toDoRepository;
    private final PostRepository postRepository;
    private final PostService postService;

    private final UserRepository userRepository;


    @Transactional
    ////public ToDoResponseDto createToDo(Request request, UserDetailsImpl userDetails){
    public ResponseEntity createToDo(Request request, Long userId){
        User found = userRepository.findById(userId).orElseThrow(
                () -> new CustomException(NOT_EXIST_USER)
        );
        System.out.println("TodoService.createToDo2");
        Optional<Post> post = postRepository.findByDateAndUser(request.getDate(), found);
        System.out.println("TodoService.createToDo3");
        if(post.isEmpty()){
            System.out.println("TodoService.createToDo1");;
            Post newPost = new Post(request.getDate(), true, found);
            postRepository.save(newPost);
            ToDo toDo = ToDo.builder().content(request.getContent()).done(request.getDone()).category(request.getCategory()).post(newPost).build();
            toDoRepository.save(toDo);
        }
        else {
            System.out.println("TodoService.createToDo2");
            ToDo toDo = ToDo.builder().content(request.getContent()).done(request.getDone()).category(request.getCategory()).post(post.get()).build();
            toDoRepository.save(toDo);
        }
        //?//?
        return ResponseEntity.ok(new SuccessMessageDto("성공", HttpStatus.OK.value()));
    }

    // 일정 완료 체크
    @Transactional
    public ToDoResponseDto doneCheck(Long toDoId){
        ToDo toDo = toDoRepository.findById(toDoId).orElseThrow(
                () -> new IllegalArgumentException("일정이 존재하지 않습니다.")
        );
        toDo.updateDone(toDo.getDone());


        return new ToDoResponseDto(toDo);
    }

    // 일정 전체 조회
    @Transactional
    public List<ToDoResponseDto> readToDo(String date, User user){

        Optional<Post> post = postRepository.findByDateAndUser(date, user);
        if(post.isEmpty())
            throw new CustomException(NOT_FOUND_DATE);

        List<ToDo> toDoList = toDoRepository.findAllByPostOrderById(post.get());

        List<ToDoResponseDto> toDoResponseDtoList = new ArrayList<>();

        for(ToDo toDo : toDoList){
            toDoResponseDtoList.add(new ToDoResponseDto(toDo));
        }
        return toDoResponseDtoList;
    }


    // 일정 수정
    @Transactional
    public ToDoResponseDto updateToDo(Long toDoId, ToDoRequestDto toDoRequestDto){
        ToDo toDo = toDoRepository.findById(toDoId).orElseThrow(
                () -> new IllegalArgumentException("일정이 존재하지 않습니다.")
        );

        toDo.update(toDoRequestDto);
        return new ToDoResponseDto(toDo);
    }

    // 일정 삭제
    @Transactional
    public ResponseEntity<SuccessMessageDto> deleteToDo(Long toDoId){
        ToDo toDo = toDoRepository.findById(toDoId).orElseThrow(
                () -> new IllegalArgumentException("일정이 존재하지 않습니다.")
        );

        toDoRepository.delete(toDo);
        return ResponseEntity.ok()
                .body(SuccessMessageDto.builder()
                        .statusCode(HttpStatus.OK.value())
                        .msg("삭제 성공")
                        .build());
    }

}
