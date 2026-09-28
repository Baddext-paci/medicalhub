package com.hospital.model;

/**
 * Displayable is an INTERFACE.
 * An interface is a promise: any class that "implements" Displayable
 * MUST write its own displayInfo() method.
 * It has no fields and no method bodies here - only the rule.
 */
public interface Displayable {

    // No body here. Each class decides how it displays itself.
    void displayInfo();
}
