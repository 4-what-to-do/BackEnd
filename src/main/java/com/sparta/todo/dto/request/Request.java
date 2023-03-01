package com.sparta.todo.dto.request;

import com.sparta.todo.entity.Category;
import lombok.Getter;

@Getter
public class Request {
    private String date;
    private Boolean open;
    private Category category;
    private String content;
    private Boolean done;

    private String user;    // 유저 인증 과정에서 지웁니다.
}
