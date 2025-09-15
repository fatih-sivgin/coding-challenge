/*
 * Project: coding-challenge
 *
 * Copyright © 2025 Vilua Healthcare GmbH
 */
package de.sivgin.coding_challenge.training.api.service;

import de.sivgin.coding_challenge.domain.TrainingEntity;
import de.sivgin.coding_challenge.training.api.io.CreateTraining;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * A service to store trainings.
 * TODO: this should connect to repositories and bind to the real domains
 *
 * @author fatih
 * @since 14/09/2025
 */
@Service
public class TrainingsCreationService {


    /**
     * Creates and stores {@link TrainingEntity}
     *
     * @param createTraining the training to store
     * @return a new instance of a {@link TrainingEntity}
     */
    @Transactional
    public TrainingEntity createTraining(CreateTraining createTraining) {
        return createMockData(createTraining);
    }

    private static TrainingEntity createMockData(CreateTraining createTraining) {
        return TrainingEntity.builder()
                .id(UUID.randomUUID())
                .speaker(createTraining.speaker())
                .price(createTraining.price())
                .description(createTraining.description()).build();
    }
}
