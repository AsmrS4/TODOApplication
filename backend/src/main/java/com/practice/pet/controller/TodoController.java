package com.practice.pet.controller;

import com.practice.pet.dto.*;
import com.practice.pet.entities.TodoEntity;
import com.practice.pet.enums.TodoStatus;
import com.practice.pet.services.TodoService;
import com.practice.pet.utils.TodoMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/todo")
@Tag(name = "Todo Controller", description = "CRUD эндпоинты для сущности TodoEntity")
@RequiredArgsConstructor
public class TodoController {
    private final TodoService todoService;
    private final TodoMapper mapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(
            summary = "Создание задачи",
            description = "Вы можете создать новую задачу",
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "Успешная обработка",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(
                                            implementation = Todo.class
                                    )
                            )
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Ошибка клиента"
                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Ошибка сервера"
                    )
            }
    )
    public Todo createTodo(@RequestBody @Valid CreateTodo createTodo) {
        TodoEntity createdTodo = todoService.createTodo(createTodo);
        return mapper.mapToTodo(createdTodo);
    }

    @GetMapping
    @Operation(
            summary = "Получение списка задач",
            description = "Вы можете получить список групп",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Успешная обработка",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(
                                            type = "array",
                                            implementation = Todo.class
                                    )
                            )
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Ошибка клиента"
                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Ошибка сервера"
                    )
            }
    )
    public ResponseEntity<List<Todo>> retrieveTodos(
            @RequestParam(required = false) LocalDateTime dateFrom,
            @RequestParam(required = false) LocalDateTime dateTo,
            @RequestParam(required = false) TodoStatus status
            ) {
        FilterParams filterParams = new FilterParams(dateFrom, dateTo, status);
        List<TodoEntity> todos = todoService.retrieveTodos(filterParams);
        return ResponseEntity.ok(todos.stream().map(mapper::mapToTodo).toList());
    }

    @PutMapping("/{id}")
    @Operation(
            summary = "Редактирование задачи",
            description = "Вы можете редактировать задачу",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Успешная обработка",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(
                                            implementation = Todo.class
                                    )
                            )
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Ошибка клиента"
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Задача не найдена"
                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Ошибка сервера"
                    )
            }
    )
    public ResponseEntity<Todo> editTodo(@PathVariable UUID id, @Valid @RequestBody EditTodo editTodo) {
        TodoEntity editedTodo = todoService.editTodo(id, editTodo);
        return ResponseEntity.ok(mapper.mapToTodo(editedTodo));
    }

    @DeleteMapping("/{id}")
    @Operation(
            summary = "Удаление задачи",
            description = "Вы можете удалить задачу",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Успешная обработка"
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Ошибка клиента"
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Задача не найдена"
                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Ошибка сервера"
                    )
            }
    )
    public void deleteTodo(@PathVariable UUID id) {
        todoService.deleteTodo(id);
    }

    @PatchMapping("/{id}")
    @Operation(
            summary = "Изменение статуса задачи",
            description = "Вы можете изменить статус задачи",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Успешная обработка",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(
                                            implementation = Todo.class
                                    )
                            )
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Ошибка клиента"
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Задача не найдена"
                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Ошибка сервера"
                    )
            }
    )
    public ResponseEntity<?> changeStatus(@PathVariable UUID id, TodoStatus status) {
        TodoEntity editedTodo = todoService.changeStatus(id, status);
        return ResponseEntity.ok(mapper.mapToTodo(editedTodo));
    }
}
