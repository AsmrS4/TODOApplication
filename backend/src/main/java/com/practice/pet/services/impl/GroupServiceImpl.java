package com.practice.pet.services.impl;

import com.practice.pet.dto.Group;
import com.practice.pet.dto.GroupRequest;
import com.practice.pet.dto.Todo;
import com.practice.pet.services.GroupService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroupServiceImpl implements GroupService {
    @Override
    public Group createGroup(GroupRequest request) {
        return null;
    }

    @Override
    public List<Group> retrieveGroups() {
        return List.of();
    }

    @Override
    public List<Todo> retrieveTodosByGroup(Long groupId) {
        return List.of();
    }

    @Override
    public Group editGroup(Long id, GroupRequest request) {
        return null;
    }

    @Override
    public void deleteGroup(Long id) {

    }
}
