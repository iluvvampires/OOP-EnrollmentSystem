package org.example.interfaces;

import org.example.model.Student;
import org.example.exception.DuplicateEntityException;
import java.util.ArrayList;

public interface IStudentService {

    // ✅ Add "throws DuplicateEntityException"
    void addStudent(Student student) throws DuplicateEntityException;

    void updateStudent(String id, String newName, String newProgram);
    void removeStudent(String id);
    Student getStudentById(String id);
    void displayAllStudents();
    boolean studentExists(String id);
    ArrayList<Student> getAllStudents();
}