package org.example.interfaces;

import org.example.model.Instructor;
import org.example.model.Section;
import org.example.exception.DuplicateEntityException;
import java.util.ArrayList;

public interface IInstructorService {

    void addInstructor(Instructor instructor) throws DuplicateEntityException;
    void assignInstructorToSection(Instructor instructor, Section section);
    Instructor getInstructorById(String id);
    void displayAllInstructors();

    // ✅ ADD THIS METHOD
    ArrayList<Instructor> getAllInstructors();

    // Optional: Add these if you need them
    boolean instructorExists(String id);
    void removeInstructor(String id);
    void updateInstructor(String id, String newName);
}