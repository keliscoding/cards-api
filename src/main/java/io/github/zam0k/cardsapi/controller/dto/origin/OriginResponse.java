package io.github.zam0k.cardsapi.controller.dto.origin;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
public class OriginResponse {
    @JsonIgnore
    private Long id;
    private String name;
    private String description;
    private String creator;
}
