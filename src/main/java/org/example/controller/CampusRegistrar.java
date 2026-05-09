package org.example.controller;

import org.example.model.Student;
import org.example.model.Course;
import org.example.service.StudentServiceImpl;
import org.example.service.CourseServiceImpl;
import org.example.interfaces.IStudentService;
import org.example.interfaces.ICourseService;
import org.example.exception.DuplicateEntityException;

public class CampusRegistrar {
    private IStudentService studentService;
    private ICourseService courseService;

    public CampusRegistrar() {
        this.studentService = new StudentServiceImpl();
        this.courseService = new CourseServiceImpl();
    }

    // ========== STUDENT MANAGEMENT ==========

    public String registerStudent(String id, String name, String program) {
        try {
            Student student = new Student(id, name, program);
            studentService.addStudent(student);
            return "[CampusRegistrar] SUCCESS: Student " + name + " (ID: " + id + ") registered.";
        } catch (DuplicateEntityException e) {
            return "[CampusRegistrar] ERROR: " + e.getMessage();
        }
    }

    public String viewAllStudents() {
        StringBuilder result = new StringBuilder("\n===== STUDENT ROSTER =====\n");
        for (Student s : studentService.getAllStudents()) {
            result.append("ID: ").append(s.getId())
                    .append(" | Name: ").append(s.getName())
                    .append(" | Program: ").append(s.getProgram()).append("\n");
        }
        return result.toString();
    }

    public String updateStudentRecord(String id, String newName, String newProgram) {
        if (!studentService.studentExists(id)) {
            return "[CampusRegistrar] ERROR: Student ID " + id + " not found!";
        }
        studentService.updateStudent(id, newName, newProgram);
        return "[CampusRegistrar] SUCCESS: Student " + id + " updated.";
    }

    public String removeStudentRecord(String id) {
        if (!studentService.studentExists(id)) {
            return "[CampusRegistrar] ERROR: Student ID " + id + " not found!";
        }
        studentService.removeStudent(id);
        return "[CampusRegistrar] SUCCESS: Student " + id + " removed.";
    }

    public Student findStudent(String id) {
        return studentService.getStudentById(id);
    }

    // ========== COURSE MANAGEMENT ==========

    public String addCourse(String courseId, String courseName, String program) {
        Course course = new Course(courseId, courseName, program);

        // Check if course already exists using the service method
        if (courseService.courseExists(courseId)) {
            return "[CampusRegistrar] ERROR: Course with ID " + courseId + " already exists!";
        }

        courseService.addCourse(course);  // No exception thrown
        return "[CampusRegistrar] SUCCESS: Course " + courseName + " added.";
    }

    public String viewAllCourses() {
        StringBuilder result = new StringBuilder("\n===== COURSE CATALOG =====\n");
        for (Course c : courseService.getAllCourses()) {
            result.append(c.toString()).append("\n");
        }
        return result.toString();
    }

    public Course findCourse(String courseId) {
        return courseService.getCourseById(courseId);
    }

    public String removeCourse(String courseId) {
        if (!courseService.courseExists(courseId)) {
            return "[CampusRegistrar] ERROR: Course ID " + courseId + " not found!";
        }
        courseService.removeCourse(courseId);
        return "[CampusRegistrar] SUCCESS: Course " + courseId + " removed.";
    }
}