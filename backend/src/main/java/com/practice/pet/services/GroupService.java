package com.practice.pet.services;

import com.practice.pet.dto.GroupRequest;
import com.practice.pet.entities.GroupEntity;
import com.practice.pet.entities.TodoEntity;

import java.util.List;

public interface GroupService {
    GroupEntity createGroup(GroupRequest request);
    List<GroupEntity > retrieveGroups();
    List<TodoEntity> retrieveTodosByGroup(Long groupId);
    GroupEntity editGroup(Long id, GroupRequest request);
    void deleteGroup(Long id);
}
