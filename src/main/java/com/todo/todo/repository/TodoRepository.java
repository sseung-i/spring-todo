package com.todo.todo.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.todo.todo.entity.Todo;

@Repository
public interface TodoRepository extends JpaRepository<Todo, Long> {
    @Query("""
              SELECT t
              FROM Todo t
              WHERE
                  (t.endDate   IS NULL AND t.startDate IS NOT NULL AND t.startDate <= :targetDate)
               OR (t.startDate IS NULL AND t.endDate   IS NOT NULL AND t.endDate   >= :targetDate)
               OR (t.startDate IS NOT NULL AND t.endDate IS NOT NULL
                   AND t.startDate <= :targetDate AND t.endDate >= :targetDate)
            """)
    List<Todo> findTodosByDateBetween(@Param("targetDate") LocalDate targetDate);
}
