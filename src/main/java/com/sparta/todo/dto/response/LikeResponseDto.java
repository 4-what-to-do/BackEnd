package com.sparta.todo.dto.response;


import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class LikeResponseDto {

    private Long count;

    public LikeResponseDto(Long count){
        this.count = count;
    }

}
