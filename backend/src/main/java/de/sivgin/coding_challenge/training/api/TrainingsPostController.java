/*
 * Project: coding-challenge
 *
 * Copyright © 2025 Vilua Healthcare GmbH
 */
package de.sivgin.coding_challenge.training.api;

import de.sivgin.coding_challenge.domain.TrainingEntity;
import de.sivgin.coding_challenge.training.api.io.CreateTraining;
import de.sivgin.coding_challenge.training.api.io.TrainingResource;
import de.sivgin.coding_challenge.training.api.service.TrainingsCreationService;
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

    @PostMapping("/api/v1/trainings")
    public ResponseEntity<TrainingResource> postTraining(@Valid @RequestBody CreateTraining createTraining) {
        TrainingEntity training = trainingsCreationService.createTraining(createTraining);
        URI location = URI.create("/api/v1/trainings/" + training.getId()); // Note: this resource is not yet defined
        TrainingResource body = TrainingsMapper.from(training, false);
        return ResponseEntity.created(location).body(body);
    }
}
