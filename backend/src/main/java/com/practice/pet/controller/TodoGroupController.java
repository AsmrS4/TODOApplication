package com.practice.pet.controller;

import com.practice.pet.dto.Group;
import com.practice.pet.dto.Todo;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/group")
@Tag(name = "Group Controller", description = "CRUD эндпоинты для сущности GroupEntity")
public class TodoGroupController {
    @PostMapping
    public ResponseEntity<Group> createGroup() {
        return null;
    }
    @GetMapping
    public ResponseEntity<List<Group>> retrieveGroups() {
        return null;
    }
    @GetMapping("/{groupId}")
    public ResponseEntity<List<Todo>> retrieveTodosByGroup(@PathVariable Long groupId) {
        return null;
    }
    @PostMapping("/{groupId}")
    public ResponseEntity<Group> editGroup(@PathVariable Long groupId) {
        return null;
    }
    @DeleteMapping("/{groupId}")
    public ResponseEntity<?> deleteGroup(@PathVariable Long groupId) {
        return null;
    }

}
