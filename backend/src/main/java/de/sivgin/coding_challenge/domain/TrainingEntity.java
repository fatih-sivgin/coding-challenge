package de.sivgin.coding_challenge.domain;

import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.UUID;

/**
 * Represents the training on storage level.
 *
 * @author fatih
 * @since 14/09/2025
 */
@Data
@Builder
public class TrainingEntity {

    private UUID id;

    private String description;

    private float price;

    private String speaker;

    private List<AppointmentEntity> appointments;

    @Builder
    public TrainingEntity(UUID id, String description, float price, String speaker, List<AppointmentEntity> appointments) {
        this.id = id;
        this.description = description;
        this.price = price;
        this.speaker = speaker;
        this.appointments = appointments;
    }
}
