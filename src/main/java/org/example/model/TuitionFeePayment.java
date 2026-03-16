package org.example.model;

import java.text.DecimalFormat; // For formatted output

public class TuitionFeePayment {
    // Fields from UML (Private)
    private double pricePerUnit = 1000.00;
    private double balance;
    private double totalTuition;

    // Use DecimalFormat for currency display
    private static final DecimalFormat DF = new DecimalFormat("#,##0.00");

    // Default Constructor
    public TuitionFeePayment() {}

    // Methods from UML
    public double calculateTuitionFee(int units, double discountRate) {
        // total = (units * price) - discount
        // e.g., if units is 15 and discountRate is 0.10 (10%)
        // totalTuition = (15 * 1000.00) * (1 - 0.10)
        // totalTuition = 15000.00 * 0.9 = 13500.00
        this.totalTuition = (units * pricePerUnit) * (1 - discountRate);
        this.balance = this.totalTuition; // Initial balance is the total tuition
        return this.totalTuition;
    }

    public void makePayment(double amount) {
        if (amount > 0 && amount <= balance) {
            this.balance -= amount;
            System.out.println("Payment of " + DF.format(amount) + " processed.");
        } else {
            System.out.println("Invalid payment amount. Make sure it's positive and not more than the current balance.");
        }
    }

    public void getRemainingBalance() {
        System.out.println("Remaining Balance: " + DF.format(this.balance));
    }

    public boolean isFullyPaid() {
        return this.balance <= 0;
    }

    // Helper method for immediate printing (from UML)
    public void printTuitionDetails() {
        System.out.println("\n--- Tuition Fee Details ---");
        System.out.println("Price per Unit: " + DF.format(pricePerUnit));
        System.out.println("Total Tuition: " + DF.format(totalTuition));
        getRemainingBalance();
        System.out.println("--------------------------");
    }
}