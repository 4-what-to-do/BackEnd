package com.sparta.todo.dto.requestDto;

import com.sparta.todo.dto.responseDto.ToDoResponseDto;
import lombok.Getter;

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
