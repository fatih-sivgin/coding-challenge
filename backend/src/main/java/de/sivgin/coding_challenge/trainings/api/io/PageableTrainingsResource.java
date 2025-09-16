/*
 * Project: coding-challenge
 *
 */
package de.sivgin.coding_challenge.trainings.api.io;

import java.util.List;

/**
 * Representation of a list of trainings (TrainingResource).
 *
 * @author fatih
 * @see TrainingResource
 * @since 14/09/2025
 */
public record PageableTrainingsResource(long totalElements, List<TrainingResource> trainings) {

}
