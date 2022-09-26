package io.github.zam0k.cardsapi.controller.dto.origin;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class OriginRequest {
    @NotBlank(message = "a name must be provided")
    private String name;

    @NotBlank(message = "a description must be provided")
    private String description;

    @NotBlank(message = "a creator must be provided")
    private String creator;
}
