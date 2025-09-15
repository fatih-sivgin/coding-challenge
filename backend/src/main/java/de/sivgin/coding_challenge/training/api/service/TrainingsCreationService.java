/*
 * Project: coding-challenge
 *
 * Copyright © 2025 Vilua Healthcare GmbH
 */
package de.sivgin.coding_challenge.training.api.service;

import de.sivgin.coding_challenge.jpa.Training;
import de.sivgin.coding_challenge.jpa.TrainingRepository;
import de.sivgin.coding_challenge.training.api.io.CreateTraining;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * A service to store trainings.
 *
 * @author fatih
 * @since 14/09/2025
 */
@RequiredArgsConstructor
@Service
public class TrainingsCreationService {
    
    private final TrainingRepository repository;

    /**
     * Creates and stores {@link Training}
     *
     * @param createTraining the training to store
     * @return a new instance of a {@link Training}
     */
    @Transactional
    public Training createTraining(CreateTraining createTraining) {

        Training training = new Training(createTraining.description(), createTraining.price(), createTraining.speaker());

        return repository.save(training);
    }

}
