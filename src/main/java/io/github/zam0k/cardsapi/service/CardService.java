package io.github.zam0k.cardsapi.service;

import io.github.zam0k.cardsapi.controller.dto.card.CardRequest;
import io.github.zam0k.cardsapi.controller.dto.card.CardResponse;
import io.github.zam0k.cardsapi.controller.dto.card.CardUpdateRequest;

import java.util.List;

public interface CardService {
    CardResponse create(CardRequest cardRequest);

    List<CardResponse> findAll();

    CardResponse findById(Long id);

    CardResponse update(Long id, CardUpdateRequest cardUpdateRequest);

    void remove(Long id);
}
