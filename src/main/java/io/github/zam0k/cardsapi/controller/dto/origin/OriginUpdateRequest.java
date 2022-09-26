package io.github.zam0k.cardsapi.controller.dto.origin;

import lombok.Data;

import javax.validation.constraints.Size;

@Data
public class OriginUpdateRequest {
    @Size(max = 255, message = "name cannot have more than 255 characters")
    private String name;
    @Size(max = 255, message = "description cannot have more than 255 characters")
    private String description;
    @Size(max = 255, message = "creator cannot have more than 255 characters")
    private String creator;
}
