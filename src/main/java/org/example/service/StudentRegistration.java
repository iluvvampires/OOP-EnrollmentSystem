package org.example.service;

import org.example.model.Student;
import java.util.ArrayList;

public class StudentRegistration {
    private ArrayList<Student> studentList = new ArrayList<>();

    public void saveStudent(Student student) {
        studentList.add(student);
    }

    public void displayAllStudent() {
        if (studentList.isEmpty()) {
            System.out.println("No student records found.");
            return;
        }
        for (Student s : studentList) {
            System.out.println(s.toString());
        }
    }


    public void updateStudent(int id, String newName, String newProgram) {
        for (Student s : studentList) {

            if (s.getId() == id) {
                s.setName(newName);
                s.setProgram(newProgram);
                System.out.println("Update successful!");
                return;
            }
        }
        System.out.println("Student with ID " + id + " not found.");
    }


    public void removeStudent(int id) {
        for (int i = 0; i < studentList.size(); i++) {
            if (studentList.get(i).getId() == id) {
                studentList.remove(i);
                System.out.println("Student with ID " + id + " removed.");
                return;
            }
        }
        System.out.println("Remove failed: Student not found.");
    }
    public Student findStudentById(int id) {
        for (Student s : studentList) {
            if (s.getId() == id) {
                return s;
            }
        }
        return null; // Returns null if no student matches the ID
    }
}