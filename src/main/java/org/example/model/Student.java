package org.example.model;

public class Student extends Person {
    private String program;

    public Student() {
        super();
    }

    public Student(int id, String name, String program) {
        super(id, name); // Passes ID and Name to the Person class constructor
        this.program = program;
    }

    public String getProgram() { return program; }
    public void setProgram(String program) { this.program = program; }

    @Override
    public String toString() {
        // We can access getName() because it's inherited from Person
        return "ID: " + getId() + "\nName: " + getName() + "\nProgram: " + program + "\n";
    }
}