package org.example.model;

public class Course {
    private String courseID;
    private String courseName;
    private String program;

    // Constructor
    public Course(String courseID, String courseName, String program) {
        this.courseID = courseID;
        this.courseName = courseName;
        this.program = program;
    }

    // ========== GETTERS ==========
    public String getCourseID() {
        return courseID;
    }

    public String getCourseName() {
        return courseName;
    }

    public String getProgram() {
        return program;
    }

    // ========== SETTERS ==========
    public void setCourseID(String courseID) {
        this.courseID = courseID;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public void setProgram(String program) {
        this.program = program;
    }

    @Override
    public String toString() {
        return "Course ID: " + courseID + " | Name: " + courseName + " | Program: " + program;
    }
}