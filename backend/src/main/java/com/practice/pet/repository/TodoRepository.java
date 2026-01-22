package com.practice.pet.repository;

import com.practice.pet.entities.GroupEntity;
import com.practice.pet.entities.TodoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface TodoRepository extends JpaRepository<TodoEntity, UUID> {
    Optional<TodoEntity> findTodoById(UUID id);
    @Query("SELECT * FROM TodoEntity te WHERE te.status <> 3")
    List<TodoEntity> findActiveTodos();
    @Query("SELECT * FROM TodoEntity te WHERE te.status <> 3 AND te.group = group")
    List<TodoEntity> findActiveTodosByGroup(@Param("group")GroupEntity group);
}
