package de.sivgin.coding_challenge.trainings.api.io;

import jakarta.validation.constraints.NotNull;

/**
 * An incoming resource to create a {@link TrainingResource}
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
