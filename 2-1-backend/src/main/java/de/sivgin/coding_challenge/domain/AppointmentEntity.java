package de.sivgin.coding_challenge.domain;

import java.time.LocalDate;
import java.util.UUID;

/**
 * Representation of an appointment on storage level.
 *
 * @author fatih
 * @since 14/09/2025
 */
public record AppointmentEntity(UUID id, LocalDate beginDate, LocalDate endDate, UUID trainingId,
                                int numberOfParticipants) {
}
