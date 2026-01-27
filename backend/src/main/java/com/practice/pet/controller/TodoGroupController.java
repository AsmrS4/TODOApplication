package com.practice.pet.controller;

import com.practice.pet.dto.Group;
import com.practice.pet.dto.GroupRequest;
import com.practice.pet.dto.Todo;
import com.practice.pet.services.GroupService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/group")
@Tag(name = "Group Controller", description = "CRUD эндпоинты для сущности GroupEntity")
public class TodoGroupController {
    private final GroupService groupService;
    @Autowired
    public TodoGroupController(GroupService groupService) {
        this.groupService = groupService;
    }
    @PostMapping
    public ResponseEntity<Group> createGroup(@RequestBody GroupRequest request) {
        return ResponseEntity.ok(groupService.createGroup(request));
    }
    @GetMapping
    public ResponseEntity<List<Group>> retrieveGroups() {
        return ResponseEntity.ok(groupService.retrieveGroups());
    }
    @GetMapping("/{groupId}")
    public ResponseEntity<List<Todo>> retrieveTodosByGroup(@PathVariable Long groupId) {
        return ResponseEntity.ok(groupService.retrieveTodosByGroup(groupId));
    }
    @PutMapping("/{groupId}")
    public ResponseEntity<Group> editGroup(@PathVariable Long groupId, @RequestBody GroupRequest request) {
        return ResponseEntity.ok(groupService.editGroup(groupId, request));
    }
    @DeleteMapping("/{groupId}")
    public void deleteGroup(@PathVariable Long groupId) {
        groupService.deleteGroup(groupId);
    }

}
