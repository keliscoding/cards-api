package io.github.zam0k.cardsapi.repository;

import io.github.zam0k.cardsapi.model.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CardRepository extends JpaRepository<Card, Long> {
    Optional<Card> findCardByNameIgnoreCase(String name);
}
