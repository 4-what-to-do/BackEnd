package com.sparta.todo.service;

import com.sparta.todo.dto.SuccessMessageDto;
import com.sparta.todo.dto.response.PostResponseDto;
import com.sparta.todo.entity.LikePost;
import com.sparta.todo.entity.Post;
import com.sparta.todo.entity.User;
import com.sparta.todo.repository.LikePostRepository;
import com.sparta.todo.repository.PostRepository;
import com.sparta.todo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LikeService {
    private final LikePostRepository likePostRepository;
    private final PostRepository postRepository;


    /* Post(하루 일정) 좋아요 기능 */
    @Transactional
    public void likePost(Long postId) {
        Optional<Post> post = postRepository.findById(postId);
        // 이전에 누른적 없을 때
        Optional<LikePost> found = likePostRepository.findByPostAndUser(post.get(),post.get().getUser());
        if(found.isEmpty()){
            LikePost likePost = likePostRepository.save(new LikePost(post.get().getUser(), post.get()));
        } else {    // 이전에 누른 적 있을 때
            likePostRepository.delete(found.get());
            likePostRepository.flush();
        }
    }

    /* Post(하루 일정) 좋아요 개수 카운팅 */
    /*@Transactional(readOnly = true)
    public Long countLikes(Long postId, User user){
        // like 테이블에서 star_id 조회
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new IllegalArgumentException("일정이 존재하지 않습니다.")
        );
        Long found = likePostRepository.countAllByPost(post);

        return found;
    }*/
}
