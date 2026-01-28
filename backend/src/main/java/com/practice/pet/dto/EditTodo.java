package com.practice.pet.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class EditTodo {
    @NotBlank(message = "Описание не должно быть пустым")
    @Size(min = 3, max = 255, message = "Допустимая длина описания задачи от 3 до 255 символов")
    private String description;
    private LocalDateTime deadlineTime;
}
