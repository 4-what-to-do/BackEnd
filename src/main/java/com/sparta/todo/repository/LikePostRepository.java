package com.sparta.todo.repository;

import com.sparta.todo.entity.LikePost;
import com.sparta.todo.entity.Post;
import com.sparta.todo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LikePostRepository extends JpaRepository<LikePost, Long> {

    Optional<LikePost> findByPostAndUser(Post post, User user);

    Long countAllByPost(Post post);

    //Long countAllByPost(Post post);
}
