package org.example.interfaces;

import org.example.model.Section;
import org.example.model.Student;
import org.example.model.Instructor;
import java.util.ArrayList;

public interface ISectionService {

    // Basic CRUD
    void addSection(Section section);
    void removeSection(String sectionName);

    // Retrieval
    Section getSectionByName(String sectionName);
    Section getSectionByIndex(int index);
    ArrayList<Section> getAllSections();

    // Display
    void displayAllSections();
    void displaySectionDetails(String sectionName);

    // Enrollment operations
    boolean enrollStudentInSection(Student student, String sectionName);
    boolean removeStudentFromSection(Student student, String sectionName);
    ArrayList<Student> getStudentsInSection(String sectionName);

    // Instructor operations
    void assignInstructorToSection(Instructor instructor, String sectionName);

    // Utility/Validation
    boolean sectionExists(String sectionName);
    boolean isSectionFull(String sectionName);
    int getAvailableSeats(String sectionName);
    void updateSectionCapacity(String sectionName, int newCapacity);
}