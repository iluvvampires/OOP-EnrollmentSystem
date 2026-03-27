package org.example.service;

import org.example.model.Course;
import java.util.ArrayList;

public class CourseRegistration {
    private ArrayList<Course> courseList = new ArrayList<>();

    public void save(Course course) {
        courseList.add(course);
        System.out.println("Course registered: " + course.getcourseName());
    }

    public void displayAll() {
        if (courseList.isEmpty()) {
            System.out.println("No courses available.");
            return;
        }
        System.out.println("--- Available Courses ---");
        for (Course c : courseList) {
            System.out.println(c.toString());
        }
    }

    public Course findCourseById(String id) {
        for (Course c : courseList) {
            if (c.getcourseID().equalsIgnoreCase(id)) {
                return c;
            }
        }
        return null;
    }
}