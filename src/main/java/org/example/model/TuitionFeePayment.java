package org.example.model;

import java.text.DecimalFormat;

public class TuitionFeePayment {
    private double pricePerUnit = 1000.00;
    private double balance;
    private double totalTuition;
    private static final DecimalFormat DF = new DecimalFormat("#,##0.00");

    public TuitionFeePayment() {}

    // ========== CALCULATE TUITION FEE ==========
    public double calculateTuitionFee(int units, double discountRate) {
        // Validate inputs
        if (units <= 0) {
            System.out.println("Error: Units must be greater than 0.");
            return 0;
        }

        if (discountRate < 0 || discountRate > 1) {
            System.out.println("Error: Discount rate must be between 0 and 1.");
            return 0;
        }

        double subtotal = units * pricePerUnit;
        double discount = subtotal * discountRate;
        this.totalTuition = subtotal - discount;
        this.balance = this.totalTuition;

        System.out.println("\n📘 TUITION CALCULATION:");
        System.out.println("   Units: " + units);
        System.out.println("   Price per Unit: PHP " + DF.format(pricePerUnit));
        System.out.println("   Subtotal: PHP " + DF.format(subtotal));
        System.out.println("   Discount (" + (discountRate * 100) + "%): PHP " + DF.format(discount));
        System.out.println("   TOTAL TUITION: PHP " + DF.format(this.totalTuition));

        return this.totalTuition;
    }

    // ========== MAKE PAYMENT ==========
    public void makePayment(double amount) {
        if (amount <= 0) {
            System.out.println("Error: Payment amount must be greater than 0.");
            return;
        }

        if (amount > balance) {
            System.out.println("⚠️ Warning: Payment amount exceeds balance.");
            System.out.println("   Amount paid: PHP " + DF.format(amount));
            System.out.println("   Actual balance: PHP " + DF.format(balance));
            System.out.println("   Change returned: PHP " + DF.format(amount - balance));
            this.balance = 0;
            System.out.println("   ✅ Balance cleared!");
        } else if (amount > 0 && amount <= balance) {
            this.balance -= amount;
            System.out.println("✅ Paid: PHP " + DF.format(amount));
            System.out.println("   Remaining balance: PHP " + DF.format(this.balance));
        } else {
            System.out.println("❌ Invalid Payment.");
        }
    }

    // ========== GET REMAINING BALANCE ==========
    public double getBalance() {
        return balance;
    }

    public void getRemainingBalance() {
        if (balance <= 0) {
            System.out.println("✅ Balance: FULLY PAID - PHP 0.00");
        } else {
            System.out.println("💰 Balance: PHP " + DF.format(this.balance));
        }
    }

    // ========== CHECK IF FULLY PAID ==========
    public boolean isFullyPaid() {
        return this.balance <= 0;
    }

    // ========== PRINT TUITION DETAILS ==========
    public void printTuitionDetails() {
        System.out.println("┌─────────────────────────────────┐");
        System.out.println("│       TUITION STATEMENT          │");
        System.out.println("├─────────────────────────────────┤");
        System.out.println("│ Total Tuition: PHP " + String.format("%-16s", DF.format(totalTuition)) + "│");
        if (balance <= 0) {
            System.out.println("│ Balance:       PHP " + String.format("%-16s", "0.00 (PAID)") + "│");
        } else {
            System.out.println("│ Balance:       PHP " + String.format("%-16s", DF.format(balance)) + "│");
        }
        System.out.println("└─────────────────────────────────┘");
    }

