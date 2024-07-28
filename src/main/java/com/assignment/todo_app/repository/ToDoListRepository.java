package com.assignment.todo_app.repository;

import com.assignment.todo_app.model.ToDo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ToDoListRepository extends JpaRepository<ToDo, Long> {
    List<ToDo> findAllByOrderByCreatedAtDesc();
}
