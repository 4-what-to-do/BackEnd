package com.sparta.todo.repository;

import com.sparta.todo.entity.Post;
import com.sparta.todo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Long> {

    Optional<Post> findByDate(String date);

    Optional<Post> findByDateAndUser(String date, User user);

}
