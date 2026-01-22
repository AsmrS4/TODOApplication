package com.practice.pet.services;

import com.practice.pet.dto.CreateTodo;
import com.practice.pet.dto.EditTodo;
import com.practice.pet.dto.Todo;
import com.practice.pet.enums.TodoStatus;

import java.util.List;
import java.util.UUID;

public interface TodoService {
    Todo createTodo(CreateTodo createTodo);
    Todo editTodo(UUID id, EditTodo editTodo);
    List<Todo> retrieveTodos();
    Todo changeStatus(UUID id, TodoStatus status);
    void deleteTodo(UUID id);
}
