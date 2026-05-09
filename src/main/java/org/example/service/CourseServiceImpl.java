package org.example.service;

import org.example.interfaces.ICourseService;
import org.example.model.Course;
import org.example.exception.DuplicateEntityException;
import java.util.ArrayList;

public class CourseServiceImpl implements ICourseService {

    private ArrayList<Course> courseList = new ArrayList<>();

    @Override
    public void addCourse(Course course) {  // ✅ No "throws"
        if (courseExists(course.getCourseID())) {
            System.out.println("Error: Course with ID " + course.getCourseID() + " already exists!");
            return;  // Just return, don't throw
        }
        courseList.add(course);
        System.out.println("Course added: " + course.getCourseName());
    }

    @Override
    public void updateCourse(String id, String newName, String newProgram) {
        for (Course c : courseList) {
            if (c.getCourseID().equalsIgnoreCase(id)) {
                c.setCourseName(newName);
                System.out.println("Course updated: " + id);
                return;
            }
        }
        System.out.println("Course not found.");
    }

    @Override
    public void removeCourse(String id) {
        boolean removed = courseList.removeIf(c -> c.getCourseID().equalsIgnoreCase(id));
        if (removed) {
            System.out.println("Course removed: " + id);
        } else {
            System.out.println("Course not found.");
        }
    }

    @Override
    public Course getCourseById(String id) {
        for (Course c : courseList) {
            if (c.getCourseID().equalsIgnoreCase(id)) {
                return c;
            }
        }
        return null;
    }

    @Override
    public void displayAllCourses() {
        if (courseList.isEmpty()) {
            System.out.println("No courses found.");
            return;
        }
        System.out.println("\n===== ALL COURSES =====");
        for (Course c : courseList) {
            System.out.println(c.toString());
        }
    }

    @Override
    public boolean courseExists(String id) {
        return getCourseById(id) != null;
    }

    @Override
    public ArrayList<Course> getAllCourses() {
        return courseList;
    }
}