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
            System.out.println("[1] Save Student");
            System.out.println("[2] Display Student");
            System.out.println("[3] Update Student");
            System.out.println("[4] Remove Student");
            System.out.println("[5] Exit");

            System.out.print("\nSelect Category: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            if (choice == 5) break;

            switch (choice) {
                case 1:

                    System.out.print("\nEnter Student ID: ");
                    String sId = scanner.nextLine();
                    System.out.print("Enter Student Name: ");
                    String sName = scanner.nextLine();
                    System.out.print("Enter Program: ");
                    String sProg = scanner.nextLine();
                    studentService.saveStudent(new Student(sId, sName, sProg));


                    System.out.print("\nEnter Course ID: ");
                    String cId = scanner.nextLine();
                    System.out.print("Enter Course Name: ");
                    String cName = scanner.nextLine();
                    System.out.print("Program: ");
                    String cProg = scanner.nextLine();
                    courseService.save(new Course(cId, cName, cProg));
                    break;

                case 2:
                    System.out.println("\n--- Student Records ---");
                    studentService.displayAllStudent();
                    System.out.println("--- Course Records ---");
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
            }
        }
    }
}
