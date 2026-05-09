package org.example.service;

import org.example.model.Student;
import org.example.model.Section;
import org.example.model.Department;
import org.example.interfaces.IEnrollmentService;
import org.example.exception.SectionFullException;

public class EnrollmentServiceImpl implements IEnrollmentService {

    @Override
    public void enrollStudentInSection(Student student, Section section) throws SectionFullException {
        if (section.isFull()) {
            throw new SectionFullException("Cannot enroll " + student.getName() +
                    " - Section " + section.getSectionName() + " is FULL! (Max: " + section.getMaxCapacity() + ")");
        }

        if (!section.getEnrolledStudents().contains(student)) {
            section.getEnrolledStudents().add(student);
            System.out.println(student.getName() + " enrolled in " + section.getSectionName());
        } else {
            System.out.println(student.getName() + " is already enrolled in this section.");
        }
    }

    @Override
    public void viewDepartmentHierarchy(Department department) {
        department.displayHierarchy();
    }

    @Override
    public boolean isSectionFull(Section section) {
        return section.isFull();
    }
}