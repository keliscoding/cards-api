package io.github.zam0k.cardsapi.util;

import io.github.zam0k.cardsapi.controller.dto.card.CardRequest;
import io.github.zam0k.cardsapi.controller.dto.card.CardResponse;
import io.github.zam0k.cardsapi.controller.dto.card.CardUpdateRequest;
import io.github.zam0k.cardsapi.model.Card;
import org.mapstruct.Condition;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface CardMapper {
    CardMapper INSTANCE = Mappers.getMapper(CardMapper.class);

    CardResponse cardToCardResponse(Card card);

    Card cardRequestToCard(CardRequest cardRequest);

    List<CardResponse> cardListToCardResponseList(List<Card> cards);

    Card cardUpdateRequestToCard(@MappingTarget Card card, CardUpdateRequest cardUpdateRequest);

    @Condition
    default boolean isNotEmpty(String value) {
        return value != null && !value.trim().isEmpty();
    }

}
