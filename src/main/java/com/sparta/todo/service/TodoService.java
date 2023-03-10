package com.sparta.todo.service;


import com.sparta.todo.dto.SuccessMessageDto;
import com.sparta.todo.dto.requestDto.Request;
import com.sparta.todo.dto.requestDto.ToDoRequestDto;
import com.sparta.todo.dto.responseDto.ToDoOpenResposeDto;
import com.sparta.todo.dto.responseDto.ToDoResponseDto;
import com.sparta.todo.entity.Post;
import com.sparta.todo.entity.ToDo;
import com.sparta.todo.entity.User;
import com.sparta.todo.exception.CustomException;
import com.sparta.todo.repository.PostRepository;
import com.sparta.todo.repository.ToDoRepository;
import com.sparta.todo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.sparta.todo.exception.Error.NOT_EXIST_USER;
import static com.sparta.todo.exception.Error.NOT_FOUND_DATE;

@Service
@RequiredArgsConstructor

public class TodoService {
    private final ToDoRepository toDoRepository;
    private final PostRepository postRepository;
    private final UserRepository userRepository;


    @Transactional
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
        return ResponseEntity.ok(new SuccessMessageDto("??????", HttpStatus.OK.value()));
    }

    // ?????? ?????? ??????
    @Transactional
    public ToDoResponseDto doneCheck(Long toDoId, ToDoRequestDto toDoRequestDto){
        ToDo toDo = toDoRepository.findById(toDoId).orElseThrow(
                () -> new IllegalArgumentException("????????? ???????????? ????????????.")
        );


       toDo.updateDone(toDoRequestDto.getDone());

        return new ToDoResponseDto(toDo);
    }

    // ?????? ?????? ??????
    @Transactional
    public List<ToDoOpenResposeDto> readToDo(String date, User user){

        Optional<Post> post = postRepository.findByDateAndUser(date, user);
        if(post.isEmpty())
            throw new CustomException(NOT_FOUND_DATE);

        List<ToDo> toDoList = toDoRepository.findAllByPostOrderById(post.get());

        List<ToDoOpenResposeDto> toDoOpenResponseDtoList = new ArrayList<>();

        for(ToDo toDo : toDoList){
            toDoOpenResponseDtoList.add(new ToDoOpenResposeDto(toDo));
        }
        return toDoOpenResponseDtoList;
    }


    // ?????? ??????
    @Transactional
    public ToDoResponseDto updateToDo(Long toDoId, ToDoRequestDto toDoRequestDto){
        ToDo toDo = toDoRepository.findById(toDoId).orElseThrow(
                () -> new IllegalArgumentException("????????? ???????????? ????????????.")
        );

        toDo.update(toDoRequestDto);
        return new ToDoResponseDto(toDo);
    }

    // ?????? ??????
    @Transactional
    public ResponseEntity<SuccessMessageDto> deleteToDo(Long toDoId){
        ToDo toDo = toDoRepository.findById(toDoId).orElseThrow(
                () -> new IllegalArgumentException("????????? ???????????? ????????????.")
        );

        toDoRepository.delete(toDo);
        return ResponseEntity.ok()
                .body(SuccessMessageDto.builder()
                        .statusCode(HttpStatus.OK.value())
                        .msg("?????? ??????")
                        .build());
    }

}
