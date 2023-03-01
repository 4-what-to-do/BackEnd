package com.sparta.todo.repository;

import com.sparta.todo.entity.Post;
import com.sparta.todo.entity.ToDo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ToDoRepository extends JpaRepository<ToDo, Long> {

    List<ToDo> findAllByPostOrderById(Post post);

}
