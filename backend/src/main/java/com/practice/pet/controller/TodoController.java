package com.practice.pet.controller;

import com.practice.pet.dto.Todo;
import com.practice.pet.enums.TodoStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/todo")
public class TodoController {
    @PostMapping
    public ResponseEntity<Todo> createTodo() {
        return null;
    }
    @GetMapping
    public ResponseEntity<List<Todo>> retrieveTodos() {
        return null;
    }
    @PutMapping("/{id}")
    public ResponseEntity<Todo> editTodo(@PathVariable UUID id) {
        return null;
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTodo(@PathVariable UUID id) {
        return null;
    }
    @PatchMapping("/{id}")
    public ResponseEntity<?> changeStatus(@PathVariable UUID id, TodoStatus status) {
        return null;
    }
}
