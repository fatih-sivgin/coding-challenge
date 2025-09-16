/*
 * Project: coding-challenge
 *
 */
package de.sivgin.coding_challenge.trainings.api;

import de.sivgin.coding_challenge.trainings.api.io.CreateTraining;
import de.sivgin.coding_challenge.trainings.api.io.TrainingResource;
import de.sivgin.coding_challenge.trainings.api.service.TrainingsCreationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

/**
 * Spring rest controller to create new trainings.
 *
 * @author fatih
 * @since 14/09/2025
 */
@RequiredArgsConstructor
@RestController
public class TrainingsPostController {

    private final TrainingsCreationService trainingsCreationService;

    /**
     * @param createTraining the training to create
     * @return a created training with status code 201
     */
    @PostMapping("/api/v1/trainings")
    public ResponseEntity<TrainingResource> postTraining(@Valid @RequestBody CreateTraining createTraining) {
        TrainingResource training = trainingsCreationService.createTraining(createTraining);
        URI location = URI.create("/api/v1/trainings/" + training.id()); // Note: this resource is not yet defined
        return ResponseEntity.created(location).body(training);
    }
}
