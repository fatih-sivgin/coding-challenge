/*
 * Project: coding-challenge
 *
 * Copyright © 2025 Vilua Healthcare GmbH
 */
package de.sivgin.coding_challenge.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

/**
 * jpa repository for {@link Appointment}
 *
 * @author fatih
 * @since 15/09/2025
 */
public interface AppointmentRepository extends JpaRepository<Appointment, UUID> {

}
