package com.practice.pet.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Group {
    private Long id;
    private String groupName;
    private LocalDateTime createTime;
}
