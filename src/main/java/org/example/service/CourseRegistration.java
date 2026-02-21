package org.example.service;

import org.example.model.Course;
import java.util.ArrayList;

public class CourseRegistration {
    private ArrayList<Course> courseList = new ArrayList<>();

    public void save(Course course) {
        courseList.add(course);
    }

    public void displayAll() {
        for (Course c : courseList) {
            System.out.println(c);
        }
    }

    public void updateCourse(String id, String newName, String newProgram) {
        for (Course c : courseList) {
            if (c.getcourseID().equals(id)) {
                c.setCourseName(newName);
                c.setProgram(newProgram);
            }
        }
    }

    public void removeCourse(String id) {
        courseList.removeIf(c -> c.getcourseID().equals(id));
    }
}