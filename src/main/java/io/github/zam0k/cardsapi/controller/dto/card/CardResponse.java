package io.github.zam0k.cardsapi.controller.dto.card;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.github.zam0k.cardsapi.controller.dto.origin.OriginResponse;
import lombok.Data;

@Data
public class CardResponse {
    @JsonIgnore
    private Long id;
    private String name;
    private String description;
    private Integer strength;
    private Integer speed;
    private Integer skill;
    private Integer gear;
    private Integer intellect;
    private String imageUrl;
    private OriginResponse origin;
}
