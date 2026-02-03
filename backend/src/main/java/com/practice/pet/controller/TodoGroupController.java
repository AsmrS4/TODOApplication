package com.practice.pet.controller;

import com.practice.pet.dto.Group;
import com.practice.pet.dto.GroupRequest;
import com.practice.pet.dto.Todo;
import com.practice.pet.entities.GroupEntity;
import com.practice.pet.entities.TodoEntity;
import com.practice.pet.services.GroupService;
import com.practice.pet.utils.GroupMapper;
import com.practice.pet.utils.TodoMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/group")
@Tag(name = "Group Controller", description = "CRUD эндпоинты для сущности GroupEntity")
@RequiredArgsConstructor
public class TodoGroupController {
    private final GroupService groupService;
    private final GroupMapper mapper;
    private final TodoMapper todoMapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(
            summary = "Создание группы",
            description = "Вы можете создать новую группу",
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "Успешная обработка",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(
                                            implementation = Group.class
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
    public Group createGroup(@RequestBody GroupRequest request) {
        GroupEntity createdGroup = groupService.createGroup(request);
        return mapper.mapToGroup(createdGroup);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @Operation(
            summary = "Получение списка групп",
            description = "Вы можете получить список групп задач",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Успешная обработка",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(
                                            type = "array",
                                            implementation = Group.class
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
    public List<Group> retrieveGroups() {
        List<GroupEntity> groups = groupService.retrieveGroups();
        return groups.stream().map(mapper::mapToGroup).toList();
    }

    @GetMapping("/{groupId}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(
            summary = "Получение списка задач по группе",
            description = "Получение списка задач",
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
    public List<Todo> retrieveTodosByGroup(@PathVariable Long groupId) {
        List<TodoEntity> todos = groupService.retrieveTodosByGroup(groupId);
        return todos.stream().map(todoMapper::mapToTodo).toList();
    }

    @PutMapping("/{groupId}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(
            summary = "Редактирование группы задач",
            description = "Вы можете изменить название для группы задачи",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Успешная обработка",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(
                                            implementation = Group.class
                                    )
                            )
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Ошибка клиента"
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Группа не найдена"
                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Ошибка сервера"
                    )
            }
    )
    public Group editGroup(@PathVariable Long groupId, @RequestBody GroupRequest request) {
        GroupEntity editedGroup = groupService.editGroup(groupId, request);
        return mapper.mapToGroup(editedGroup);
    }

    @DeleteMapping("/{groupId}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(
            summary = "Удаление группы задач",
            description = "Вы можете удалить группу со всеми связанными с ней задачами",
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
                            description = "Группа не найдена"
                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Ошибка сервера"
                    )
            }
    )
    public void deleteGroup(@PathVariable Long groupId) {
        groupService.deleteGroup(groupId);
    }

}
