package org.example.service;
import org.example.model.Student;
import java.util.ArrayList;

public class StudentRegistration {
    private ArrayList<Student> studentsList = new ArrayList<>();

    //All service or server layers are all in this, this includes the CRUD.

    //Create
    public void addStudent(Student student){
        studentsList.add(student);
    }

    //Read
    public void displayAll() {
        System.out.println(studentsList);
    }

    //Update
    public void  updateStudent(Student student){

    }

    //Remove

}
