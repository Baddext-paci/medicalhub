package com.hospital.model;

/**
 * A Patient IS A Person (inheritance) and CAN BE displayed (interface).
 * "extends" = inherit from a class.  "implements" = keep the promise of an interface.
 */
public class Patient extends Person implements Displayable {

    private int age;
    private String gender;
    private String disease;

    public Patient(String id, String name, int age, String gender, String phone, String disease) {
        super(id, name, phone); // call the parent (Person) constructor
        this.age = age;
        this.gender = gender;
        this.disease = disease;
    }

    public int getAge() {
        return age;
    }

    public String getGender() {
        return gender;
    }

    public String getDisease() {
        return disease;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setDisease(String disease) {
        this.disease = disease;
    }

    // Required by the Displayable interface.
    @Override
    public void displayInfo() {
        System.out.println("ID: " + getId());
        System.out.println("Name: " + getName());
        System.out.println("Age: " + age);
        System.out.println("Gender: " + gender);
        System.out.println("Phone: " + getPhone());
        System.out.println("Disease: " + disease);
    }
}
