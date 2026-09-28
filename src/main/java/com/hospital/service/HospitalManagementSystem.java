package com.hospital.service;

import com.hospital.model.Appointment;
import com.hospital.model.Doctor;
import com.hospital.model.Patient;

import java.util.ArrayList;

/**
 * This class is the "brain" of the application.
 * It owns the three ArrayLists (our temporary "database") and contains
 * all the logic to add, find and list patients, doctors and appointments.
 * It does NOT read keyboard input or print menus - that is Main's job.
 */
public class HospitalManagementSystem {

    // Data lives in memory only. When the program closes, it is gone.
    private ArrayList<Patient> patients = new ArrayList<>();
    private ArrayList<Doctor> doctors = new ArrayList<>();
    private ArrayList<Appointment> appointments = new ArrayList<>();

    // Counters used to generate IDs like P001, D001, A001.
    private int patientCounter = 0;
    private int doctorCounter = 0;
    private int appointmentCounter = 0;

    // ---------- ID generation ----------

    // String.format("%03d", 5) gives "005".
    private String nextPatientId() {
        patientCounter++;
        return "P" + String.format("%03d", patientCounter);
    }

    private String nextDoctorId() {
        doctorCounter++;
        return "D" + String.format("%03d", doctorCounter);
    }

    private String nextAppointmentId() {
        appointmentCounter++;
        return "A" + String.format("%03d", appointmentCounter);
    }

    // ---------- Patients ----------

    public Patient registerPatient(String name, int age, String gender, String phone, String disease) {
        Patient patient = new Patient(nextPatientId(), name, age, gender, phone, disease);
        patients.add(patient);
        return patient;
    }

    public ArrayList<Patient> getAllPatients() {
        return patients;
    }

    // Returns the patient, or null if no patient has that ID.
    public Patient findPatientById(String id) {
        for (Patient patient : patients) {
            if (patient.getId().equalsIgnoreCase(id)) {
                return patient;
            }
        }
        return null;
    }

    // ---------- Doctors ----------

    public Doctor addDoctor(String name, String specialization, String phone) {
        Doctor doctor = new Doctor(nextDoctorId(), name, specialization, phone);
        doctors.add(doctor);
        return doctor;
    }

    public ArrayList<Doctor> getAllDoctors() {
        return doctors;
    }

    public Doctor findDoctorById(String id) {
        for (Doctor doctor : doctors) {
            if (doctor.getId().equalsIgnoreCase(id)) {
                return doctor;
            }
        }
        return null;
    }

    // ---------- Appointments ----------

    /**
     * Creates an appointment only if BOTH the patient and doctor exist.
     * Throws an exception with a clear message otherwise.
     */
    public Appointment bookAppointment(String patientId, String doctorId,
                                       String date, String time, String reason) {
        Patient patient = findPatientById(patientId);
        if (patient == null) {
            throw new IllegalArgumentException("Patient with ID " + patientId + " does not exist.");
        }
        Doctor doctor = findDoctorById(doctorId);
        if (doctor == null) {
            throw new IllegalArgumentException("Doctor with ID " + doctorId + " does not exist.");
        }

        // Use the real IDs from the found objects (fixes lower/upper case typing).
        Appointment appointment = new Appointment(
                nextAppointmentId(), patient.getId(), doctor.getId(), date, time, reason);
        appointments.add(appointment);
        return appointment;
    }

    public ArrayList<Appointment> getAllAppointments() {
        return appointments;
    }

    // ---------- Sample data ----------

    /** Adds a few records so the program can be tested immediately. */
    public void loadSampleData() {
        registerPatient("Jean Claude", 24, "Male", "0788123456", "Malaria");
        registerPatient("Marie Uwase", 31, "Female", "0788234567", "Flu");
        registerPatient("Eric Mugisha", 45, "Male", "0788345678", "High blood pressure");

        addDoctor("Dr. Alice", "General Medicine", "0788001122");
        addDoctor("Dr. Patrick", "Cardiology", "0788003344");

        bookAppointment("P001", "D001", "28/09/2026", "10:00", "Medical checkup");
        bookAppointment("P003", "D002", "29/09/2026", "14:30", "Heart follow-up");
    }
}
