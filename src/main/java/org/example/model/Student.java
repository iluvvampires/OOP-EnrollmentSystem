package org.example.model;

public class Student extends Person {
    private String program;
    private TuitionFeePayment payment;

    public Student(String id, String name, String program) {
        super(id, name);
        this.program = program;
        this.payment = new TuitionFeePayment();
    }

    public String getProgram() {
        return program;
    }
    public void setProgram(String program) {
        this.program = program;
    }

    public TuitionFeePayment getPayment() {
        return payment;
    }

    @Override
    public void mainTask() {
        System.out.println("Main Task: Studying for " + program);
    }
}