package com.sparta.todo.dto.responseDto;

import com.sparta.todo.entity.Post;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class PostResponseDto {
    private Long postId;
    private String nickname;
    private String date;
    private Boolean open;
    private Boolean likeStatus;
    private Integer likeCount;

    private List<ToDoResponseDto> toDoResponseDtoList = new ArrayList<>();


    public PostResponseDto(Post postEntity){
        this.postId = postEntity.getId();
        this.nickname = postEntity.getUser().getNickname();
        this.date = postEntity.getDate();
        this.open = postEntity.getOpen();
        this.likeStatus = postEntity.getLikeStatus();
        this.likeCount = postEntity.getLikeCount();
    }

    public PostResponseDto(Post postEntity, Boolean boolLike, List<ToDoResponseDto> toDoResponseDtoList){
        this.postId = postEntity.getId();
        this.nickname = postEntity.getUser().getNickname();
        this.date = postEntity.getDate();
        this.open = postEntity.getOpen();
        this.likeStatus = boolLike;
        this.likeCount = postEntity.getLikeCount();
        this.toDoResponseDtoList = toDoResponseDtoList;
    }
}
