package org.example.interfaces;

import org.example.model.Student;

public interface ITuitionService {
    double calculateTuitionFee(int units, double discountRate);
    void makePayment(Student student, double amount);
    void processPaymentType(Student student, int type, double amount);
    double getRemainingBalance(Student student);
    boolean isFullyPaid(Student student);
}