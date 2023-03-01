package com.sparta.todo.service;

import com.sparta.todo.dto.requestDto.PostRequestDto;
import com.sparta.todo.dto.responseDto.PostResponseDto;
import com.sparta.todo.entity.LikePost;
import com.sparta.todo.entity.Post;
import com.sparta.todo.entity.User;
import com.sparta.todo.exception.CustomException;
import com.sparta.todo.exception.Error;
import com.sparta.todo.repository.LikePostRepository;
import com.sparta.todo.repository.PostRepository;
import com.sparta.todo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LikeService {
    private final LikePostRepository likePostRepository;
    private final PostRepository postRepository;

    private final UserRepository userRepository;

    // Post(하루 일정) 좋아요 기능
    @Transactional
    public PostResponseDto likePost(Long postId, PostRequestDto postRequestDto, User user) {

        Post post = postRepository.findById(postId).orElseThrow(
                () -> new IllegalArgumentException("일정이 존재하지 않습니다.")
        );

        Optional<LikePost> found = likePostRepository.findByPostAndUser(post,user);

        Integer likeCnt = post.getLikeCount();

        if(found.isEmpty()&&postRequestDto.getLikeStatus().equals(true)){
            likePostRepository.save(new LikePost(user, post));
            post.update(true, likeCnt+1);
        } else if (!found.isEmpty()&&postRequestDto.getLikeStatus().equals(false)) {
            likePostRepository.delete(found.get());
            likePostRepository.flush();
            post.update(false, likeCnt-1);
            return new PostResponseDto(post);
        } else {
            throw new CustomException(Error.WRONG_LIKE_REQUEST);
        }
        return new PostResponseDto(post);
    }
}
