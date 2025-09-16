package de.sivgin.coding_challenge.trainings.api.io;

import jakarta.annotation.Nonnull;

import java.time.LocalDate;
import java.util.UUID;

/**
 * Representation of an appointment.
 *
 * @author fatih
 * @since 14/09/2025
 */
public record AppointmentResource(

        @Nonnull
        UUID id,

        @Nonnull
        LocalDate beginDate,

        @Nonnull
        LocalDate endDate,

        @Nonnull
        UUID trainingId,

        int numberOfParticipants) {
}
