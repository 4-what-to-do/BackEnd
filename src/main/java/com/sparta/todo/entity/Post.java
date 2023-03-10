package com.sparta.todo.entity;

import com.sparta.todo.dto.requestDto.PostRequestDto;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "POST_ID")
    private Long id;

    @Column(nullable = false)
    private String date;

    @Column(nullable = false)
    private Boolean open;

    @Column(nullable = false)
    private Boolean likeStatus = false;

    @Column(name = "LIKE_COUNT")
    private Integer likeCount = 0;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID")
    private User user;

    @OneToMany(mappedBy = "post", cascade = CascadeType.REMOVE)
    private List<ToDo> toDoList = new ArrayList<>();

    //생성 메서드
    @Builder
    public Post(PostRequestDto postRequestDto, User user){
        this.date = postRequestDto.getDate();
        this.user = user;
        this.open = postRequestDto.getOpen();
        this.likeCount = 0;
        this.likeStatus = false;
    }

    public Post(String date, Boolean open, User user){
        this.date = date;
        this.open = open;
        this.user = user;
    }

    public void update(Boolean open){
        this.open = open;
    }

    public void update(Boolean likeStatus, Integer likeCount){
        this.likeStatus = likeStatus;
        this.likeCount = likeCount;
    }
}