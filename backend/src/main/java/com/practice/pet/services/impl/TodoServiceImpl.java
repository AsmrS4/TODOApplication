package com.practice.pet.services.impl;

import com.practice.pet.dto.CreateTodo;
import com.practice.pet.dto.EditTodo;
import com.practice.pet.dto.Todo;
import com.practice.pet.entities.TodoEntity;
import com.practice.pet.enums.TodoStatus;
import com.practice.pet.repository.TodoRepository;
import com.practice.pet.services.TodoService;
import com.practice.pet.utils.TodoMapper;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class TodoServiceImpl implements TodoService {
    private final TodoRepository todoRepository;
    private final TodoMapper mapper;

    @Autowired
    public TodoServiceImpl(TodoRepository todoRepository, TodoMapper todoMapper) {
        this.todoRepository = todoRepository;
        this.mapper = todoMapper;
    }

    @Override
    public Todo createTodo(CreateTodo createTodo) {
        TodoEntity newTodo = mapper.mapToEntity(createTodo);
        validateDeadline(createTodo.getDeadlineTime());
        return mapper.mapToTodo(save(newTodo));
    }

    @Override
    public Todo editTodo(UUID id, EditTodo editTodo) {
        TodoEntity todoEntity = findEntityById(id);
        validateDeadline(editTodo.getDeadlineTime());
        mapper.mergeChanges(todoEntity, editTodo);
        return mapper.mapToTodo(save(todoEntity));
    }

    @Override
    public List<Todo> retrieveTodos() {
        List<TodoEntity> todos = todoRepository.findAll();
        return todos.stream().map(mapper::mapToTodo).toList();
    }

    @Override
    public Todo changeStatus(UUID id, TodoStatus status) {
        TodoEntity todoEntity = findEntityById(id);
        todoEntity.setStatus(status);
        return mapper.mapToTodo(save(todoEntity));
    }

    @Override
    public void deleteTodo(UUID id) {
        TodoEntity todoEntity = findEntityById(id);
        todoRepository.delete(todoEntity);
    }

    private void validateDeadline(LocalDateTime deadline) {
        if(deadline != null && LocalDateTime.now().isAfter(deadline)) {
            throw new IllegalArgumentException("Дата дедлайна не может быть раньше текущего дня");
        }
    }

    private TodoEntity save(TodoEntity todoEntity) {
        return todoRepository.save(todoEntity);
    }

    private TodoEntity findEntityById(UUID id) {
        return todoRepository.findTodoById(id)
                .orElseThrow(()-> new EntityNotFoundException("Задача не найдена"));
    }

}
