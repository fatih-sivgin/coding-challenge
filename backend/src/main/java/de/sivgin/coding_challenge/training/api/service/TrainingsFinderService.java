/*
 * Project: coding-challenge
 *
 * Copyright © 2025 Vilua Healthcare GmbH
 */
package de.sivgin.coding_challenge.training.api.service;

import de.sivgin.coding_challenge.domain.AppointmentEntity;
import de.sivgin.coding_challenge.domain.TrainingEntity;
import jakarta.annotation.Nonnull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

/**
 * A service to find trainings.
 * TODO: this should connect to repositories and bind to the real domains
 *
 * @author fatih
 * @since 14/09/2025
 */
@Service
public class TrainingsFinderService {


    // Mock class to send back a domain specific model
    public Page<TrainingEntity> findAllTrainings(@Nonnull PageRequest pageRequest) {
        List<TrainingEntity> trainings = createMockData();
        return new PageImpl<>(trainings, pageRequest, trainings.size());
    }


    public Page<TrainingEntity> findAllTrainingsWithTime(@Nonnull PageRequest pageRequest, @Nonnull LocalDate beginDate, @Nonnull LocalDate endDate) {
        List<TrainingEntity> trainings = createMockData();

        // find trainings matching to the given dates
        List<TrainingEntity> cleaned = new ArrayList<>();
        for (TrainingEntity training : trainings) {
            Optional<AppointmentEntity> firstMatchingAppointment = training.getAppointments().stream()
                    .filter(ae -> !ae.beginDate().isBefore(beginDate) && !ae.endDate().isAfter(endDate))
                    .findFirst();
            if (firstMatchingAppointment.isPresent()) {
                cleaned.add(training);
            }
        }


        return new PageImpl<>(cleaned, pageRequest, cleaned.size());
    }

    private static List<TrainingEntity> createMockData() {
        List<TrainingEntity> trainings = new ArrayList<>();

        UUID uuid = UUID.randomUUID();
        AppointmentEntity a1 = new AppointmentEntity(UUID.randomUUID(), LocalDate.now(), LocalDate.now().plusDays(1), uuid, 10);
        TrainingEntity t1 = TrainingEntity.builder()
                .id(uuid)
                .speaker("speaker")
                .price(12.9f)
                .description("a description")
                .appointments(List.of(a1))
                .build();
        trainings.add(t1);

        UUID uuid2 = UUID.randomUUID();
        TrainingEntity t2 = TrainingEntity.builder()
                .id(uuid2)
                .speaker("speaker2")
                .price(99.9f)
                .description("another description")
                .appointments(Collections.emptyList())
                .build();
        trainings.add(t2);

        return trainings;
    }
}
