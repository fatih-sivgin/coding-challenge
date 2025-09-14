/*
 * Project: coding-challenge
 *
 * Copyright © 2025 Vilua Healthcare GmbH
 */
package de.sivgin.coding_challenge.training.api.io;

import java.util.List;

/**
 * Representation of a list of trainings.
 *
 * @author fatih
 * @see TrainingResource
 * @since 14/09/2025
 */
public record PageableTrainingsResource(long totalElements, List<TrainingResource> trainings) {

}
