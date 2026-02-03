package com.practice.pet.services.impl;

import com.practice.pet.dto.GroupRequest;
import com.practice.pet.entities.GroupEntity;
import com.practice.pet.entities.TodoEntity;
import com.practice.pet.repository.GroupRepository;
import com.practice.pet.repository.TodoRepository;
import com.practice.pet.services.GroupService;
import com.practice.pet.utils.GroupMapper;
import com.practice.pet.utils.TodoMapper;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GroupServiceImpl implements GroupService {
    private final GroupRepository groupRepository;
    private final TodoRepository todoRepository;
    private final TodoMapper todoMapper;
    private final GroupMapper groupMapper;


    @Override
    public GroupEntity createGroup(GroupRequest request) {
        validateGroupName(request.getGroupName());
        GroupEntity newGroup = groupMapper.mapToEntity(request);
        return save(newGroup);
    }

    @Override
    public List<GroupEntity> retrieveGroups() {
        return groupRepository.findAll();
    }

    @Override
    public List<TodoEntity> retrieveTodosByGroup(Long groupId) {
        GroupEntity group = findEntityById(groupId);
        return todoRepository.findActiveTodosByGroup(group);
    }

    @Override
    public GroupEntity editGroup(Long id, GroupRequest request) {
        validateGroupName(request.getGroupName());
        GroupEntity group = findEntityById(id);
        group.setGroupName(request.getGroupName());
        return save(group);
    }

    @Override
    public void deleteGroup(Long id) {
        GroupEntity group = findEntityById(id);
        groupRepository.delete(group);
    }

    private GroupEntity save(GroupEntity group) {
        return groupRepository.save(group);
    }

    private GroupEntity findEntityById(Long id) {
        return groupRepository.findGroupById(id).orElseThrow(
                () -> new EntityNotFoundException("Группа не найдена")
        );
    }

    private void validateGroupName(String name) {
        if(groupRepository.existsByGroupName(name)) {
            throw new IllegalArgumentException("Имя " + "\"" + name + "\"" + " уже занято");
        }
    }
}
