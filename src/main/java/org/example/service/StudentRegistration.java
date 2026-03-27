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
            System.out.println("ID: " + s.getId() + " | Name: " + s.getName() + " | Program: " + s.getProgram());
            s.mainTask();
        }
    }

    public Student findStudentById(String id) {
        for (Student s : studentList) {
            if (s.getId().equalsIgnoreCase(id)) {
                return s;
            }
        }
        return null;
    }

    public void removeStudent(String id) {
        boolean removed = studentList.removeIf(s -> s.getId().equalsIgnoreCase(id));
        if (removed) {
            System.out.println("Student " + id + " removed.");
        } else {
            System.out.println("Student not found.");
        }
    }
    public void updateStudent(String id, String newName, String newProgram) {
        for (Student s : studentList) {
            if (s.getId().equalsIgnoreCase(id)) {
                s.setName(newName);
                s.setProgram(newProgram);
                System.out.println("Update successful for Student ID: " + id);
                return;
            }
        }
        System.out.println("Update failed: Student with ID " + id + " not found.");
    }
}