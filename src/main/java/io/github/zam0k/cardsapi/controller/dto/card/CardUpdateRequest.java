package io.github.zam0k.cardsapi.controller.dto.card;

import lombok.Data;
import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.Size;

@Data
public class CardUpdateRequest {
    @Size(max = 255, message = "name cannot have more than 255 characters")
    private String name;
    @Size(max = 255, message = "description cannot have more than 255 characters")
    private String description;
    private Integer strength;
    private Integer speed;
    private Integer skill;
    private Integer gear;
    private Integer intellect;
    @URL(message = "image url must be provided in correct format")
    @Size(max = 255, message = "imageUrl cannot have more than 255 characters")
    private String imageUrl;
    private Long originId;
}
