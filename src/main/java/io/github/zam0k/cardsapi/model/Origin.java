package io.github.zam0k.cardsapi.model;

import lombok.Data;

import javax.persistence.*;
import java.time.OffsetDateTime;

import static javax.persistence.GenerationType.IDENTITY;

@Entity(name = "origins")
@Data
public class Origin {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    private String name;
    private String description;
    private String creator;
    @Column(name = "created_at")
    private OffsetDateTime createdAt;
    @Column(name = "updated_at")
    private OffsetDateTime updatedAt;

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
