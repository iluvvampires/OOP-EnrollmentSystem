package org.example.model;

public class Course {
    private String courseID;
    private String courseName;
    private String program;

    public Course(String courseID, String courseName, String program) {
        this.courseID = courseID;
        this.courseName = courseName;
        this.program = program;
    }
    // getters and setters
    public String getcourseID() {
        return courseID;
    }
    public void setCourseID(String courseID) {
        this.courseID = courseID;
    }

    public String getcourseName() {
        return courseName;
    }
    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    @Override
    public String toString() {
        return "Course ID: " + courseID + " | Name: " + courseName + " | Program: " + program;
    }
}