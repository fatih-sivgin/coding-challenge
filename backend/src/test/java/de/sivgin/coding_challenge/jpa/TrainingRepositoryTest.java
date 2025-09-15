package de.sivgin.coding_challenge.jpa;

import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;


/**
 * jpa test on {@link Training} using {@link TrainingRepository}.
 *
 * @author fatih
 * @since 15/09/2025
 */
@DataJpaTest
class TrainingRepositoryTest {

    @Inject
    protected TestEntityManager testEntityManager;

    @Inject
    private TrainingRepository trainingRepository;



    /* check data.sql file if required */
    @Test
    void shouldFindAll() {
        Pageable pageable = Pageable.unpaged();
        Page<Training> result = trainingRepository.findAll(pageable);

        assertThat(result.getContent()).hasSize(1);
    }

    @Test
    void shouldFindAllWithAppointments() {
        Pageable pageable = Pageable.unpaged();
        Page<Training> result = trainingRepository.findAllWithAppointments(pageable);

        assertThat(result.getContent()).hasSize(1);
    }

    /* check data.sql file if required */
    @Test
    void shouldFindAllBetweenDates() {
        // given
        LocalDate beginDate = LocalDate.of(2025, 9, 18);
        LocalDate endDate = LocalDate.of(2025, 9, 26);
        Pageable pageable = Pageable.unpaged();
        // test
        Page<Training> result = trainingRepository.findAllBetweenDates(beginDate, endDate, pageable);
        // then
        assertThat(result.getContent()).hasSize(1);
    }

    /* check data.sql file if required */
    @Test
    void shouldFindAllBetweenDatesWithAppointments() {
        // given
        LocalDate beginDate = LocalDate.of(2025, 9, 18);
        LocalDate endDate = LocalDate.of(2025, 9, 26);
        Pageable pageable = Pageable.unpaged();
        // test
        Page<Training> result = trainingRepository.findAllBetweenDatesWithAppointments(beginDate, endDate, pageable);
        // then
        assertThat(result.getContent()).hasSize(1);
    }

    @Test
    void shouldSave() {

        // given
        Training training = new Training("description", 19.99f, "speaker");

        // test
        Training result = trainingRepository.saveAndFlush(training);

        // then
        assertThat(result.getId()).isNotNull();
        assertThat(result.getAppointments()).isNotNull();
        assertThat(result.getAppointments()).isEmpty();
    }

    @Test
    void shouldNotSave() {

        // given
        Training training = new Training("description", 19.99f, null);

        // test and then
        assertThatThrownBy(() -> {
            // Code that should throw
            trainingRepository.saveAndFlush(training);
        }).isInstanceOf(DataIntegrityViolationException.class);
    }
}