/*
 * Project: coding-challenge
 *
 */
package de.sivgin.coding_challenge.jpa;

import jakarta.annotation.Nonnull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.UUID;

/**
 * jpa repository for {@link Training}.
 *
 * @author fatih
 * @since 15/09/2025
 */
public interface TrainingRepository extends JpaRepository<Training, UUID> {

    /**
     *
     * @param pageable for paging
     * @return a list of training instances (with appointments)
     */
    @Query("""
            SELECT DISTINCT t
            FROM Training t
            LEFT JOIN FETCH t.appointments
            """)
    Page<Training> findAllWithAppointments(@Nonnull Pageable pageable);

    /**
     *
     * @param beginDate the beginDate to match
     * @param endDate   the endDate to match
     * @param pageable  for paging
     * @return a list of training instances (without appointments)
     */
    @Query("""
            SELECT DISTINCT t
            FROM Training t
            JOIN t.appointments a
            WHERE a.beginDate >= :beginDate
            AND a.endDate <= :endDate
            """)
    Page<Training> findAllBetweenDates(@Nonnull LocalDate beginDate, @Nonnull LocalDate endDate, @Nonnull Pageable pageable);

    /**
     *
     * @param beginDate the beginDate to match
     * @param endDate   the endDate to match
     * @param pageable  for paging
     * @return a list of training instances (with appointments)
     */
    @Query("""
            SELECT DISTINCT t
            FROM Training t
            JOIN FETCH t.appointments a
            WHERE a.beginDate >= :beginDate
            AND a.endDate <= :endDate
            """)
    Page<Training> findAllBetweenDatesWithAppointments(@Nonnull LocalDate beginDate, @Nonnull LocalDate endDate, @Nonnull Pageable pageable);
}
