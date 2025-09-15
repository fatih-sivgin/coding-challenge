package de.sivgin.coding_challenge.jpa;

import jakarta.annotation.Nonnull;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Represents the training on storage level.
 *
 * @author fatih
 * @since 14/09/2025
 */
@ToString(of = {"id", "description"})
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table
@Entity
public class Training {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Setter
    @Basic(optional = false)
    private String description;

    @Setter
    @Basic(optional = false)
    private float price;

    @Setter
    @Basic(optional = false)
    private String speaker;

    @Setter
    @OneToMany(mappedBy = "training" /* point to training field in Appointment */, fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Appointment> appointments = new ArrayList<>();

    /**
     * Constructor to create a new instance of training
     */
    public Training(@Nonnull String description, float price, @Nonnull String speaker) {
        this.description = description;
        this.price = price;
        this.speaker = speaker;
    }
}