    // ========== PROCESS PAYMENT BY TYPE ==========
    public void processPaymentType(int type, double amount) {
        if (balance <= 0) {
            System.out.println("✅ Student already has ZERO balance. No payment needed.");
            return;
        }

        System.out.println("\n💰 PROCESSING PAYMENT...");
        System.out.println("   Current Balance: PHP " + DF.format(balance));

        switch (type) {
            case 1: // FULL PAYMENT
                System.out.println("   Payment Type: FULL PAYMENT");
                if (amount >= balance) {
                    double change = amount - balance;
                    this.balance = 0;
                    System.out.println("   ✅ Full payment received! Account Cleared!");
                    if (change > 0) {
                        System.out.println("   💵 Change: PHP " + DF.format(change));
                    }
                } else {
                    System.out.println("   ❌ Amount is not sufficient for Full Payment.");
                    System.out.println("   Needed: PHP " + DF.format(balance));
                    System.out.println("   Provided: PHP " + DF.format(amount));
                }
                break;

            case 2: // MONTHLY INSTALLMENT (5 months)
                System.out.println("   Payment Type: MONTHLY INSTALLMENT (5 months)");
                double monthlyInstallment = balance / 5;
                System.out.println("   Minimum Monthly Payment: PHP " + DF.format(monthlyInstallment));

                if (amount >= monthlyInstallment) {
                    this.balance -= amount;
                    System.out.println("   ✅ Monthly Payment Processed.");
                    System.out.println("   Paid: PHP " + DF.format(amount));
                    System.out.println("   Remaining: PHP " + DF.format(balance));

                    if (balance > 0) {
                        double nextInstallment = balance / 5;
                        System.out.println("   Next monthly installment: PHP " + DF.format(nextInstallment));
                    }
                } else {
                    System.out.println("   ❌ Amount below minimum monthly payment.");
                    System.out.println("   Minimum required: PHP " + DF.format(monthlyInstallment));
                }
                break;

            case 3: // PARTIAL PAYMENT
                System.out.println("   Payment Type: PARTIAL PAYMENT");
                if (amount > 0 && amount <= balance) {
                    this.balance -= amount;
                    System.out.println("   ✅ Partial Payment of PHP " + DF.format(amount) + " applied.");
                    System.out.println("   Remaining balance: PHP " + DF.format(balance));
                } else if (amount > balance) {
                    System.out.println("   ⚠️ Amount exceeds balance. Processing as full payment...");
                    double change = amount - balance;
                    this.balance = 0;
                    System.out.println("   ✅ Balance cleared!");
                    if (change > 0) {
                        System.out.println("   💵 Change: PHP " + DF.format(change));
                    }
                } else {
                    System.out.println("   ❌ Invalid amount for Partial Payment.");
                }
                break;

            default:
                System.out.println("   ❌ Invalid payment type. Please select 1, 2, or 3.");
        }
    }

    // ========== APPLY SCHOLARSHIP (Bonus Feature) ==========
    public void applyScholarship(String scholarshipType, int units) {
        double discountRate = 0;

        switch (scholarshipType.toLowerCase()) {
            case "president":
                discountRate = 0.50; // 50% discount
                break;
            case "dean":
                discountRate = 0.25; // 25% discount
                break;
            case "athletic":
                discountRate = 0.30; // 30% discount
                break;
            case "academic":
                discountRate = 0.15; // 15% discount
                break;
            default:
                System.out.println("Unknown scholarship type. No discount applied.");
                return;
        }

        double subtotal = units * pricePerUnit;
        double discount = subtotal * discountRate;
        this.totalTuition = subtotal - discount;
        this.balance = this.totalTuition;

        System.out.println("\n🏆 SCHOLARSHIP APPLIED: " + scholarshipType.toUpperCase());
        System.out.println("   Discount Rate: " + (discountRate * 100) + "%");
        System.out.println("   Discount Amount: PHP " + DF.format(discount));
        System.out.println("   Original Total: PHP " + DF.format(subtotal));
        System.out.println("   NEW TOTAL: PHP " + DF.format(this.totalTuition));
    }

    // ========== SETTERS AND GETTERS ==========
    public double getPricePerUnit() {
        return pricePerUnit;
    }

    public void setPricePerUnit(double pricePerUnit) {
        this.pricePerUnit = pricePerUnit;
    }

    public double getTotalTuition() {
        return totalTuition;
    }

    public void setTotalTuition(double totalTuition) {
        this.totalTuition = totalTuition;
        this.balance = totalTuition;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}