/*
 * Project: coding-challenge
 *
 */
package de.sivgin.coding_challenge.trainings.api;

import de.sivgin.coding_challenge.trainings.api.io.PageableTrainingsResource;
import de.sivgin.coding_challenge.trainings.api.service.TrainingsFinderService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.Optional;

/**
 * Spring rest controller to find trainings.
 *
 * @author fatih
 * @since 14/09/2025
 */
@RequiredArgsConstructor
@RestController
public class TrainingsController {

    private final TrainingsFinderService trainingsFinderService;

    /**
     *
     * @param page                the page for pagination
     * @param size                the size of the elements a page should have
     * @param beginDate           to filter over appointments if value exists, should match with endDate
     * @param endDate             to filter over appointments if value exists, should match with endDate
     * @param includeAppointments to set if appointments should be part of result
     * @return a list of trainings with status code 200
     */
    @GetMapping("/api/v1/trainings")
    public ResponseEntity<PageableTrainingsResource> getTrainings(
            @RequestParam(required = false, defaultValue = "0") int page,
            @RequestParam(required = false, defaultValue = "10") int size,
            @RequestParam(required = false) Optional<LocalDate> beginDate,
            @RequestParam(required = false) Optional<LocalDate> endDate,
            @RequestParam(required = false, defaultValue = "false") boolean includeAppointments) {

        // preparation before service call
        PageRequest pageRequest = PageRequest.of(page, size);

        // service call
        PageableTrainingsResource trainings;
        if (beginDate.isPresent() && endDate.isPresent() && !endDate.get().isBefore(beginDate.get())) {
            trainings = trainingsFinderService.findAllTrainingsWithTime(pageRequest, beginDate.get(), endDate.get(), includeAppointments);
        } else {
            trainings = trainingsFinderService.findAllTrainings(pageRequest, includeAppointments);
        }

        // prepare response
        return ResponseEntity.ok(trainings);
    }

}
