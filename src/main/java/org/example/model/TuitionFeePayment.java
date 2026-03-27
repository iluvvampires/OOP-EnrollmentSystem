package org.example.model;
import java.text.DecimalFormat;

public class TuitionFeePayment {
    private double pricePerUnit = 1000.00;
    private double balance;
    private double totalTuition;
    private static final DecimalFormat DF = new DecimalFormat("#,##0.00");

    public TuitionFeePayment() {}

    public double calculateTuitionFee(int units, double discountRate) {
        this.totalTuition = (units * pricePerUnit) * (1 - discountRate);
        this.balance = this.totalTuition;
        return this.totalTuition;
    }

    public void makePayment(double amount) {
        if (amount > 0 && amount <= balance) {
            this.balance -= amount;
            System.out.println("Paid: " + DF.format(amount));
        } else {
            System.out.println("Invalid Payment.");
        }
    }

    public void getRemainingBalance() {
        System.out.println("Balance: PHP " + DF.format(this.balance));
    }

    public boolean isFullyPaid() {
        return this.balance <= 0;
    }

    public void printTuitionDetails() {
        System.out.println("Total: " + DF.format(totalTuition));
        getRemainingBalance();
    }
    public void processPaymentType(int type, double amount) {
        switch (type) {
            case 1:
                if (amount >= balance) {
                    this.balance = 0;
                    System.out.println("Full Payment Received. Account Cleared!");
                } else {
                    System.out.println("Amount is not sufficient for Full Payment. Please complete the current balance: " + DF.format(balance));
                }
                break;
            case 2:
                double monthlyInstallment = balance / 5;
                if (amount >= monthlyInstallment) {
                    this.balance -= amount;
                    System.out.println("Monthly Payment Processed. Remaining: " + DF.format(balance));
                } else {
                    System.out.println("Minimum monthly payment is: " + DF.format(monthlyInstallment));
                }
                break;
            case 3:
                if (amount > 0 && amount <= balance) {
                    this.balance -= amount;
                    System.out.println("Partial Payment of " + DF.format(amount) + " applied.");
                } else {
                    System.out.println("Invalid amount for Partial Payment.");
                }
                break;
        }
    }
}