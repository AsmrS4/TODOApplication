package com.practice.pet.services;

import com.practice.pet.dto.Group;
import com.practice.pet.dto.GroupRequest;
import com.practice.pet.dto.Todo;

import java.util.List;

public interface GroupService {
    Group createGroup(GroupRequest request);
    List<Group> retrieveGroups();
    List<Todo> retrieveTodosByGroup(Long groupId);
    Group editGroup(Long id, GroupRequest request);
    void deleteGroup(Long id);
}
