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
            System.out.println("[5] Make Payment"); // added make payment for tuition fee payment
            System.out.println("[6] Exit");

            System.out.print("\nSelect Choice: ");
            int choice = Integer.parseInt(scanner.nextLine());

            if (choice == 6) {
                System.out.println("Exiting System...");
                break;
            }

            switch (choice) {
                case 1:
                    System.out.print("\nEnter Student ID: ");
                    String sId = scanner.nextLine();
                    System.out.print("Enter Student Name: ");
                    String sName = scanner.nextLine();
                    System.out.print("Enter Program: ");
                    String sProg = scanner.nextLine();

                    System.out.print("\nEnter Course ID: ");
                    String cId = scanner.nextLine();
                    System.out.print("Enter Course Name: ");
                    String cName = scanner.nextLine();

                    System.out.print("Units for this course: ");
                    int units = Integer.parseInt(scanner.nextLine());

                    System.out.print("Enter Discount Rate (ex. 0.10): ");
                    double discount = Double.parseDouble(scanner.nextLine());


                    Student newStudent = new Student(sId, sName, sProg);


                    newStudent.getPayment().calculateTuitionFee(units, discount);
                    newStudent.getPayment().printTuitionDetails();


                    newStudent.mainTask();

                    courseService.save(new Course(cId, cName, sProg));
                    studentService.saveStudent(newStudent);
                    break;

                case 2:
                    studentService.displayAllStudent();
                    courseService.displayAll();
                    break;

                case 3:
                    System.out.print("Enter Student ID to Update: ");
                    String upId = scanner.nextLine();
                    System.out.print("New Name: ");
                    String upName = scanner.nextLine();
                    System.out.print("New Program: ");
                    String upProg = scanner.nextLine();
                    studentService.updateStudent(upId, upName, upProg);
                    break;

                case 4:
                    System.out.print("Enter Student ID to Remove: ");
                    String remId = scanner.nextLine();
                    studentService.removeStudent(remId);
                    break;

                case 5:
                    System.out.print("\nEnter Student ID for Payment: ");
                    String payId = scanner.nextLine();
                    Student stu = studentService.findStudentById(payId);

                    if (stu != null) {
                        System.out.println("Student Found: " + stu.getName());
                        stu.getPayment().getRemainingBalance();

                        if (!stu.getPayment().isFullyPaid()) {
                            System.out.println("\nSelect Payment Type:");
                            System.out.println("[1] Full Payment");
                            System.out.println("[2] Monthly Installment (5 Months)");
                            System.out.println("[3] Partial Payment");
                            System.out.print("Choice: ");
                            int payType = Integer.parseInt(scanner.nextLine());

                            System.out.print("Enter Amount: ");
                            String input = scanner.nextLine().replace(",", "");
                            double payAmt = Double.parseDouble(input);


                            stu.getPayment().processPaymentType(payType, payAmt);
                            stu.getPayment().getRemainingBalance();
                        } else {
                            System.out.println("Already fully paid.");
                        }
                    } else {
                        System.out.println("Student not found.");
                    }
                    break;

                default:
                    System.out.println("Invalid Choice!");
            }
        }
        scanner.close();
    }
}