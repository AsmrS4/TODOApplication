package com.practice.pet.services;

import com.practice.pet.dto.CreateTodo;
import com.practice.pet.dto.EditTodo;
import com.practice.pet.dto.FilterParams;
import com.practice.pet.entities.TodoEntity;
import com.practice.pet.enums.TodoStatus;

import java.util.List;
import java.util.UUID;

public interface TodoService {
    TodoEntity createTodo(CreateTodo createTodo);
    TodoEntity editTodo(UUID id, EditTodo editTodo);
    List<TodoEntity> retrieveTodos(FilterParams filterParams);
    TodoEntity changeStatus(UUID id, TodoStatus status);
    void deleteTodo(UUID id);
}
