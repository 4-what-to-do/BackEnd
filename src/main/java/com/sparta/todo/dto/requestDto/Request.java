package com.sparta.todo.dto.requestDto;

import com.sparta.todo.entity.Category;
import lombok.Getter;

@Getter
public class Request {
    private String date;
    private Category category;
    private String content;
    private Boolean done;

}
