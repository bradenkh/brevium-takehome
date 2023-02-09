package org.example;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Appointment {

    private int doctorId;
    private int personId;
    private LocalDateTime appointmentTime;
    private boolean isNewPatientAppointment;

    public int getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(int doctorId) {
        this.doctorId = doctorId;
    }

    public int getPersonId() {
        return personId;
    }

    public void setPersonId(int personId) {
        this.personId = personId;
    }

    public LocalDateTime getAppointmentTime() {
        return appointmentTime;
    }

    public LocalDate getAppointmentLocalDate() {
        return LocalDate.from(appointmentTime);
    }

    public void setAppointmentTime(LocalDateTime appointmentTime) {
        this.appointmentTime = appointmentTime;
    }

    @JsonProperty
    public void setAppointmentTime(String str) {
        setAppointmentTime(LocalDateTime.parse(str, DateTimeFormatter.ISO_DATE_TIME));
    }

    public boolean getIsNewPatientAppointment() {
        return isNewPatientAppointment;
    }

    public void SetIsNewPatientAppointment(boolean newPatientAppointment) {
        isNewPatientAppointment = newPatientAppointment;
    }

    public Appointment() {    }
    public Appointment(int doctorId, int personId, LocalDateTime appointmentTime, boolean isNewPatientAppointment) {
        this.doctorId = doctorId;
        this.personId = personId;
        this.appointmentTime = appointmentTime;
        this.isNewPatientAppointment = isNewPatientAppointment;
    }
}
