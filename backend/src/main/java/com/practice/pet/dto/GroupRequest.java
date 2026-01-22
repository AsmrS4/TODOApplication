package com.practice.pet.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class GroupRequest {
    @NotBlank(message = "Заголовок не может быть пустым")
    @Size(min = 3, max = 255, message = "Допустимая длина заголовка группы от 3 до 50 символов")
    private String groupName;
}
