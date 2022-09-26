package io.github.zam0k.cardsapi.model;

import lombok.Data;

import javax.persistence.*;
import java.time.OffsetDateTime;

import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.IDENTITY;

@Entity(name = "cards")
@Data
public class Card {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    private String name;
    private String description;
    private Integer strength;
    private Integer speed;
    private Integer skill;
    private Integer gear;
    private Integer intellect;
    @Column(name = "image_url")
    private String imageUrl;
    @Column(name = "created_at")
    private OffsetDateTime createdAt;
    @Column(name = "updated_at")
    private OffsetDateTime updatedAt;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "origin_id")
    private Origin origin;

    @PrePersist
    private void createdAt() {
        this.createdAt = OffsetDateTime.now();
        this.updatedAt = OffsetDateTime.now();
    }

    @PreUpdate
    private void updatedAt() {
        this.updatedAt = OffsetDateTime.now();
    }

}
