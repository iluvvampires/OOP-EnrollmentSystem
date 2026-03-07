package org.example.model;

public class Instructor extends Person {
    private String courses;

    public Instructor(int id, String name, String course) {
        super(id, name);
    }
}