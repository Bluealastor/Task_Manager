package com.BlueAlastor.task.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DTOTask {
    private Long id;

    @NotBlank(message = "Il titolo è obbligatorio")
    private String title;

    private String description;
    private boolean completed;
}
