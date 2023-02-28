package com.sparta.todo.entity;

import com.sparta.todo.dto.request.ToDoRequestDto;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ToDo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TODO_ID")
    private Long id;

    @Column(nullable = false, columnDefinition = "text", length = 100)
    private String content;

    @Column(nullable = false)
    private Boolean done;

    @Column(nullable = false)
    private Category category;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "POST_ID", nullable = false)
    private Post post;

    //생성 메서드
    @Builder
    public ToDo (String content, Boolean done, Category category, Post post){
        this.content = content;
        this.done = done;
        this.category = category;
        this.post = post;
    }

    public void updateDone(Boolean doneC){
        this.done = doneC;
    }

    public void update(ToDoRequestDto toDoRequestDto){
        this.content = toDoRequestDto.getContent();
        this.category = toDoRequestDto.getCategory();
    }
}
