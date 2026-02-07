package org.example;

public class Course {
    private String courseID;
    private String courseName;
    private String courseProgram;

    public Course(){
    }

    public Course(String courseID, String courseName, String courseProgram) {
        this.courseID = courseID;
        this.courseName = courseName;
        this.courseProgram = courseProgram;

    }

    public String getCourseID(){
        return courseID;
    }

    public void setCourseID(String courseID) {
        this.courseID = courseID;
    }

    public String getcourseName(){
        return courseName;
    }

    public void setcourseName(String courseName){
        this.courseName = courseName;
    }

    public String getcourseProgram() {
        return courseProgram;

    }

    public void setcourseProgram(String courseProgram) {
        this.courseProgram = courseProgram;
    }
}
