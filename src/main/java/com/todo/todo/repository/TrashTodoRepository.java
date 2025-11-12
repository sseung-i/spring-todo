package com.todo.todo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.todo.todo.entity.TrashTodo;

@Repository
public interface TrashTodoRepository extends JpaRepository<TrashTodo, Long> {

}
