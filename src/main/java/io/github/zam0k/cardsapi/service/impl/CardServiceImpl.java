package io.github.zam0k.cardsapi.service.impl;

import io.github.zam0k.cardsapi.controller.dto.card.CardRequest;
import io.github.zam0k.cardsapi.controller.dto.card.CardResponse;
import io.github.zam0k.cardsapi.controller.dto.card.CardUpdateRequest;
import io.github.zam0k.cardsapi.exception.BusinessRuleException;
import io.github.zam0k.cardsapi.exception.NotFoundException;
import io.github.zam0k.cardsapi.model.Card;
import io.github.zam0k.cardsapi.model.Origin;
import io.github.zam0k.cardsapi.repository.CardRepository;
import io.github.zam0k.cardsapi.repository.OriginRepository;
import io.github.zam0k.cardsapi.service.CardService;
import io.github.zam0k.cardsapi.util.CardMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class CardServiceImpl implements CardService {

    private final CardRepository repository;
    private final OriginRepository originRepository;

    @Override
    @Transactional
    public CardResponse create(CardRequest cardRequest) {
        Boolean isCardRepeated = repository.findCardByNameIgnoreCase(cardRequest.getName()).isPresent();

        if (isCardRepeated)
            throw new BusinessRuleException("Card name {name=" + cardRequest.getName() + "} already exists");

        Optional<Origin> optionalOrigin = originRepository.findById(cardRequest.getOriginId());

        if (!optionalOrigin.isPresent())
            throw new NotFoundException("Origin was not found for parameters {id=" + cardRequest.getOriginId() + "}");

        Card card = CardMapper.INSTANCE.cardRequestToCard(cardRequest);

        card.setOrigin(optionalOrigin.get());
        repository.save(card);

        return CardMapper.INSTANCE.cardToCardResponse(card);
    }

    @Override
    public List<CardResponse> findAll() {
        return CardMapper.INSTANCE.cardListToCardResponseList(repository.findAll());
    }

    @Override
    public CardResponse findById(Long id) {
        Card card = repository.findById(id).orElseThrow(() ->
                new NotFoundException("Card was not found for parameters {id=" + id + "}"));
        return CardMapper.INSTANCE.cardToCardResponse(card);
    }

    @Override
    @Transactional
    public CardResponse update(Long id, CardUpdateRequest cardUpdateRequest) {

        Optional<Card> cardOptional = repository.findById(id);

        if (!cardOptional.isPresent()) {
            throw new NotFoundException("Card was not found for parameters {id=" + id + "}");
        }

        Card card = cardOptional.get();

        card = CardMapper.INSTANCE.cardUpdateRequestToCard(card, cardUpdateRequest);

        repository.save(card);

        return CardMapper.INSTANCE.cardToCardResponse(card);
    }

    @Override
    @Transactional
    public void remove(Long id) {
        if (!repository.existsById(id)) {
            throw new NotFoundException("Card was not found for parameters {id=" + id + "}");
        }
        repository.deleteById(id);
    }
}
