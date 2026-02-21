package org.example;

import org.example.model.Course;
import org.example.model.Student;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Student s = new Student();

        System.out.print("Enter Student ID: ");
        String ID = scanner.nextLine();

        System.out.print("Enter Student Name: ");
        String name = scanner.nextLine();

        System.out.print("Enter Student Program: ");
        String program = scanner.nextLine();

        s.setID(ID);
        s.setName(name);
        s.setProgram(program);

        System.out.println("\n" + "Student ID: " + s.getID());
        System.out.println("Student Name: " + s.getName());
        System.out.println("Student Program: " + s.getProgram());

        Course c = new Course();

        System.out.print("\n" + "Enter Course ID: ");
        String courseID = scanner.nextLine();

        System.out.print("Enter Course Name: ");
        String courseName = scanner.nextLine();

        System.out.print("Enter Course Program: ");
        String courseProgram = scanner.nextLine();

        c.setCourseID(courseID);
        c.setcourseName(courseName);
        c.setcourseProgram(courseProgram);

        System.out.println("\n" + "Course ID: " + c.getCourseID());
        System.out.println("Course Name: " + c.getcourseName());
        System.out.println("Course Program: " + c.getcourseProgram());

    }
}