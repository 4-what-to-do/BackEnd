package com.sparta.todo.dto.response;

import com.sparta.todo.entity.LikePost;
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
    private Integer likeCount;
    private List<ToDoResponseDto> toDoResponseDtoList = new ArrayList<>();


    public PostResponseDto(Post postEntity){
        this.postId = postEntity.getId();
        this.nickname = postEntity.getUser().getNickname();
        this.date = postEntity.getDate();
        this.open = postEntity.getOpen();
    }

    public PostResponseDto(Post postEntity, List<ToDoResponseDto> toDoResponseDtoList){
        this.postId = postEntity.getId();
        this.nickname = postEntity.getUser().getNickname();
        this.date = postEntity.getDate();
        this.open = postEntity.getOpen();
        this.likeCount = postEntity.getLikePostList() != null ? postEntity.getLikePostList().size() : 0;
        this.toDoResponseDtoList = toDoResponseDtoList;
    }

}
