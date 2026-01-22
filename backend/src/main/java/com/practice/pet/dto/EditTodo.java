package com.practice.pet.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class EditTodo {
    @NotBlank(message = "Описание не должно быть пустым")
    @Min(value = 3, message = "Минимальная длина описания 3 символа")
    @Max(value = 255, message = "Максимальная длина описания 255 символов")
    private String description;
    private LocalDateTime deadlineTime;
}
