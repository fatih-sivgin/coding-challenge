/*
 * Project: coding-challenge
 *
 * Copyright © 2025 Vilua Healthcare GmbH
 */
package de.sivgin.coding_challenge.training.api.service;

import de.sivgin.coding_challenge.jpa.Training;
import de.sivgin.coding_challenge.jpa.TrainingRepository;
import jakarta.annotation.Nonnull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

/**
 * A service to find trainings.
 *
 * @author fatih
 * @since 14/09/2025
 */
@RequiredArgsConstructor
@Service
public class TrainingsFinderService {

    private final TrainingRepository trainingRepository;

    public Page<Training> findAllTrainings(@Nonnull PageRequest pageRequest, boolean includeAppointments) {
        if (includeAppointments) {
            return trainingRepository.findAllWithAppointments(pageRequest);
        }
        return trainingRepository.findAll(pageRequest);
    }

    public Page<Training> findAllTrainingsWithTime(@Nonnull PageRequest pageRequest, @Nonnull LocalDate beginDate,
                                                   @Nonnull LocalDate endDate, boolean includeAppointments) {
        if (includeAppointments) {
            return trainingRepository.findAllBetweenDatesWithAppointments(beginDate, endDate, pageRequest);
        }
        return trainingRepository.findAllBetweenDates(beginDate, endDate, pageRequest);
    }

}
