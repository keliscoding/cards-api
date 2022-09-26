package io.github.zam0k.cardsapi.controller.dto.origin;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class OriginRequest {
    @Size(max = 255, message = "name cannot have more than 255 characters")
    @NotBlank(message = "a name must be provided")
    private String name;

    @Size(max = 255, message = "description cannot have more than 255 characters")
    @NotBlank(message = "a description must be provided")
    private String description;

    @Size(max = 255, message = "creator cannot have more than 255 characters")
    @NotBlank(message = "a creator must be provided")
    private String creator;
}
