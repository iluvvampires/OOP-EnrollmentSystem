package org.example.controller;

import org.example.model.Student;
import org.example.service.StudentServiceImpl;
import org.example.service.TuitionServiceImpl;
import org.example.interfaces.IStudentService;
import org.example.interfaces.ITuitionService;

public class AccountingOffice {
    private IStudentService studentService;
    private ITuitionService tuitionService;

    public AccountingOffice() {
        this.studentService = new StudentServiceImpl();
        this.tuitionService = new TuitionServiceImpl();
    }

    public String calculateStudentTuition(String studentId, int units, double discountRate) {
        Student student = studentService.getStudentById(studentId);
        if (student == null) {
            return "[AccountingOffice] ERROR: Student ID " + studentId + " not found!";
        }

        double total = tuitionService.calculateTuitionFee(units, discountRate);
        student.getPayment().calculateTuitionFee(units, discountRate);

        return String.format("[AccountingOffice] Total Tuition for %s: PHP %.2f (Discount: %.0f%%)",
                student.getName(), total, discountRate * 100);
    }

    public String processPayment(String studentId, double amount) {
        Student student = studentService.getStudentById(studentId);
        if (student == null) {
            return "[AccountingOffice] ERROR: Student ID " + studentId + " not found!";
        }

        double oldBalance = student.getPayment().getBalance();
        tuitionService.makePayment(student, amount);
        double newBalance = student.getPayment().getBalance();

        return String.format("[AccountingOffice] Payment of PHP %.2f received from %s.\n" +
                        "                           Previous Balance: PHP %.2f\n" +
                        "                           Remaining Balance: PHP %.2f",
                amount, student.getName(), oldBalance, newBalance);
    }

    public String processPaymentWithType(String studentId, int paymentType, double amount) {
        Student student = studentService.getStudentById(studentId);
        if (student == null) {
            return "[AccountingOffice] ERROR: Student ID " + studentId + " not found!";
        }

        tuitionService.processPaymentType(student, paymentType, amount);

        if (student.getPayment().isFullyPaid()) {
            return "[AccountingOffice] SUCCESS: " + student.getName() + " has fully paid their tuition!";
        }
        return "[AccountingOffice] Payment processed. Remaining balance: PHP " +
                String.format("%.2f", student.getPayment().getBalance());
    }

    public String checkStudentBalance(String studentId) {
        Student student = studentService.getStudentById(studentId);
        if (student == null) {
            return "[AccountingOffice] ERROR: Student ID " + studentId + " not found!";
        }

        if (student.getPayment().isFullyPaid()) {
            return "[AccountingOffice] " + student.getName() + " has NO outstanding balance. (FULLY PAID)";
        }
        return String.format("[AccountingOffice] %s has remaining balance: PHP %.2f",
                student.getName(), student.getPayment().getBalance());
    }

    public String getTuitionDetails(String studentId) {
        Student student = studentService.getStudentById(studentId);
        if (student == null) {
            return "[AccountingOffice] ERROR: Student not found!";
        }

        StringBuilder result = new StringBuilder();
        result.append("\n===== TUITION STATEMENT FOR ").append(student.getName()).append(" =====\n");
        student.getPayment().printTuitionDetails();
        return result.toString();
    }
}