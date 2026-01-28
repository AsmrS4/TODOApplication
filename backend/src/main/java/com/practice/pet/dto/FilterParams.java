package com.practice.pet.dto;

import com.practice.pet.enums.TodoStatus;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.PastOrPresent;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class FilterParams {
    private LocalDateTime dateFrom;
    private LocalDateTime dateTo;
    private TodoStatus status;
}
