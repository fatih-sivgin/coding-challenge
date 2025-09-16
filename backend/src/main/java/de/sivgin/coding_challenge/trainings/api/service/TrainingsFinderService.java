/*
 * Project: coding-challenge
 *
 */
package de.sivgin.coding_challenge.trainings.api.service;

import de.sivgin.coding_challenge.jpa.Training;
import de.sivgin.coding_challenge.jpa.TrainingRepository;
import de.sivgin.coding_challenge.trainings.api.io.PageableTrainingsResource;
import jakarta.annotation.Nonnull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

/**
 * A service to find trainings.
 *
 * @author fatih
 * @since 14/09/2025
 */
@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class TrainingsFinderService {

    private final TrainingRepository trainingRepository;

    public PageableTrainingsResource findAllTrainings(@Nonnull PageRequest pageRequest, boolean includeAppointments) {
        Page<Training> found;
        if (includeAppointments) {
            found = trainingRepository.findAllWithAppointments(pageRequest);
        } else {
            found = trainingRepository.findAll(pageRequest);
        }
        return TrainingsMapper.from(found, includeAppointments);
    }

    public PageableTrainingsResource findAllTrainingsWithTime(@Nonnull PageRequest pageRequest, @Nonnull LocalDate beginDate,
                                                              @Nonnull LocalDate endDate, boolean includeAppointments) {
        Page<Training> found;
        if (includeAppointments) {
            found = trainingRepository.findAllBetweenDatesWithAppointments(beginDate, endDate, pageRequest);
        } else {
            found = trainingRepository.findAllBetweenDates(beginDate, endDate, pageRequest);
        }
        return TrainingsMapper.from(found, includeAppointments);
    }

}
