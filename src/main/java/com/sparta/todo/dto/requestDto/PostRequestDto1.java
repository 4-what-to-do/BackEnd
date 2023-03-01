package com.sparta.todo.dto.requestDto;

import com.sparta.todo.dto.responseDto.ToDoResponseDto;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class PostRequestDto1 {
    private Long postId;
    private String nickname;
    private Boolean open;
    private Boolean likeStatus;
    private Integer likeCount;
    private List<ToDoResponseDto> toDoResponseDtoList = new ArrayList<>();

}
