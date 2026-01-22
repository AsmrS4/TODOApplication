package com.practice.pet.services.impl;

import com.practice.pet.dto.CreateTodo;
import com.practice.pet.dto.EditTodo;
import com.practice.pet.dto.Todo;
import com.practice.pet.enums.TodoStatus;
import com.practice.pet.services.TodoService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoServiceImpl implements TodoService {
    @Override
    public Todo createTodo(CreateTodo createTodo) {
        return null;
    }

    @Override
    public Todo editTodo(Long id, EditTodo editTodo) {
        return null;
    }

    @Override
    public List<Todo> retrieveTodos() {
        return List.of();
    }

    @Override
    public Todo changeStatus(Long id, TodoStatus status) {
        return null;
    }

    @Override
    public void deleteTodo(Long id) {

    }
}
