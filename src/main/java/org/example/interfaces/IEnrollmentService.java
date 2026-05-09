package org.example.interfaces;

import org.example.model.Student;
import org.example.model.Section;
import org.example.model.Department;
import org.example.exception.SectionFullException;

public interface IEnrollmentService {
    void enrollStudentInSection(Student student, Section section) throws SectionFullException;
    void viewDepartmentHierarchy(Department department);
    boolean isSectionFull(Section section);
}