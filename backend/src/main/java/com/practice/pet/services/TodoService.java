package com.practice.pet.services;

import com.practice.pet.dto.CreateTodo;
import com.practice.pet.dto.EditTodo;
import com.practice.pet.dto.Todo;
import com.practice.pet.enums.TodoStatus;

import java.util.List;

public interface TodoService {
    Todo createTodo(CreateTodo createTodo);
    Todo editTodo(Long id, EditTodo editTodo);
    List<Todo> retrieveTodos();
    Todo changeStatus(Long id, TodoStatus status);
    void deleteTodo(Long id);
}
