package com.practice.pet.services.impl;

import com.practice.pet.dto.CreateTodo;
import com.practice.pet.dto.EditTodo;
import com.practice.pet.dto.FilterParams;
import com.practice.pet.entities.TodoEntity;
import com.practice.pet.enums.TodoStatus;
import com.practice.pet.exceptions.IncorrectDateException;
import com.practice.pet.repository.TodoRepository;
import com.practice.pet.services.TodoService;
import com.practice.pet.utils.FilterSpecification;
import com.practice.pet.utils.TodoMapper;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TodoServiceImpl implements TodoService {
    private final TodoRepository todoRepository;
    private final TodoMapper mapper;

    @Override
    public TodoEntity createTodo(CreateTodo createTodo) {
        validateDeadline(createTodo.getDeadlineTime());
        TodoEntity newTodo = mapper.mapToEntity(createTodo);
        return save(newTodo);
    }

    @Override
    public TodoEntity editTodo(UUID id, EditTodo editTodo) {
        validateDeadline(editTodo.getDeadlineTime());
        TodoEntity todoEntity = findEntityById(id);
        mapper.mergeChanges(todoEntity, editTodo);
        return save(todoEntity);
    }

    @Override
    public List<TodoEntity> retrieveTodos(FilterParams filterParams) {
        validateFilterParams(filterParams);
        Specification<TodoEntity> filterSpecification = FilterSpecification.configureFilters(filterParams);
        return todoRepository.findAll(filterSpecification);
    }

    @Override
    public TodoEntity changeStatus(UUID id, TodoStatus status) {
        TodoEntity todoEntity = findEntityById(id);
        todoEntity.setStatus(status);
        return save(todoEntity);
    }

    @Override
    public void deleteTodo(UUID id) {
        TodoEntity todoEntity = findEntityById(id);
        todoRepository.delete(todoEntity);
    }

    private void validateDeadline(LocalDateTime deadline) {
        if(deadline != null && LocalDateTime.now().isAfter(deadline)) {
            throw new IllegalArgumentException("Дата дедлайна не может быть раньше текущего дня");
        }
    }

    private TodoEntity save(TodoEntity todoEntity) {
        return todoRepository.save(todoEntity);
    }

    private TodoEntity findEntityById(UUID id) {
        return todoRepository.findTodoById(id)
                .orElseThrow(()-> new EntityNotFoundException("Задача не найдена"));
    }

    private void validateFilterParams(FilterParams params){
        validateDates(params);
        validateStatus(params);
    }

    private void validateStatus(FilterParams params) {
        List<TodoStatus> statuses = List.of(TodoStatus.values());
        var status = params.getStatus();
        if(status != null) {
            if(!statuses.contains(status)) throw new IllegalArgumentException("Неизвестный статус задачи");
        }
    }

    private void validateDates(FilterParams params) {
        if(params.getDateFrom() != null || params.getDateTo() != null) {
            LocalDateTime today = LocalDateTime.now();
            LocalDateTime dateFrom = params.getDateFrom();
            LocalDateTime dateTo = params.getDateTo();
            if(dateFrom != null && dateFrom.isAfter(today)) {
                throw new IncorrectDateException("Нижняя граница даты не может быть больше текущего дня");
            }
            if(dateTo != null && dateTo.isAfter(today)) {
                throw new IncorrectDateException("Верхняя граница даты не может быть больше текущего дня");
            }
            if(dateFrom != null && dateTo != null ) {
                if(dateFrom.isAfter(dateTo))
                    throw new IncorrectDateException("Нижняя граница даты не может быть больше верхней");
            }
        }
    }
}
