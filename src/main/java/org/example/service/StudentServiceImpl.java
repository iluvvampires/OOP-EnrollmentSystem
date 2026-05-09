package org.example.service;

import org.example.interfaces.IStudentService;
import org.example.model.Student;
import org.example.exception.DuplicateEntityException;
import java.util.ArrayList;

public class StudentServiceImpl implements IStudentService {

    private ArrayList<Student> studentList = new ArrayList<>();

    @Override
    public void addStudent(Student student) throws DuplicateEntityException {
        if (studentExists(student.getId())) {
            throw new DuplicateEntityException("Student with ID " + student.getId() + " already exists!");
        }
        studentList.add(student);
        System.out.println("Student added: " + student.getName());
    }

    @Override
    public void updateStudent(String id, String newName, String newProgram) {
        for (Student s : studentList) {
            if (s.getId().equalsIgnoreCase(id)) {
                s.setName(newName);
                s.setProgram(newProgram);
                System.out.println("Student updated: " + id);
                return;
            }
        }
        System.out.println("Student not found.");
    }

    @Override
    public void removeStudent(String id) {
        boolean removed = studentList.removeIf(s -> s.getId().equalsIgnoreCase(id));
        if (removed) {
            System.out.println("Student removed: " + id);
        } else {
            System.out.println("Student not found.");
        }
    }

    @Override
    public Student getStudentById(String id) {
        for (Student s : studentList) {
            if (s.getId().equalsIgnoreCase(id)) {
                return s;
            }
        }
        return null;
    }

    @Override
    public void displayAllStudents() {
        if (studentList.isEmpty()) {
            System.out.println("No students found.");
            return;
        }
        System.out.println("\n===== ALL STUDENTS =====");
        for (Student s : studentList) {
            System.out.println("ID: " + s.getId() + " | Name: " + s.getName() + " | Program: " + s.getProgram());
        }
    }

    @Override
    public boolean studentExists(String id) {
        return getStudentById(id) != null;
    }

    // ✅ ADD THIS METHOD
    @Override
    public ArrayList<Student> getAllStudents() {
        return studentList;
    }
}