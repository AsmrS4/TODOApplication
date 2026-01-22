package com.practice.pet.controller;

import com.practice.pet.dto.CreateTodo;
import com.practice.pet.dto.EditTodo;
import com.practice.pet.dto.Todo;
import com.practice.pet.enums.TodoStatus;
import com.practice.pet.services.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/todo")
public class TodoController {
    private final TodoService todoService;

    @Autowired
    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @PostMapping
    public ResponseEntity<Todo> createTodo(@RequestBody CreateTodo createTodo) {
        return ResponseEntity.ok(todoService.createTodo(createTodo));
    }

    @GetMapping
    public ResponseEntity<List<Todo>> retrieveTodos() {
        return ResponseEntity.ok(todoService.retrieveTodos());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Todo> editTodo(@PathVariable UUID id, @RequestBody EditTodo editTodo) {
        return ResponseEntity.ok(todoService.editTodo(id, editTodo));
    }

    @DeleteMapping("/{id}")
    public void deleteTodo(@PathVariable UUID id) {
        todoService.deleteTodo(id);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> changeStatus(@PathVariable UUID id, TodoStatus status) {
        return ResponseEntity.ok(todoService.changeStatus(id, status));
    }
}
