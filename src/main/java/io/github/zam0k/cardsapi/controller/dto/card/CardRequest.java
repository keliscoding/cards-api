package io.github.zam0k.cardsapi.controller.dto.card;

import lombok.Data;
import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class CardRequest {
    @NotBlank(message = "a name must be provided")
    private String name;

    @NotBlank(message = "a description must be provided")
    private String description;

    @NotNull(message = "a strength must be provided")
    private Integer strength;

    @NotNull(message = "a speed must be provided")
    private Integer speed;

    @NotNull(message = "a skill must be provided")
    private Integer skill;

    @NotNull(message = "a gear must be provided")
    private Integer gear;

    @NotNull(message = "an intellect must be provided")
    private Integer intellect;

    @NotBlank(message = "an image url must be provided")
    @URL(message = "image url must be provided in correct format")
    private String imageUrl;

    @NotNull(message = "an origin id must be provided")
    private Long originId;
}
