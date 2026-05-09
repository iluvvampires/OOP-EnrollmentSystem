package org.example.service;

import org.example.interfaces.IInstructorService;
import org.example.model.Instructor;
import org.example.model.Section;
import org.example.exception.DuplicateEntityException;
import java.util.ArrayList;

public class InstructorServiceImpl implements IInstructorService {

    private ArrayList<Instructor> instructorList = new ArrayList<>();

    @Override
    public void addInstructor(Instructor instructor) throws DuplicateEntityException {
        if (instructorExists(instructor.getId())) {
            throw new DuplicateEntityException("Instructor with ID " + instructor.getId() + " already exists!");
        }
        instructorList.add(instructor);
        System.out.println("Instructor added: " + instructor.getName());
    }

    @Override
    public void assignInstructorToSection(Instructor instructor, Section section) {
        if (instructor == null) {
            System.out.println("Error: Instructor not found!");
            return;
        }
        if (section == null) {
            System.out.println("Error: Section not found!");
            return;
        }

        section.setInstructor(instructor);
        instructor.addCourse(section.getSectionName());
        System.out.println("Instructor " + instructor.getName() + " assigned to section: " + section.getSectionName());
    }

    @Override
    public Instructor getInstructorById(String id) {
        for (Instructor instructor : instructorList) {
            if (instructor.getId().equalsIgnoreCase(id)) {
                return instructor;
            }
        }
        return null;
    }

    @Override
    public void displayAllInstructors() {
        if (instructorList.isEmpty()) {
            System.out.println("No instructors found in the system.");
            return;
        }

        System.out.println("\n========== ALL INSTRUCTORS ==========");
        for (Instructor instructor : instructorList) {
            System.out.println("ID: " + instructor.getId());
            System.out.println("Name: " + instructor.getName());
            System.out.println("Courses Teaching: " + instructor.getCourses());
            System.out.println("-----------------------------------");
        }
    }

    // ✅ ADD THIS METHOD
    @Override
    public ArrayList<Instructor> getAllInstructors() {
        return instructorList;
    }

    // Helper method
    public boolean instructorExists(String id) {
        return getInstructorById(id) != null;
    }

    public void removeInstructor(String id) {
        boolean removed = instructorList.removeIf(i -> i.getId().equalsIgnoreCase(id));
        if (removed) {
            System.out.println("Instructor " + id + " removed successfully.");
        } else {
            System.out.println("Instructor with ID " + id + " not found.");
        }
    }

    public void updateInstructor(String id, String newName) {
        Instructor instructor = getInstructorById(id);
        if (instructor != null) {
            instructor.setName(newName);
            System.out.println("Instructor " + id + " updated to: " + newName);
        }
    }
}