package org.example.controller;

import org.example.model.Instructor;
import org.example.model.Section;
import org.example.model.Student;
import org.example.service.InstructorServiceImpl;
import org.example.service.SectionServiceImpl;
import org.example.interfaces.IInstructorService;
import org.example.interfaces.ISectionService;

public class ProgramChair {
    private IInstructorService instructorService;
    private ISectionService sectionService;

    public ProgramChair() {
        this.instructorService = new InstructorServiceImpl();
        this.sectionService = new SectionServiceImpl();
    }

    public String createSection(String sectionName, int maxCapacity) {
        Section section = new Section(sectionName, maxCapacity);
        sectionService.addSection(section);
        return "[ProgramChair] SUCCESS: Section " + sectionName + " created with capacity " + maxCapacity;
    }

    public String assignInstructorToSection(String instructorId, String sectionName) {
        Instructor instructor = instructorService.getInstructorById(instructorId);
        Section section = sectionService.getSectionByName(sectionName);

        if (instructor == null) {
            return "[ProgramChair] ERROR: Instructor ID " + instructorId + " not found!";
        }
        if (section == null) {
            return "[ProgramChair] ERROR: Section " + sectionName + " not found!";
        }

        instructorService.assignInstructorToSection(instructor, section);
        return "[ProgramChair] SUCCESS: " + instructor.getName() + " assigned to " + sectionName;
    }

    public String viewAllSections() {
        StringBuilder result = new StringBuilder("\n===== ALL SECTIONS =====\n");
        for (Section s : sectionService.getAllSections()) {
            result.append(s.toString()).append("\n");
            if (s.getInstructor() != null) {
                result.append("  Instructor: ").append(s.getInstructor().getName()).append("\n");
            } else {
                result.append("  Instructor: TBA\n");
            }
            result.append("  Enrolled: ").append(s.getEnrolledStudents().size())
                    .append("/").append(s.getMaxCapacity()).append("\n");
        }
        return result.toString();
    }

    public String getSectionDetails(String sectionName) {
        Section section = sectionService.getSectionByName(sectionName);
        if (section == null) {
            return "[ProgramChair] ERROR: Section " + sectionName + " not found!";
        }

        StringBuilder result = new StringBuilder();
        result.append("\n===== SECTION: ").append(sectionName).append(" =====\n");
        result.append("Capacity: ").append(section.getEnrolledStudents().size())
                .append("/").append(section.getMaxCapacity()).append("\n");

        if (section.getInstructor() != null) {
            result.append("Instructor: ").append(section.getInstructor().getName()).append("\n");
        }

        result.append("Students:\n");
        for (Student s : section.getEnrolledStudents()) {
            result.append("  - ").append(s.getName()).append(" (").append(s.getId()).append(")\n");
        }
        return result.toString();
    }

    // ✅ NEW METHOD - Update Section Capacity
    public String updateSectionCapacity(String sectionName, int newCapacity) {
        Section section = sectionService.getSectionByName(sectionName);
        if (section == null) {
            return "[ProgramChair] ERROR: Section " + sectionName + " not found!";
        }

        if (newCapacity < section.getEnrolledStudents().size()) {
            return "[ProgramChair] ERROR: Cannot reduce capacity below current enrollment (" +
                    section.getEnrolledStudents().size() + " students)!";
        }

        section.setMaxCapacity(newCapacity);
        return "[ProgramChair] SUCCESS: Section " + sectionName + " capacity updated to " + newCapacity;
    }
}