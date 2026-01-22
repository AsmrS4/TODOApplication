package com.practice.pet.utils;

import com.practice.pet.dto.CreateTodo;
import com.practice.pet.dto.EditTodo;
import com.practice.pet.dto.Todo;
import com.practice.pet.entities.GroupEntity;
import com.practice.pet.entities.TodoEntity;
import com.practice.pet.enums.TodoStatus;
import com.practice.pet.repository.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TodoMapper {
    private final GroupRepository groupRepository;
    @Autowired
    public TodoMapper(GroupRepository repository) {
        groupRepository = repository;
    }
    public Todo mapToTodo(TodoEntity todoEntity) {
        Todo todo = new Todo();
        todo.setId(todoEntity.getId());
        todo.setStatus(todoEntity.getStatus());
        todo.setDescription(todoEntity.getDescription());
        todo.setDeadlineTime(todoEntity.getDeadlineTime());
        todo.setCreateTime(todoEntity.getCreateTime());
        return todo;
    }

    public TodoEntity mapToEntity(CreateTodo createTodo) {
        TodoEntity newTodo = new TodoEntity();
        newTodo.setDescription(createTodo.getDescription());
        newTodo.setStatus(TodoStatus.NEW);
        newTodo.setDeadlineTime(createTodo.getDeadlineTime());
        newTodo.setGroup(findGroupById(createTodo.getGroupId()));
        return newTodo;
    }

    public void mergeChanges(TodoEntity todoEntity, EditTodo editTodo) {
        todoEntity.setDescription(editTodo.getDescription());
        todoEntity.setDeadlineTime(editTodo.getDeadlineTime());
    }

    private GroupEntity findGroupById(Long id) {
        return groupRepository.findGroupById(id).orElse(null);
    }
}
