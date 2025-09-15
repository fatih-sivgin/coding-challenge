package de.sivgin.coding_challenge.training.api.io;

import jakarta.validation.constraints.NotNull;

/**
 * Resource to create a {@link TrainingResource}
 *
 * @author fatih
 * @since 14/09/2025
 */
public record CreateTraining(

        @NotNull
        String description,

        @NotNull
        Float price,

        @NotNull
        String speaker) {
}
