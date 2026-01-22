package com.practice.pet.dto;

import com.practice.pet.enums.TodoStatus;

import java.time.LocalDateTime;
import java.util.UUID;

public class Todo {
    private UUID id;
    private String description;
    private TodoStatus status;
    private LocalDateTime deadlineTime;
    private LocalDateTime createTime;
}
