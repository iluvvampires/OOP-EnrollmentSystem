package org.example.service;
import org.example.model.Student;
import java.util.ArrayList;

public class StudentRegistration {
    private ArrayList<Student> studentList = new ArrayList<>();

    public void saveStudent(Student student) {
        studentList.add(student);
    }

    public void displayAllStudent() {
        for (Student s : studentList) {
            System.out.println(s.toString());
        }
    }


    public void updateStudent(String id, String newName, String newProgram) {
        for (int i = 0; i < studentList.size(); i++) {
            if (studentList.get(i).getStudentID().equals(id)) {
                studentList.get(i).setStudentName(newName);
                studentList.get(i).setProgram(newProgram);
                System.out.println("Update successful!");
                return;
            }
        }
        System.out.println("Student not found.");
    }


    public void removeStudent(String id) {
        for (int i = 0; i < studentList.size(); i++) {
            if (studentList.get(i).getStudentID().equals(id)) {
                studentList.remove(i);
                System.out.println("Student removed.");
                return;
            }
        }
    }
}
