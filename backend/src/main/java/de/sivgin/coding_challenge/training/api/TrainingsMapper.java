/*
 * Project: coding-challenge
 *
 * Copyright © 2025 Vilua Healthcare GmbH
 */
package de.sivgin.coding_challenge.training.api;

import de.sivgin.coding_challenge.jpa.Appointment;
import de.sivgin.coding_challenge.jpa.Training;
import de.sivgin.coding_challenge.training.api.io.AppointmentResource;
import de.sivgin.coding_challenge.training.api.io.PageableTrainingsResource;
import de.sivgin.coding_challenge.training.api.io.TrainingResource;
import lombok.experimental.UtilityClass;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * @author fatih
 * @since 14/09/2025
 */
@UtilityClass
public class TrainingsMapper {

    // some mappers to prepare response
    static PageableTrainingsResource from(Page<Training> trainings) {
        List<TrainingResource> trainingsResource = trainings.getContent().stream()
                .map(TrainingsMapper::from)
                .toList();
        return new PageableTrainingsResource(trainings.getTotalElements(), trainingsResource);
    }

    static TrainingResource from(Training entity) {
        List<AppointmentResource> appointments = entity.getAppointments().stream()
                    .map(TrainingsMapper::from)
                    .toList();

        return new TrainingResource(entity.getId(), entity.getDescription(), entity.getPrice(), entity.getSpeaker(), appointments);
    }

    static AppointmentResource from(Appointment entity) {
        return new AppointmentResource(entity.getId(), entity.getBeginDate(), entity.getEndDate(), entity.getTraining().getId(), entity.getNumberOfParticipants());
    }
}
