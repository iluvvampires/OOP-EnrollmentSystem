package org.example.service;

import org.example.interfaces.ITuitionService;
import org.example.model.Student;
import java.text.DecimalFormat;

public class TuitionServiceImpl implements ITuitionService {

    private static final double PRICE_PER_UNIT = 1000.00;
    private static final DecimalFormat DF = new DecimalFormat("#,##0.00");

    // ========== CALCULATE TUITION FEE ==========
    @Override
    public double calculateTuitionFee(int units, double discountRate) {
        if (units <= 0) {
            System.out.println("Error: Units must be greater than 0.");
            return 0;
        }

        if (discountRate < 0 || discountRate > 1) {
            System.out.println("Error: Discount rate must be between 0 and 1.");
            return 0;
        }

        double subtotal = units * PRICE_PER_UNIT;
        double discount = subtotal * discountRate;
        double total = subtotal - discount;

        System.out.println("\n📘 TUITION CALCULATION:");
        System.out.println("   Units: " + units);
        System.out.println("   Price per Unit: PHP " + DF.format(PRICE_PER_UNIT));
        System.out.println("   Subtotal: PHP " + DF.format(subtotal));
        System.out.println("   Discount (" + (discountRate * 100) + "%): PHP " + DF.format(discount));
        System.out.println("   TOTAL TUITION: PHP " + DF.format(total));

        return total;
    }

    // ========== MAKE PAYMENT ==========
    @Override
    public void makePayment(Student student, double amount) {
        if (student == null) {
            System.out.println("Error: Student cannot be null.");
            return;
        }

        if (amount <= 0) {
            System.out.println("Error: Payment amount must be greater than 0.");
            return;
        }

        student.getPayment().makePayment(amount);
    }

    // ========== PROCESS PAYMENT BY TYPE ==========
    @Override
    public void processPaymentType(Student student, int paymentType, double amount) {
        if (student == null) {
            System.out.println("Error: Student cannot be null.");
            return;
        }

        student.getPayment().processPaymentType(paymentType, amount);
    }

    // ========== GET REMAINING BALANCE ==========
    @Override
    public double getRemainingBalance(Student student) {
        if (student == null) {
            System.out.println("Error: Student cannot be null.");
            return 0;
        }
        return student.getPayment().getBalance();
    }

    // ========== CHECK IF FULLY PAID ==========
    @Override
    public boolean isFullyPaid(Student student) {
        if (student == null) {
            return false;
        }
        return student.getPayment().isFullyPaid();
    }

    // ========== GET TUITION STATEMENT ==========

    public String getTuitionStatement(Student student) {
        if (student == null) {
            return "Error: Student not found.";
        }

        StringBuilder sb = new StringBuilder();
        sb.append("\n╔══════════════════════════════════════════════════════════╗\n");
        sb.append("║              TUITION STATEMENT                            ║\n");
        sb.append("╠══════════════════════════════════════════════════════════╣\n");
        sb.append("║ Student: ").append(String.format("%-45s", student.getName())).append("║\n");
        sb.append("║ ID: ").append(String.format("%-52s", student.getId())).append("║\n");
        sb.append("║ Program: ").append(String.format("%-48s", student.getProgram())).append("║\n");
        sb.append("╠══════════════════════════════════════════════════════════╣\n");

        student.getPayment().printTuitionDetails();

        sb.append("╚══════════════════════════════════════════════════════════╝\n");
        return sb.toString();
    }

    // ========== APPLY SCHOLARSHIP ==========

    public double applyScholarship(Student student, String scholarshipType, double currentTotal) {
        if (student == null) {
            System.out.println("Error: Student not found.");
            return currentTotal;
        }

        double discountRate = 0;

        switch (scholarshipType.toLowerCase()) {
            case "president":
                discountRate = 0.50;
                break;
            case "dean":
                discountRate = 0.25;
                break;
            case "athletic":
                discountRate = 0.30;
                break;
            case "academic":
                discountRate = 0.15;
                break;
            default:
                System.out.println("Unknown scholarship type. No discount applied.");
                return currentTotal;
        }

        double discount = currentTotal * discountRate;
        double newTotal = currentTotal - discount;

        System.out.println("\n🏆 SCHOLARSHIP APPLIED: " + scholarshipType.toUpperCase());
        System.out.println("   Discount Rate: " + (discountRate * 100) + "%");
        System.out.println("   Discount Amount: PHP " + DF.format(discount));
        System.out.println("   Original Total: PHP " + DF.format(currentTotal));
        System.out.println("   NEW TOTAL: PHP " + DF.format(newTotal));

        return newTotal;
    }

    // ========== VALIDATE PAYMENT AMOUNT ==========

    public boolean isValidPaymentAmount(double amount) {
        if (amount <= 0) {
            System.out.println("Error: Payment amount must be positive.");
            return false;
        }
        return true;
    }

    // ========== GET PRICE PER UNIT ==========

    public double getPricePerUnit() {
        return PRICE_PER_UNIT;
    }
}