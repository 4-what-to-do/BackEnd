package com.sparta.todo.service;

import com.sparta.todo.dto.request.PostRequestDto;
import com.sparta.todo.dto.response.PostResponseDto;
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

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final ToDoRepository toDoRepository;


    @Transactional
    public void createPost(PostRequestDto postRequestDto, User user){
        if(postRequestDto.getDate().isEmpty()){
            Post post = postRepository.save(new Post(postRequestDto,user));
        }
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
            if(toDo.getPost().getOpen().equals(true)){
                postResponseDtoList.add(new PostResponseDto(toDo.getPost(),toDoResponseDtoList));
            }
        }
        return postResponseDtoList;
    }
}
