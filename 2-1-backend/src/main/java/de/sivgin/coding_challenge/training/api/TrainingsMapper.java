/*
 * Project: coding-challenge
 *
 * Copyright © 2025 Vilua Healthcare GmbH
 */
package de.sivgin.coding_challenge.training.api;

import de.sivgin.coding_challenge.domain.AppointmentEntity;
import de.sivgin.coding_challenge.domain.TrainingEntity;
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
    static PageableTrainingsResource from(Page<TrainingEntity> trainings, boolean includeAppointments) {
        List<TrainingResource> trainingsResource = trainings.getContent().stream()
                .map(t -> from(t, includeAppointments))
                .toList();
        return new PageableTrainingsResource(trainings.getTotalElements(), trainingsResource);
    }

    static TrainingResource from(TrainingEntity entity, boolean includeAppointments) {
        List<AppointmentResource> appointments = null;
        if (includeAppointments) {

            appointments = entity.getAppointments().stream()
                    .map(TrainingsMapper::from)
                    .toList();
        }
        return new TrainingResource(entity.getId(), entity.getDescription(), entity.getPrice(), entity.getSpeaker(), appointments);
    }

    static AppointmentResource from(AppointmentEntity entity) {
        return new AppointmentResource(entity.id(), entity.beginDate(), entity.endDate(), entity.trainingId(), entity.numberOfParticipants());
    }
}
