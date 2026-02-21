package org.example.model;

public class Course {
    private String courseID;
    private String courseName;
    private String courseProgram;

    public Course(){
    }
    public Course(String courseID) {

    }

    public Course(String courseID, String name, String program) {
        this.courseID = courseID;
        this.name = name;
        this.program = program;
    }

    public void setCourseID(String courseID){
        this.courseID = courseID;
    }

    public int getCourseID() {
        return this.courseID;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public int getProgram() {
        return program;
    }
    public void setProgram(String program) {
        this.program = program;

    }
}
