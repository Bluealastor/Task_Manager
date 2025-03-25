package com.BlueAlastor.task.dto;

import lombok.Data;

@Data
public class DTOTask {
    private Long id;

    @NotBlank(message = "Il titolo è obbligatorio")
    private String title;

    private String description;
    private boolean completed;
}
