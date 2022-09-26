package io.github.zam0k.cardsapi.controller.dto.origin;

import lombok.Data;

@Data
public class OriginUpdateRequest {
    private String name;
    private String description;
    private String creator;
}
