package com.hospital.model;

/**
 * Person is a PARENT (base) class.
 * Patient and Doctor both "extend" it, so they inherit these shared fields
 * (id, name, phone) instead of repeating the same code twice.
 * This is basic INHERITANCE.
 */
public class Person {

    // private = ENCAPSULATION: outside code cannot touch these directly.
    private String id;
    private String name;
    private String phone;

    // Constructor: runs when we create a new object with "new".
    public Person(String id, String name, String phone) {
        this.id = id;
        this.name = name;
        this.phone = phone;
    }

    // Getters let other classes READ the values.
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    // Setters let other classes CHANGE the values (we can add checks here).
    public void setName(String name) {
        this.name = name;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
