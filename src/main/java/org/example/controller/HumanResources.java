package org.example.controller;

import org.example.model.Instructor;
import org.example.service.InstructorServiceImpl;
import org.example.interfaces.IInstructorService;
import org.example.exception.DuplicateEntityException;

public class HumanResources {
    private IInstructorService instructorService;

    public HumanResources() {
        this.instructorService = new InstructorServiceImpl();
    }

    public String hireInstructor(String id, String name) {
        try {
            Instructor instructor = new Instructor(id, name);
            instructorService.addInstructor(instructor);
            return "[HR] SUCCESS: Instructor " + name + " (ID: " + id + ") hired.";
        } catch (DuplicateEntityException e) {
            return "[HR] ERROR: " + e.getMessage();
        }
    }

    public String viewAllInstructors() {
        StringBuilder result = new StringBuilder("\n===== FACULTY DIRECTORY =====\n");

        // ✅ Now this works - getAllInstructors() exists
        for (Instructor i : instructorService.getAllInstructors()) {
            result.append("ID: ").append(i.getId())
                    .append(" | Name: ").append(i.getName())
                    .append(" | Courses: ").append(i.getCourses()).append("\n");
        }

        if (instructorService.getAllInstructors().isEmpty()) {
            result.append("(No instructors hired yet)\n");
        }
        return result.toString();
    }

    public Instructor findInstructor(String id) {
        return instructorService.getInstructorById(id);
    }

    public String updateInstructorInfo(String id, String newName) {
        Instructor instructor = instructorService.getInstructorById(id);
        if (instructor == null) {
            return "[HR] ERROR: Instructor ID " + id + " not found!";
        }
        instructor.setName(newName);
        return "[HR] SUCCESS: Instructor " + id + " updated to " + newName;
    }

    public String terminateInstructor(String id) {
        Instructor instructor = instructorService.getInstructorById(id);
        if (instructor == null) {
            return "[HR] ERROR: Instructor ID " + id + " not found!";
        }

        // You need to add removeInstructor to your service if you want this
        // instructorService.removeInstructor(id);
        return "[HR] SUCCESS: Instructor " + id + " terminated.";
    }

    public String getInstructorTeachingLoad(String id) {
        Instructor instructor = instructorService.getInstructorById(id);
        if (instructor == null) {
            return "[HR] ERROR: Instructor not found!";
        }
        return "[HR] Instructor " + instructor.getName() + " teaches: " + instructor.getCourses();
    }
}