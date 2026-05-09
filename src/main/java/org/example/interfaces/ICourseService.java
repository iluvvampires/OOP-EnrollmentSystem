package org.example.interfaces;

import org.example.model.Course;

import java.util.ArrayList;

public interface ICourseService {
    void addCourse(Course course);
    void updateCourse(String id, String newName, String newProgram);
    void removeCourse(String id);
    Course getCourseById(String id);
    void displayAllCourses();
    boolean courseExists(String id);
    // In ICourseService.java
    ArrayList<Course> getAllCourses();
}