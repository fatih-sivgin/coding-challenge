package de.sivgin.coding_challenge.jpa;

import jakarta.annotation.Nonnull;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

/**
 * Representation of an appointment on storage level.
 *
 * @author fatih
 * @since 14/09/2025
 */
@ToString(of = {"id", "beginDate", "endDate"})
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table // TODO add index on beginDate and endDate
@Entity
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Setter
    @Basic(optional = false)
    private LocalDate beginDate;

    @Setter
    @Basic(optional = false)
    private LocalDate endDate;

    @Setter
    @Basic(optional = false)
    private int numberOfParticipants;

    // here the foreign key is defined
    @Setter
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "training_id" /* here the foreign key is defined, so appointment owns it */)
    private Training training;

    /**
     * Constructor to create a new instance of appointment
     */
    public Appointment(@Nonnull LocalDate beginDate, @Nonnull LocalDate endDate, int numberOfParticipants, @Nonnull Training training) {
        this.beginDate = beginDate;
        this.endDate = endDate;
        this.numberOfParticipants = numberOfParticipants;
        this.training = training;
    }
}
