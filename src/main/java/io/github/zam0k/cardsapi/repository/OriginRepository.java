package io.github.zam0k.cardsapi.repository;

import io.github.zam0k.cardsapi.model.Origin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OriginRepository extends JpaRepository<Origin, Long> {
}
