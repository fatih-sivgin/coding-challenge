/*
 * Project: coding-challenge
 *
 */
package de.sivgin.coding_challenge.trainings.api.service;

import de.sivgin.coding_challenge.jpa.Appointment;
import de.sivgin.coding_challenge.jpa.Training;
import de.sivgin.coding_challenge.trainings.api.io.AppointmentResource;
import de.sivgin.coding_challenge.trainings.api.io.PageableTrainingsResource;
import de.sivgin.coding_challenge.trainings.api.io.TrainingResource;
import lombok.experimental.UtilityClass;
import org.springframework.data.domain.Page;

import java.util.Collections;
import java.util.List;

/**
 * @author fatih
 * @since 14/09/2025
 */
@UtilityClass
class TrainingsMapper {

    // some mappers to prepare response
    static PageableTrainingsResource from(Page<Training> trainings, boolean includeAppointments) {
        List<TrainingResource> trainingsResource = trainings.getContent().stream()
                .map(t -> from(t, includeAppointments))
                .toList();
        return new PageableTrainingsResource(trainings.getTotalElements(), trainingsResource);
    }

    static TrainingResource from(Training entity, boolean includeAppointments) {
        List<AppointmentResource> appointments = Collections.emptyList();
        if(includeAppointments){
            appointments = entity.getAppointments().stream()
                    .map(TrainingsMapper::from)
                    .toList();
        }

        return new TrainingResource(entity.getId(), entity.getDescription(), entity.getPrice(), entity.getSpeaker(), appointments);
    }

    static AppointmentResource from(Appointment entity) {
        return new AppointmentResource(entity.getId(), entity.getBeginDate(), entity.getEndDate(), entity.getTraining().getId(), entity.getNumberOfParticipants());
    }
}
