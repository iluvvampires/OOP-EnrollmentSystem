package org.example;

public class Student {
    private String ID;
    private String name;
    private String program;

    public Student() {
    }

    public Student(String ID, String name, String program) {
        this.ID = ID;
        this.name = name;
        this.program = program;
    }

    public String getID(){
        return ID;
    }

    public void setID(String ID){
        this.ID = ID;
    }

    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }

    public void setProgram(String program){
        this.program = program;
    }

    public String getProgram(){
        return program;
    }
}