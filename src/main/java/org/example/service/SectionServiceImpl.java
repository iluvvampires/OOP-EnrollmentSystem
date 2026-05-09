package org.example.service;

import org.example.interfaces.ISectionService;
import org.example.model.Section;
import org.example.model.Student;
import org.example.model.Instructor;
import java.util.ArrayList;

public class SectionServiceImpl implements ISectionService {

    private ArrayList<Section> sectionList = new ArrayList<>();

    // ========== ADD SECTION ==========
    @Override
    public void addSection(Section section) {
        // Check for duplicate section name
        if (sectionExists(section.getSectionName())) {
            System.out.println("Error: Section " + section.getSectionName() + " already exists!");
            return;
        }
        sectionList.add(section);
        System.out.println("Section created: " + section.getSectionName() + " (Capacity: " + section.getMaxCapacity() + ")");
    }

    // ========== GET SECTION BY NAME ==========
    @Override
    public Section getSectionByName(String sectionName) {
        for (Section section : sectionList) {
            if (section.getSectionName().equalsIgnoreCase(sectionName)) {
                return section;
            }
        }
        System.out.println("Section " + sectionName + " not found.");
        return null;
    }

    // ========== GET SECTION BY INDEX/ID ==========
    @Override
    public Section getSectionByIndex(int index) {
        if (index >= 0 && index < sectionList.size()) {
            return sectionList.get(index);
        }
        return null;
    }

    // ========== DISPLAY ALL SECTIONS ==========
    @Override
    public void displayAllSections() {
        if (sectionList.isEmpty()) {
            System.out.println("No sections available.");
            return;
        }

        System.out.println("\n========== ALL SECTIONS ==========");
        for (Section section : sectionList) {
            System.out.println(section.toString());
            if (section.getInstructor() != null) {
                System.out.println("  ├── Instructor: " + section.getInstructor().getName());
            } else {
                System.out.println("  ├── Instructor: TBA");
            }
            System.out.println("  └── Enrolled Students: " + section.getEnrolledStudents().size() + "/" + section.getMaxCapacity());
        }
    }

    // ========== ENROLL STUDENT IN SECTION ==========
    @Override
    public boolean enrollStudentInSection(Student student, String sectionName) {
        Section section = getSectionByName(sectionName);
        if (section == null) {
            System.out.println("Error: Section " + sectionName + " not found!");
            return false;
        }

        // Check if section is full
        if (section.isFull()) {
            System.out.println("Error: Section " + sectionName + " is FULL! Cannot enroll " + student.getName());
            return false;
        }

        // Check if student already enrolled
        if (section.getEnrolledStudents().contains(student)) {
            System.out.println("Error: " + student.getName() + " is already enrolled in " + sectionName);
            return false;
        }

        section.getEnrolledStudents().add(student);
        System.out.println("SUCCESS: " + student.getName() + " enrolled in " + sectionName);
        System.out.println("  └── Available seats left: " + (section.getMaxCapacity() - section.getEnrolledStudents().size()));
        return true;
    }

    // ========== ASSIGN INSTRUCTOR TO SECTION ==========
    @Override
    public void assignInstructorToSection(Instructor instructor, String sectionName) {
        Section section = getSectionByName(sectionName);
        if (section == null) {
            System.out.println("Error: Section " + sectionName + " not found!");
            return;
        }

        if (instructor == null) {
            System.out.println("Error: Instructor not found!");
            return;
        }

        section.setInstructor(instructor);
        instructor.addCourse(sectionName);
        System.out.println("SUCCESS: " + instructor.getName() + " assigned to " + sectionName);
    }

    // ========== GET ALL STUDENTS IN SECTION ==========
    @Override
    public ArrayList<Student> getStudentsInSection(String sectionName) {
        Section section = getSectionByName(sectionName);
        if (section == null) {
            return new ArrayList<>();
        }
        return section.getEnrolledStudents();
    }

    // ========== CHECK IF SECTION EXISTS ==========
    @Override
    public boolean sectionExists(String sectionName) {
        return getSectionByName(sectionName) != null;
    }

