package io.github.zam0k.cardsapi.controller.dto.card;

import lombok.Data;
import org.hibernate.validator.constraints.URL;

@Data
public class CardUpdateRequest {
    private String name;
    private String description;
    private Integer strength;
    private Integer speed;
    private Integer skill;
    private Integer gear;
    private Integer intellect;
    @URL(message = "image url must be provided in correct format")
    private String imageUrl;
    private Long originId;
}
