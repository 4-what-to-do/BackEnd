package com.sparta.todo.repository;

import com.sparta.todo.entity.LikePost;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikePostRepository extends JpaRepository<LikePost, Long> {
}
