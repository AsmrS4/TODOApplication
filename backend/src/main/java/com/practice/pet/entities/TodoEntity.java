package com.practice.pet.entities;

import com.practice.pet.enums.TodoStatus;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity
@Table(name = "todos")
public class TodoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(nullable = false)
    private String description;
    @Column(nullable = false)
    private TodoStatus status;
    private LocalDateTime deadlineTime = null;
    private LocalDateTime createTime = LocalDateTime.now();
    @ManyToOne
    @JoinColumn(name = "group_id")
    private GroupEntity group;
}
