package de.sivgin.coding_challenge.training.api.io;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.annotation.Nonnull;

import java.util.List;
import java.util.UUID;

/**
 * Representation of a training.
 *
 * @author fatih
 * @see AppointmentResource
 * @since 14/09/2025
 */
public record TrainingResource(

        @Nonnull
        UUID id,

        @Nonnull
        String description,

        float price,

        @Nonnull
        String speaker,

        /*
         * if appointments are null or empty, then appointments can be ignored
         */
        @JsonInclude(JsonInclude.Include.NON_EMPTY)
        List<AppointmentResource> appointments) {
}
