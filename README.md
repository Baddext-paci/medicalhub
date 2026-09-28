# Java Hospital Management System

A small console-based Java project for learning OOP: classes, objects,
constructors, encapsulation, inheritance, ArrayList and exception handling.
Data is kept in memory only (no database), so it resets each time you run it.

## Requirements
- JDK 17 or newer (check with `javac -version`)
- Maven is optional

## Project structure
```
HospitalManagementSystem/
├── pom.xml
├── README.md
└── src/main/java/com/hospital/
    ├── Main.java                              (menu + keyboard input)
    ├── model/
    │   ├── Displayable.java                   (interface: displayInfo())
    │   ├── Person.java                        (parent class: id, name, phone)
    │   ├── Patient.java                       (extends Person, implements Displayable)
    │   ├── Doctor.java                        (extends Person, implements Displayable)
    │   └── Appointment.java
    └── service/
        └── HospitalManagementSystem.java      (ArrayLists + logic)
```

## Compile and run (no Maven)
Run these from the `HospitalManagementSystem` folder.

macOS / Linux:
```
mkdir -p out
javac -d out $(find src -name "*.java")
java -cp out com.hospital.Main
```

Windows (Command Prompt):
```
mkdir out
dir /s /b src\*.java > sources.txt
javac -d out @sources.txt
java -cp out com.hospital.Main
```

## Compile and run (with Maven)
```
mvn compile
java -cp target/classes com.hospital.Main
```

## Sample data
On start the program loads 3 patients (P001-P003), 2 doctors (D001-D002)
and 2 appointments, so you can test right away.

## How information flows
Keyboard -> Main (reads and validates input) -> HospitalManagementSystem
(creates objects, stores them in ArrayLists) -> Main (prints results).

## Future ideas
MySQL, login, roles, medical records, prescriptions, billing, JavaFX GUI.
