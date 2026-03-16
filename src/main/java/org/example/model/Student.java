package org.example.model;

public class Student extends Person {
    private String program;
    private TuitionFeePayment payment; // Composition!

    public Student(int id, String name, String program) {
        super(id, name);
        this.program = program;
        this.payment = new TuitionFeePayment(); // Initialize the payment object
    }
    public void setProgram(String program) {
        this.program = program;
    }

    public TuitionFeePayment getPayment() {
        return payment;
    }

    // Override toString to include program and payment status for clarity
    @Override
    public String toString() {
        return "Student ID: " + getId() + ", Name: " + getName() + ", Program: " + program +
                ", Payment Status: " + (payment.isFullyPaid() ? "Paid" : "Pending Balance");
    }
}