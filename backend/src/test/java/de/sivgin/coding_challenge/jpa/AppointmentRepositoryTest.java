package de.sivgin.coding_challenge.jpa;

import jakarta.inject.Inject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.dao.DataIntegrityViolationException;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;


/**
 * jpa test on {@link Appointment} using {@link AppointmentRepository}.
 *
 * @author fatih
 * @since 15/09/2025
 */
@DataJpaTest
class AppointmentRepositoryTest {

    @Inject
    protected TestEntityManager testEntityManager;

    @Inject
    private AppointmentRepository appointmentRepository;

    private Training training;

    @BeforeEach
    void beforeEach() {
        training = new Training("description", 19.99f, "speaker");
        testEntityManager.persist(training);
    }

    /* check data.sql file if required */
    @Test
    void shouldFindExistingEntries() {
        List<Appointment> result = appointmentRepository.findAll();

        assertThat(result).hasSize(2);
    }

    @Test
    void shouldSave() {

        // given
        Appointment appointment = new Appointment(LocalDate.now(), LocalDate.now(), 10, training);

        // test
        Appointment result = appointmentRepository.saveAndFlush(appointment);

        // then
        assertThat(result.getId()).isNotNull();
    }

    @Test
    void shouldNotSave() {

        // given
        Appointment appointment = new Appointment(LocalDate.now(), LocalDate.now(), 10, null);

        // test and then
        assertThatThrownBy(() -> {
            // Code that should throw
            appointmentRepository.saveAndFlush(appointment);
        }).isInstanceOf(DataIntegrityViolationException.class);
    }
}