/*
 * Project: coding-challenge
 *
 */
package de.sivgin.coding_challenge.trainings.api.service;

import de.sivgin.coding_challenge.jpa.Training;
import de.sivgin.coding_challenge.jpa.TrainingRepository;
import de.sivgin.coding_challenge.trainings.api.io.CreateTraining;
import de.sivgin.coding_challenge.trainings.api.io.TrainingResource;
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
@Transactional
public class TrainingsCreationService {

    private final TrainingRepository repository;

    /**
     * Creates and stores {@link Training}
     *
     * @param createTraining the training to store
     * @return a new instance of a {@link Training}
     */
    public TrainingResource createTraining(CreateTraining createTraining) {
        Training training = new Training(createTraining.description(), createTraining.price(), createTraining.speaker());
        repository.save(training);
        return TrainingsMapper.from(training, false);
    }

}
