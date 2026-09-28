package com.hospital;

import com.hospital.model.Appointment;
import com.hospital.model.Displayable;
import com.hospital.model.Doctor;
import com.hospital.model.Patient;
import com.hospital.service.HospitalManagementSystem;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Main is the entry point. It shows the menu, reads what the user types,
 * and calls the HospitalManagementSystem to do the real work.
 */
public class Main {

    private static final Scanner scanner = new Scanner(System.in);
    private static final HospitalManagementSystem hospital = new HospitalManagementSystem();

    public static void main(String[] args) {
        hospital.loadSampleData();

        boolean running = true;
        while (running) {
            printMenu();
            int choice = readInt("Enter your choice: ");

            switch (choice) {
                case 1:
                    registerPatient();
                    break;
                case 2:
                    viewPatients();
                    break;
                case 3:
                    searchPatient();
                    break;
                case 4:
                    addDoctor();
                    break;
                case 5:
                    viewDoctors();
                    break;
                case 6:
                    bookAppointment();
                    break;
                case 7:
                    viewAppointments();
                    break;
                case 8:
                    System.out.println("\nThank you for using the system. Goodbye!");
                    running = false;
                    break;
                default:
                    System.out.println("\nInvalid choice. Please enter a number from 1 to 8.");
            }
        }
        scanner.close();
    }

    // ---------- Menu ----------

    private static void printMenu() {
        System.out.println();
        System.out.println("1. Register Patient");
        System.out.println("2. View All Patients");
        System.out.println("3. Search Patient");
        System.out.println("4. Add Doctor");
        System.out.println("5. View All Doctors");
        System.out.println("6. Book Appointment");
        System.out.println("7. View All Appointments");
        System.out.println("8. Exit");
        System.out.println();
    }

    // ---------- Menu actions ----------

    private static void registerPatient() {
        System.out.println("\n--- Register Patient ---");
        String name = readText("Enter patient name: ");
        int age = readAge("Enter age: ");
        String gender = readText("Enter gender: ");
        String phone = readText("Enter phone: ");
        String disease = readText("Enter disease: ");

        Patient patient = hospital.registerPatient(name, age, gender, phone, disease);
        System.out.println("\nPatient registered successfully!");
        System.out.println("Patient ID: " + patient.getId());
    }

    private static void viewPatients() {
        ArrayList<Patient> patients = hospital.getAllPatients();
        System.out.println("\n========== PATIENTS ==========");
        if (patients.isEmpty()) {
            System.out.println("No patients registered yet.");
            return;
        }
        printAll(patients);
    }

    private static void searchPatient() {
        String id = readText("Enter Patient ID: ");
        Patient patient = hospital.findPatientById(id);

        if (patient == null) {
            System.out.println("No patient found with ID " + id + ".");
        } else {
            System.out.println();
            patient.displayInfo();
        }
    }

    private static void addDoctor() {
        System.out.println("\n--- Add Doctor ---");
        String name = readText("Enter doctor name: ");
        String specialization = readText("Enter specialization: ");
        String phone = readText("Enter phone: ");

        Doctor doctor = hospital.addDoctor(name, specialization, phone);
        System.out.println("\nDoctor added successfully!");
        System.out.println("Doctor ID: " + doctor.getId());
    }

    private static void viewDoctors() {
        ArrayList<Doctor> doctors = hospital.getAllDoctors();
        System.out.println("\n========== DOCTORS ==========");
        if (doctors.isEmpty()) {
            System.out.println("No doctors added yet.");
            return;
        }
        printAll(doctors);
    }

    private static void bookAppointment() {
        System.out.println("\n--- Book Appointment ---");

        if (hospital.getAllPatients().isEmpty() || hospital.getAllDoctors().isEmpty()) {
            System.out.println("You need at least one patient and one doctor first.");
            return;
        }

        // Show short lists so the user knows which IDs exist.
        System.out.println("Available patients:");
        for (Patient patient : hospital.getAllPatients()) {
            System.out.println("  " + patient.getId() + " - " + patient.getName());
        }
        String patientId = readText("Enter Patient ID: ");

        System.out.println("Available doctors:");
        for (Doctor doctor : hospital.getAllDoctors()) {
            System.out.println("  " + doctor.getId() + " - " + doctor.getName()
                    + " (" + doctor.getSpecialization() + ")");
        }
        String doctorId = readText("Enter Doctor ID: ");

        String date = readText("Enter date (dd/mm/yyyy): ");
        String time = readText("Enter time (HH:mm): ");
        String reason = readText("Enter reason: ");

        // try/catch = EXCEPTION HANDLING. The service throws an error if an ID is wrong.
        try {
            Appointment appointment = hospital.bookAppointment(patientId, doctorId, date, time, reason);
            System.out.println("\nAppointment booked successfully!");
            System.out.println("Appointment ID: " + appointment.getAppointmentId());
        } catch (IllegalArgumentException e) {
            System.out.println("\nCould not book appointment: " + e.getMessage());
        }
    }

    private static void viewAppointments() {
        ArrayList<Appointment> appointments = hospital.getAllAppointments();
        System.out.println("\n========== APPOINTMENTS ==========");
        if (appointments.isEmpty()) {
            System.out.println("No appointments booked yet.");
            return;
        }
        for (Appointment appointment : appointments) {
            Patient patient = hospital.findPatientById(appointment.getPatientId());
            Doctor doctor = hospital.findDoctorById(appointment.getDoctorId());

            System.out.println();
            System.out.println("Appointment ID: " + appointment.getAppointmentId());
            System.out.println("Patient: " + patient.getName() + " (" + patient.getId() + ")");
            System.out.println("Doctor: " + doctor.getName() + " (" + doctor.getId() + ")");
            System.out.println("Date: " + appointment.getDate());
            System.out.println("Time: " + appointment.getTime());
            System.out.println("Reason: " + appointment.getReason());
        }
    }

    // ---------- Shared helper using the interface ----------

    /**
     * Works for ANY list of objects that implement Displayable
     * (patients, doctors...). Java calls the correct displayInfo() for each one.
     * This is called POLYMORPHISM.
     */
    private static void printAll(ArrayList<? extends Displayable> items) {
        for (Displayable item : items) {
            System.out.println();
            item.displayInfo();
        }
    }

    // ---------- Input helpers (validation) ----------

    // Keeps asking until the user types a non-empty line.
    private static String readText(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            if (!input.isEmpty()) {
                return input;
            }
            System.out.println("This field cannot be empty. Please try again.");
        }
    }

    // Keeps asking until the user types a valid whole number.
    private static int readInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }
    }

    // Like readInt, but also checks the age is realistic.
    private static int readAge(String prompt) {
        while (true) {
            int age = readInt(prompt);
            if (age >= 0 && age <= 120) {
                return age;
            }
            System.out.println("Age must be between 0 and 120.");
        }
    }
}
