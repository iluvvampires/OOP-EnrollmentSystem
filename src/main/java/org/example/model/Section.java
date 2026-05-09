package org.example.model;

import java.util.ArrayList;

public class Section {
    private String sectionName;
    private int maxCapacity;
    private ArrayList<Student> enrolledStudents;
    private Instructor instructor;

    // Constructor
    public Section(String sectionName, int maxCapacity) {
        this.sectionName = sectionName;
        this.maxCapacity = maxCapacity;
        this.enrolledStudents = new ArrayList<>();
        this.instructor = null;
    }

    // ========== GETTERS ==========
    public String getSectionName() {
        return sectionName;
    }

    public int getMaxCapacity() {
        return maxCapacity;
    }

    public ArrayList<Student> getEnrolledStudents() {
        return enrolledStudents;
    }

    public Instructor getInstructor() {
        return instructor;
    }

    // ========== SETTERS ==========
    public void setSectionName(String sectionName) {
        this.sectionName = sectionName;
    }

    // ✅ ADD THIS METHOD - This fixes your error!
    public void setMaxCapacity(int maxCapacity) {
        if (maxCapacity < enrolledStudents.size()) {
            System.out.println("Warning: Cannot set capacity below current enrollment (" +
                    enrolledStudents.size() + " students)!");
            return;
        }
        this.maxCapacity = maxCapacity;
    }

    public void setInstructor(Instructor instructor) {
        this.instructor = instructor;
    }

    // ========== UTILITY METHODS ==========
    public int getAvailableSeats() {
        return maxCapacity - enrolledStudents.size();
    }

    public boolean isFull() {
        return enrolledStudents.size() >= maxCapacity;
    }

    public void addStudent(Student student) {
        if (!isFull() && !enrolledStudents.contains(student)) {
            enrolledStudents.add(student);
            System.out.println("Student " + student.getName() + " added to " + sectionName);
        } else if (isFull()) {
            System.out.println("Section " + sectionName + " is full!");
        } else {
            System.out.println("Student " + student.getName() + " already enrolled in " + sectionName);
        }
    }

    public boolean removeStudent(Student student) {
        boolean removed = enrolledStudents.remove(student);
        if (removed) {
            System.out.println("Student " + student.getName() + " removed from " + sectionName);
        }
        return removed;
    }

    @Override
    public String toString() {
        return "Section: " + sectionName + " | Enrolled: " + enrolledStudents.size() + "/" + maxCapacity;
    }
}