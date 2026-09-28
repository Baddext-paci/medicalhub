package com.hospital.model;

/**
 * A Doctor IS A Person (inheritance) and CAN BE displayed (interface).
 */
public class Doctor extends Person implements Displayable {

    private String specialization;

    public Doctor(String id, String name, String specialization, String phone) {
        super(id, name, phone);
        this.specialization = specialization;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    // Required by the Displayable interface.
    @Override
    public void displayInfo() {
        System.out.println("ID: " + getId());
        System.out.println("Name: " + getName());
        System.out.println("Specialization: " + specialization);
        System.out.println("Phone: " + getPhone());
    }
}
