package com.hospital.model;

/**
 * An Appointment links ONE patient to ONE doctor.
 * It stores only the IDs; the service class looks up the full objects when needed.
 */
public class Appointment {

    private String appointmentId;
    private String patientId;
    private String doctorId;
    private String date;
    private String time;
    private String reason;

    public Appointment(String appointmentId, String patientId, String doctorId,
                       String date, String time, String reason) {
        this.appointmentId = appointmentId;
        this.patientId = patientId;
        this.doctorId = doctorId;
        this.date = date;
        this.time = time;
        this.reason = reason;
    }

    public String getAppointmentId() {
        return appointmentId;
    }

    public String getPatientId() {
        return patientId;
    }

    public String getDoctorId() {
        return doctorId;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public String getReason() {
        return reason;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    @Override
    public String toString() {
        return "Appointment ID: " + appointmentId
                + "\nPatient ID: " + patientId
                + "\nDoctor ID: " + doctorId
                + "\nDate: " + date
                + "\nTime: " + time
                + "\nReason: " + reason;
    }
}
