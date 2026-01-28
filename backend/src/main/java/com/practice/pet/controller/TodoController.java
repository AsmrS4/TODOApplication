package com.practice.pet.controller;

import com.practice.pet.dto.CreateTodo;
import com.practice.pet.dto.EditTodo;
import com.practice.pet.dto.FilterParams;
import com.practice.pet.dto.Todo;
import com.practice.pet.enums.TodoStatus;
import com.practice.pet.services.TodoService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Nullable;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/todo")
@Tag(name = "Todo Controller", description = "CRUD эндпоинты для сущности TodoEntity")
public class TodoController {
    private final TodoService todoService;

    @Autowired
    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @PostMapping
    public ResponseEntity<Todo> createTodo(@RequestBody @Valid CreateTodo createTodo) {
        return ResponseEntity.ok(todoService.createTodo(createTodo));
    }

    @GetMapping
    public ResponseEntity<List<Todo>> retrieveTodos(
            @RequestParam(required = false) LocalDateTime dateFrom,
            @RequestParam(required = false) LocalDateTime dateTo,
            @RequestParam(required = false) TodoStatus status
            ) {
        FilterParams filterParams = new FilterParams(dateFrom, dateTo, status);
        return ResponseEntity.ok(todoService.retrieveTodos(filterParams));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Todo> editTodo(@PathVariable UUID id, @Valid @RequestBody EditTodo editTodo) {
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
