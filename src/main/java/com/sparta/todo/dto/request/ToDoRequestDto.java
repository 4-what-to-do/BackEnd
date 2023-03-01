package com.sparta.todo.dto.request;

import com.sparta.todo.entity.Category;
import lombok.Getter;


@Getter
public class ToDoRequestDto {
    private Long toDoId;
    private Category category;
    private String content;
    private Boolean done;
    private Long postId;
}
