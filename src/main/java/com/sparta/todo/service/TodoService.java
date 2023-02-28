package com.sparta.todo.service;

import com.sparta.todo.dto.request.ToDoRequestDto;
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

@Service
@RequiredArgsConstructor
public class TodoService {

    private final PostRepository postRepository;
    private final ToDoRepository toDoRepository;

    private final PostService postService;

    /*@Transactional
    public void createToDo(ToDoRequestDto toDoRequestDto, User user){
        postService.createPost(toDoRequestDto,user);
        ToDo toDo = toDoRepository.save(toDoRequestDto,user);
    }*/

}