    // ========== CHECK IF SECTION IS FULL ==========
    @Override
    public boolean isSectionFull(String sectionName) {
        Section section = getSectionByName(sectionName);
        if (section == null) {
            return true; // Non-existent section considered "full" for safety
        }
        return section.isFull();
    }

    // ========== GET AVAILABLE SEATS ==========
    @Override
    public int getAvailableSeats(String sectionName) {
        Section section = getSectionByName(sectionName);
        if (section == null) {
            return 0;
        }
        return section.getMaxCapacity() - section.getEnrolledStudents().size();
    }

    // ========== UPDATE SECTION CAPACITY ==========
    @Override
    public void updateSectionCapacity(String sectionName, int newCapacity) {
        Section section = getSectionByName(sectionName);
        if (section == null) {
            System.out.println("Error: Section " + sectionName + " not found!");
            return;
        }

        if (newCapacity < section.getEnrolledStudents().size()) {
            System.out.println("Error: Cannot reduce capacity below current enrollment (" +
                    section.getEnrolledStudents().size() + " students)!");
            return;
        }

        section.setMaxCapacity(newCapacity);
        System.out.println("Section " + sectionName + " capacity updated to " + newCapacity);
    }

    // ========== REMOVE STUDENT FROM SECTION ==========
    @Override
    public boolean removeStudentFromSection(Student student, String sectionName) {
        Section section = getSectionByName(sectionName);
        if (section == null) {
            System.out.println("Error: Section " + sectionName + " not found!");
            return false;
        }

        boolean removed = section.getEnrolledStudents().remove(student);
        if (removed) {
            System.out.println("SUCCESS: " + student.getName() + " removed from " + sectionName);
        } else {
            System.out.println("Error: " + student.getName() + " not found in " + sectionName);
        }
        return removed;
    }

    // ========== GET ALL SECTIONS (for other services) ==========
    @Override
    public ArrayList<Section> getAllSections() {
        return sectionList;
    }

    // ========== REMOVE SECTION ==========
    @Override
    public void removeSection(String sectionName) {
        boolean removed = sectionList.removeIf(s -> s.getSectionName().equalsIgnoreCase(sectionName));
        if (removed) {
            System.out.println("Section " + sectionName + " removed successfully.");
        } else {
            System.out.println("Section " + sectionName + " not found.");
        }
    }

    // ========== DISPLAY SECTION DETAILS (Detailed View) ==========
    @Override
    public void displaySectionDetails(String sectionName) {
        Section section = getSectionByName(sectionName);
        if (section == null) {
            System.out.println("Section " + sectionName + " not found!");
            return;
        }

        System.out.println("\n╔════════════════════════════════════════╗");
        System.out.println("║     SECTION DETAILS: " + String.format("%-15s", sectionName) + "║");
        System.out.println("╠════════════════════════════════════════╣");
        System.out.println("║ Max Capacity: " + String.format("%-30s", section.getMaxCapacity()) + "║");
        System.out.println("║ Enrolled: " + String.format("%-31s", section.getEnrolledStudents().size() + "/" + section.getMaxCapacity()) + "║");
        System.out.println("║ Available Seats: " + String.format("%-26s", getAvailableSeats(sectionName)) + "║");

        if (section.getInstructor() != null) {
            System.out.println("║ Instructor: " + String.format("%-29s", section.getInstructor().getName()) + "║");
        } else {
            System.out.println("║ Instructor: TBA" + String.format("%-30s", "") + "║");
        }

        System.out.println("╠════════════════════════════════════════╣");
        System.out.println("║ STUDENT ROSTER:                         ║");

        if (section.getEnrolledStudents().isEmpty()) {
            System.out.println("║   (No students enrolled)               ║");
        } else {
            for (Student s : section.getEnrolledStudents()) {
                System.out.println("║   - " + String.format("%-38s", s.getName() + " (" + s.getId() + ")") + "║");
            }
        }
        System.out.println("╚════════════════════════════════════════╝");
    }
}