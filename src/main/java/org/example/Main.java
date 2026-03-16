package org.example;

import org.example.model.Student;
import org.example.model.Course;
import org.example.service.StudentRegistration;
import org.example.service.CourseRegistration;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        StudentRegistration studentService = new StudentRegistration();
        CourseRegistration courseService = new CourseRegistration();

        while (true) {
            System.out.println("\n--- ENROLLMENT SYSTEM ---");
            System.out.println("[1] Register Student & Course");
            System.out.println("[2] Display All Records");
            System.out.println("[3] Update Student");
            System.out.println("[4] Remove Student");
            System.out.println("[5] Make Payment");
            System.out.println("[6] Exit");

            System.out.print("\nSelect Choice: ");

            int choice = Integer.parseInt(scanner.nextLine());

            if (choice == 6) {
                System.out.println("Exiting System...");
                break;
            }

            switch (choice) {
                case 1:
                    System.out.print("\nEnter Student ID (Numeric): ");
                    int sId = Integer.parseInt(scanner.nextLine());
                    System.out.print("Enter Student Name: ");
                    String sName = scanner.nextLine();
                    System.out.print("Enter Program: ");
                    String sProg = scanner.nextLine();


                    System.out.print("\nEnter Course ID: ");
                    String cId = scanner.nextLine();
                    System.out.print("Enter Course Name: ");
                    String cName = scanner.nextLine();

                    System.out.print("Units for this course: ");
                    int units = Integer.parseInt(scanner.nextLine()); // Declare units ONCE

                    System.out.print("Enter Discount Rate (e.g., 0.10 for 10%): ");
                    double discount = Double.parseDouble(scanner.nextLine());

                    Student newStudent = new Student(sId, sName, sProg);
                    // Use the object we created above
                    newStudent.getPayment().calculateTuitionFee(units, discount);
                    newStudent.getPayment().printTuitionDetails();

                    // Save everything
                    courseService.save(new Course(cId, cName, sProg));
                    studentService.saveStudent(newStudent);

                    System.out.println("Student registration with payment details complete!");
                    break;

                case 2:
                    System.out.println("\n--- STUDENT RECORDS ---");
                    studentService.displayAllStudent();
                    System.out.println("\n--- COURSE RECORDS ---");
                    courseService.displayAll();
                    break;

                case 3:
                    System.out.print("Enter Student ID to Update (Numeric): ");

                    int upId = Integer.parseInt(scanner.nextLine());

                    System.out.print("New Name: ");
                    String upName = scanner.nextLine();
                    System.out.print("New Program: ");
                    String upProg = scanner.nextLine();


                    studentService.updateStudent(upId, upName, upProg);
                    break;

                case 4:
                    System.out.print("Enter Student ID to Remove (Numeric): ");

                    int remId = Integer.parseInt(scanner.nextLine());


                    studentService.removeStudent(remId);
                    break;

                case 5:
                    System.out.print("\nEnter Student ID to make payment for (Numeric): ");
                    int payId = Integer.parseInt(scanner.nextLine());

                    // First, find the student using the ID
                    Student studentToPayFor = studentService.findStudentById(payId); // Assumes you have findStudentById method

                    if (studentToPayFor != null) {
                        System.out.println("Student Found: " + studentToPayFor.getName());
                        studentToPayFor.getPayment().getRemainingBalance(); // Show current balance

                        if (!studentToPayFor.getPayment().isFullyPaid()) {
                            System.out.print("Enter Payment Amount: ");
                            double amountToPay = Double.parseDouble(scanner.nextLine());

                            // MAKE PAYMENT (UML method)
                            studentToPayFor.getPayment().makePayment(amountToPay);

                            // Provide updated status immediately
                            studentToPayFor.getPayment().getRemainingBalance();
                        } else {
                            System.out.println("Tuition is already fully paid.");
                        }
                    } else {
                        System.out.println("Student with ID " + payId + " not found.");
                    }
                    break;

                case 6: // Updated Exit choice
                    System.out.println("Exiting System...");
                    //

                default:
                    System.out.println("Invalid Choice!");
            }
        }
        scanner.close();
    }
}