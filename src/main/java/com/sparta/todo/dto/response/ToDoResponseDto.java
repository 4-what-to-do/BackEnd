package com.sparta.todo.dto.response;

import com.sparta.todo.entity.Category;
import com.sparta.todo.entity.ToDo;
import lombok.Getter;

@Getter
public class ToDoResponseDto {
    private Long toDoId;
    private Category category;
    private String content;
    private Boolean done;
    private Long postId;


    public ToDoResponseDto(ToDo toDoEntity){
        this.toDoId = toDoEntity.getId();
        this.category = toDoEntity.getCategory();
        this.content = toDoEntity.getContent();
        this.done = toDoEntity.getDone();
        this.postId = toDoEntity.getPost().getId();
    }
}
