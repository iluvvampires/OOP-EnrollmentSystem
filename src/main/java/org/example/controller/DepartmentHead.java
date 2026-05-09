package org.example.controller;

import org.example.model.Student;
import org.example.model.Section;
import org.example.model.Department;
import org.example.service.EnrollmentServiceImpl;
import org.example.service.StudentServiceImpl;
import org.example.service.SectionServiceImpl;
import org.example.interfaces.IEnrollmentService;
import org.example.interfaces.IStudentService;
import org.example.interfaces.ISectionService;
import org.example.exception.SectionFullException;

public class DepartmentHead {
    private IEnrollmentService enrollmentService;
    private IStudentService studentService;
    private ISectionService sectionService;
    private Department department;

    public DepartmentHead(String departmentName) {
        this.enrollmentService = new EnrollmentServiceImpl();
        this.studentService = new StudentServiceImpl();
        this.sectionService = new SectionServiceImpl();
        this.department = new Department(departmentName);
    }

    public String addSectionToDepartment(String sectionName) {
        Section section = sectionService.getSectionByName(sectionName);
        if (section == null) {
            return "[DepartmentHead] ERROR: Section " + sectionName + " does not exist!";
        }
        department.addSection(section);
        return "[DepartmentHead] SUCCESS: Section " + sectionName + " added to " + department.getDepartmentName();
    }

    public String enrollStudentInSection(String studentId, String sectionName) {
        Student student = studentService.getStudentById(studentId);
        Section section = sectionService.getSectionByName(sectionName);

        if (student == null) {
            return "[DepartmentHead] ERROR: Student ID " + studentId + " not found!";
        }
        if (section == null) {
            return "[DepartmentHead] ERROR: Section " + sectionName + " not found!";
        }

        try {
            enrollmentService.enrollStudentInSection(student, section);
            return "[DepartmentHead] SUCCESS: " + student.getName() + " enrolled in " + sectionName;
        } catch (SectionFullException e) {
            return "[DepartmentHead] ERROR: " + e.getMessage();
        }
    }

    public String viewDepartmentHierarchy() {
        StringBuilder result = new StringBuilder();
        result.append("\n========== ").append(department.getDepartmentName()).append(" ==========\n");

        for (Section s : department.getSections()) {
            result.append("\n└── ").append(s.getSectionName()).append("\n");
            if (s.getInstructor() != null) {
                result.append("    └── Instructor: ").append(s.getInstructor().getName()).append("\n");
            }
            result.append("    └── Students:\n");
            for (Student stu : s.getEnrolledStudents()) {
                result.append("        └── ").append(stu.getName()).append(" (").append(stu.getId()).append(")\n");
            }
            if (s.getEnrolledStudents().isEmpty()) {
                result.append("        └── (No students enrolled)\n");
            }
        }
        return result.toString();
    }

    public String getAvailableSeatsInSection(String sectionName) {
        Section section = sectionService.getSectionByName(sectionName);
        if (section == null) {
            return "[DepartmentHead] ERROR: Section not found!";
        }
        int available = section.getMaxCapacity() - section.getEnrolledStudents().size();
        return "[DepartmentHead] Section " + sectionName + " has " + available + " available seats.";
    }

    // ✅ NEW METHOD - Remove Student from Section
    public String removeStudentFromSection(String studentId, String sectionName) {
        Student student = studentService.getStudentById(studentId);
        Section section = sectionService.getSectionByName(sectionName);

        if (student == null) {
            return "[DepartmentHead] ERROR: Student ID " + studentId + " not found!";
        }
        if (section == null) {
            return "[DepartmentHead] ERROR: Section " + sectionName + " not found!";
        }

        boolean removed = section.getEnrolledStudents().remove(student);
        if (removed) {
            return "[DepartmentHead] SUCCESS: " + student.getName() + " removed from " + sectionName;
        } else {
            return "[DepartmentHead] ERROR: " + student.getName() + " is not enrolled in " + sectionName;
        }
    }
}