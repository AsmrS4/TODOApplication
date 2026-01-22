package com.practice.pet.services.impl;

import com.practice.pet.dto.Group;
import com.practice.pet.dto.GroupRequest;
import com.practice.pet.dto.Todo;
import com.practice.pet.entities.GroupEntity;
import com.practice.pet.entities.TodoEntity;
import com.practice.pet.repository.GroupRepository;
import com.practice.pet.repository.TodoRepository;
import com.practice.pet.services.GroupService;
import com.practice.pet.utils.GroupMapper;
import com.practice.pet.utils.TodoMapper;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroupServiceImpl implements GroupService {
    private final GroupRepository groupRepository;
    private final TodoRepository todoRepository;
    private final TodoMapper todoMapper;
    private final GroupMapper groupMapper;

    public GroupServiceImpl(
            GroupRepository groupRepository,
            TodoRepository todoRepository,
            TodoMapper mapper,
            GroupMapper groupMapper
    ) {
        this.groupRepository = groupRepository;
        this.todoRepository = todoRepository;
        this.todoMapper = mapper;
        this.groupMapper = groupMapper;
    }
    @Override
    public Group createGroup(GroupRequest request) {
        validateGroupName(request.getGroupName());
        GroupEntity newGroup = groupMapper.mapToEntity(request);
        return groupMapper.mapToGroup(save(newGroup));
    }

    @Override
    public List<Group> retrieveGroups() {
        List<GroupEntity> groups = groupRepository.findAll();
        return groups.stream().map(groupMapper::mapToGroup).toList();
    }

    @Override
    public List<Todo> retrieveTodosByGroup(Long groupId) {
        GroupEntity group = findEntityById(groupId);
        List<TodoEntity> todos = todoRepository.findActiveTodosByGroup(group);
        return todos.stream().map(todoMapper::mapToTodo).toList();
    }

    @Override
    public Group editGroup(Long id, GroupRequest request) {
        validateGroupName(request.getGroupName());
        GroupEntity group = findEntityById(id);
        group.setGroupName(request.getGroupName());
        return groupMapper.mapToGroup(save(group));
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
