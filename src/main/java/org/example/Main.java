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
            System.out.println("[5] Exit");

            System.out.print("\nSelect Choice: ");

            int choice = Integer.parseInt(scanner.nextLine());

            if (choice == 5) {
                System.out.println("Exiting System...");
                break;
            }

            switch (choice) {
                case 1:

                    System.out.print("\nEnter Student ID (Numeric): ");
                    int sId = Integer.parseInt(scanner.nextLine()); // Matches Person(int id)
                    System.out.print("Enter Student Name: ");
                    String sName = scanner.nextLine();
                    System.out.print("Enter Program: ");
                    String sProg = scanner.nextLine();


                    studentService.saveStudent(new Student(sId, sName, sProg));


                    System.out.print("\nEnter Course ID: ");
                    String cId = scanner.nextLine();
                    System.out.print("Enter Course Name: ");
                    String cName = scanner.nextLine();
                    System.out.print("Course Program: ");
                    String cProg = scanner.nextLine();

                    courseService.save(new Course(cId, cName, cProg));
                    System.out.println("\nRegistration Successful!");
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

                default:
                    System.out.println("Invalid Choice!");
            }
        }
        scanner.close();
    }
}