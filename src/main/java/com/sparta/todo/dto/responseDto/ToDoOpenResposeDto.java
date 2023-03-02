package com.sparta.todo.dto.responseDto;

import com.sparta.todo.entity.Category;
import com.sparta.todo.entity.ToDo;
import lombok.Getter;

@Getter
public class ToDoOpenResposeDto {

    private Long id;
    private Category category;
    private String content;
    private Boolean done;
    private Long postId;
    private Boolean open;

    public ToDoOpenResposeDto(ToDo toDoEntity){
        this.id = toDoEntity.getId();
        this.category = toDoEntity.getCategory();
        this.content = toDoEntity.getContent();
        this.done = toDoEntity.getDone();
        this.postId = toDoEntity.getPost().getId();
        this.open = toDoEntity.getPost().getOpen();
    }
}
