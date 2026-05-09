package org.example.model;

import java.util.ArrayList;

public class Department {
    private String departmentName;
    private ArrayList<Section> sections;

    public Department(String departmentName) {
        this.departmentName = departmentName;
        this.sections = new ArrayList<>();
    }

    public String getDepartmentName() { return departmentName; }
    public ArrayList<Section> getSections() { return sections; }
    public void addSection(Section section) { sections.add(section); }

    public void displayHierarchy() {
        System.out.println("Department: " + departmentName);
        for (Section s : sections) {
            System.out.println("  " + s);
            if (s.getInstructor() != null) {
                System.out.println("    Instructor: " + s.getInstructor().getName());
            }
            for (Student stu : s.getEnrolledStudents()) {
                System.out.println("      Student: " + stu.getName() + " (" + stu.getId() + ")");
            }
        }
    }
}