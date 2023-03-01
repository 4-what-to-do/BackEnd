package com.sparta.todo.dto.request;

import com.sparta.todo.dto.response.ToDoResponseDto;
import com.sparta.todo.entity.Post;
import lombok.Getter;

import javax.persistence.criteria.ListJoin;
import java.util.ArrayList;
import java.util.List;

@Getter
public class PostRequestDto {
    private Long postId;
    private String nickname;
    private String date;
    private Boolean open;
    private Boolean likeStatus;
    private Integer likeCount;
    private List<ToDoResponseDto> toDoResponseDtoList = new ArrayList<>();

}
