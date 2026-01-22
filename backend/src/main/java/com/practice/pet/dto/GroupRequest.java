package com.practice.pet.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class GroupRequest {
    @NotBlank(message = "Заголовок не может быть пустым")
    @Min(value = 3, message = "Минимальная длина заголовка 3 символа")
    @Max(value = 50, message = "Максимальная длина заголовка 50 символов")
    private String groupName;
}
