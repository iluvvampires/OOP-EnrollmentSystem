package org.example.service;

import org.example.model.Course;
import java.util.ArrayList;

public class CourseRegistration {
    private ArrayList<Course> courseList = new ArrayList<>();


    public void save(Course course) {
        courseList.add(course);
        System.out.println("Course saved successfully!");
    }


    public void displayAll() {
        if (courseList.isEmpty()) {
            System.out.println("No courses registered yet.");
            return;
        }
        for (Course c : courseList) {

            System.out.println(c);
        }
    }

    public void updateCourse(String id, String newName, String newProgram) {
        for (Course c : courseList) {

            if (c.getcourseID().equals(id)) {
                c.setCourseName(newName);
                c.setProgram(newProgram);
                System.out.println("Course " + id + " updated successfully!");
                return; // Exit once found and updated
            }
        }
        System.out.println("Update failed: Course ID " + id + " not found.");
    }

    public void removeCourse(String id) {

        boolean removed = courseList.removeIf(c -> c.getcourseID().equals(id));

        if (removed) {
            System.out.println("Course " + id + " has been removed.");
        } else {
            System.out.println("Remove failed: Course ID " + id + " not found.");
        }
    }
}