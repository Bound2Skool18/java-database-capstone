package com.project.back_end.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * Appointment entity
 * Represents a scheduled meeting between a Doctor and a Patient in the clinic
 * system.
 * Includes scheduling metadata and helper methods for UI-friendly date/time
 * representations.
 */
@Entity
public class Appointment {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne(optional = false)
  @NotNull(message = "doctor cannot be null")
  private Doctor doctor;

  @ManyToOne(optional = false)
  @NotNull(message = "patient cannot be null")
  private Patient patient;

  @NotNull(message = "appointment time cannot be null")
  @Future(message = "Appointment time must be in the future")
  private LocalDateTime appointmentTime;

  /**
   * Status of the appointment: 0 = Scheduled, 1 = Completed
   */
  @NotNull(message = "status cannot be null")
  private Integer status; // use Integer to allow validation annotations

  // Constructors
  public Appointment() {
  }

  public Appointment(Doctor doctor, Patient patient, LocalDateTime appointmentTime, Integer status) {
    this.doctor = doctor;
    this.patient = patient;
    this.appointmentTime = appointmentTime;
    this.status = status;
  }

  public Appointment(Long id, Doctor doctor, Patient patient, LocalDateTime appointmentTime, Integer status) {
    this.id = id;
    this.doctor = doctor;
    this.patient = patient;
    this.appointmentTime = appointmentTime;
    this.status = status;
  }

  // Helper / derived values (not persisted)
  @Transient
  public LocalDateTime getEndTime() {
    return appointmentTime != null ? appointmentTime.plusHours(1) : null;
  }

  @Transient
  public LocalDate getAppointmentDate() {
    return appointmentTime != null ? appointmentTime.toLocalDate() : null;
  }

  @Transient
  public LocalTime getAppointmentTimeOnly() {
    return appointmentTime != null ? appointmentTime.toLocalTime() : null;
  }

  // Getters & Setters
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Doctor getDoctor() {
    return doctor;
  }

  public void setDoctor(Doctor doctor) {
    this.doctor = doctor;
  }

  public Patient getPatient() {
    return patient;
  }

  public void setPatient(Patient patient) {
    this.patient = patient;
  }

  public LocalDateTime getAppointmentTime() {
    return appointmentTime;
  }

  public void setAppointmentTime(LocalDateTime appointmentTime) {
    this.appointmentTime = appointmentTime;
  }

  public Integer getStatus() {
    return status;
  }

  public void setStatus(Integer status) {
    this.status = status;
  }
}
