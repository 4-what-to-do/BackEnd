package com.sparta.todo.repository;

import com.sparta.todo.entity.Category;
import com.sparta.todo.entity.Post;
import com.sparta.todo.entity.ToDo;
import com.sparta.todo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Long> {

    Optional<Post> findByDate(String date);

    Optional<Post> findByUser(User user);

    Optional<Post> findByDateAndUser(String date, User user);

}
