package org.example;

import org.example.controller.*;
import java.util.Scanner;

public class Main {
    private static CampusRegistrar registrar = new CampusRegistrar();
    private static HumanResources hr = new HumanResources();
    private static ProgramChair programChair = new ProgramChair();
    private static DepartmentHead deptHead = new DepartmentHead("College of Computer Studies");
    private static AccountingOffice accounting = new AccountingOffice();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        // Load sample data for testing


        while (true) {
            displayMainMenu();
            int choice = getIntInput();

            switch (choice) {
                case 1:
                    studentManagementMenu();
                    break;
                case 2:
                    instructorManagementMenu();
                    break;
                case 3:
                    courseManagementMenu();
                    break;
                case 4:
                    sectionManagementMenu();
                    break;
                case 5:
                    enrollmentMenu();
                    break;
                case 6:
                    tuitionMenu();
                    break;
                case 7:
                    viewHierarchyMenu();
                    break;
                case 8:
                    System.out.println("\n✅ Exiting Enrollment System. Goodbye!");
                    scanner.close();
                    return;
                default:
                    System.out.println("❌ Invalid choice! Please enter 1-8.");
            }
        }
    }

    // ========== DISPLAY MAIN MENU ==========
    private static void displayMainMenu() {
        System.out.println("\n╔══════════════════════════════════════════════════╗");
        System.out.println("║           ENROLLMENT SYSTEM - MAIN MENU          ║");
        System.out.println("╠══════════════════════════════════════════════════╣");
        System.out.println("║  [1] Student Management (Campus Registrar)       ║");
        System.out.println("║  [2] Instructor Management (Human Resources)     ║");
        System.out.println("║  [3] Course Management (Campus Registrar)        ║");
        System.out.println("║  [4] Section Management (Program Chair)          ║");
        System.out.println("║  [5] Enrollment (Department Head)                ║");
        System.out.println("║  [6] Tuition & Payments (Accounting Office)      ║");
        System.out.println("║  [7] View Department Hierarchy                   ║");
        System.out.println("║  [8] Exit                                        ║");
        System.out.println("╚══════════════════════════════════════════════════╝");
        System.out.print("\n👉 Enter your choice: ");
    }

    // ========== HELPER METHODS ==========
    private static int getIntInput() {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    private static double getDoubleInput() {
        try {
            return Double.parseDouble(scanner.nextLine());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    // ========== STUDENT MANAGEMENT MENU ==========
    private static void studentManagementMenu() {
        while (true) {
            System.out.println("\n┌─────────────────────────────────────────┐");
            System.out.println("│     STUDENT MANAGEMENT                   │");
            System.out.println("├─────────────────────────────────────────┤");
            System.out.println("│  [1] Register New Student               │");
            System.out.println("│  [2] View All Students                  │");
            System.out.println("│  [3] Update Student Record              │");
            System.out.println("│  [4] Remove Student                     │");
            System.out.println("│  [5] Find Student by ID                 │");
            System.out.println("│  [6] Back to Main Menu                  │");
            System.out.println("└─────────────────────────────────────────┘");
            System.out.print("👉 Choice: ");

            int choice = getIntInput();

            switch (choice) {
                case 1:
                    System.out.print("Enter Student ID: ");
                    String id = scanner.nextLine();
                    System.out.print("Enter Name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter Program (BSIT/BSCS/BSIS): ");
                    String program = scanner.nextLine();
                    System.out.println(registrar.registerStudent(id, name, program));
                    break;
                case 2:
                    System.out.println(registrar.viewAllStudents());
                    break;
                case 3:
                    System.out.print("Enter Student ID: ");
                    id = scanner.nextLine();
                    System.out.print("New Name: ");
                    name = scanner.nextLine();
                    System.out.print("New Program: ");
                    program = scanner.nextLine();
                    System.out.println(registrar.updateStudentRecord(id, name, program));
                    break;
                case 4:
                    System.out.print("Enter Student ID: ");
                    id = scanner.nextLine();
                    System.out.println(registrar.removeStudentRecord(id));
                    break;
                case 5:
                    System.out.print("Enter Student ID: ");
                    id = scanner.nextLine();
                    var student = registrar.findStudent(id);
                    if (student != null) {
                        System.out.println("\n✅ Student Found:");
                        System.out.println("   ID: " + student.getId());
                        System.out.println("   Name: " + student.getName());
                        System.out.println("   Program: " + student.getProgram());
                    } else {
                        System.out.println("❌ Student not found!");
                    }
                    break;
                case 6:
                    return;
                default:
                    System.out.println("❌ Invalid choice!");
            }
        }
    }

    // ========== INSTRUCTOR MANAGEMENT MENU ==========
    private static void instructorManagementMenu() {
        while (true) {
            System.out.println("\n┌─────────────────────────────────────────┐");
            System.out.println("│     INSTRUCTOR MANAGEMENT                │");
            System.out.println("├─────────────────────────────────────────┤");
            System.out.println("│  [1] Hire New Instructor (HR)            │");
            System.out.println("│  [2] View All Instructors                │");
            System.out.println("│  [3] View Instructor Teaching Load       │");
            System.out.println("│  [4] Update Instructor Info              │");
            System.out.println("│  [5] Terminate Instructor                │");
            System.out.println("│  [6] Back to Main Menu                   │");
            System.out.println("└─────────────────────────────────────────┘");
            System.out.print("👉 Choice: ");

            int choice = getIntInput();

            switch (choice) {
                case 1:
                    System.out.print("Enter Instructor ID: ");
                    String id = scanner.nextLine();
                    System.out.print("Enter Name: ");
                    String name = scanner.nextLine();
                    System.out.println(hr.hireInstructor(id, name));
                    break;
                case 2:
                    System.out.println(hr.viewAllInstructors());
                    break;
                case 3:
                    System.out.print("Enter Instructor ID: ");
                    id = scanner.nextLine();
                    System.out.println(hr.getInstructorTeachingLoad(id));
                    break;
                case 4:
                    System.out.print("Enter Instructor ID: ");
                    id = scanner.nextLine();
                    System.out.print("New Name: ");
                    name = scanner.nextLine();
                    System.out.println(hr.updateInstructorInfo(id, name));
                    break;
                case 5:
                    System.out.print("Enter Instructor ID: ");
                    id = scanner.nextLine();
                    System.out.println(hr.terminateInstructor(id));
                    break;
                case 6:
                    return;
                default:
                    System.out.println("❌ Invalid choice!");
            }
        }
    }

    // ========== COURSE MANAGEMENT MENU ==========
    private static void courseManagementMenu() {
        while (true) {
            System.out.println("\n┌─────────────────────────────────────────┐");
            System.out.println("│     COURSE MANAGEMENT                    │");
            System.out.println("├─────────────────────────────────────────┤");
            System.out.println("│  [1] Add New Course                      │");
            System.out.println("│  [2] View All Courses                    │");
            System.out.println("│  [3] Remove Course                       │");
            System.out.println("│  [4] Find Course by ID                   │");
            System.out.println("│  [5] Back to Main Menu                   │");
            System.out.println("└─────────────────────────────────────────┘");
            System.out.print("👉 Choice: ");

            int choice = getIntInput();

            switch (choice) {
                case 1:
                    System.out.print("Enter Course ID: ");
                    String id = scanner.nextLine();
                    System.out.print("Enter Course Name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter Program: ");
                    String program = scanner.nextLine();
                    System.out.println(registrar.addCourse(id, name, program));
                    break;
                case 2:
                    System.out.println(registrar.viewAllCourses());
                    break;
                case 3:
                    System.out.print("Enter Course ID: ");
                    id = scanner.nextLine();
                    System.out.println(registrar.removeCourse(id));
                    break;
                case 4:
                    System.out.print("Enter Course ID: ");
                    id = scanner.nextLine();
                    var course = registrar.findCourse(id);
                    if (course != null) {
                        System.out.println("\n✅ Course Found:");
                        System.out.println(course);
                    } else {
                        System.out.println("❌ Course not found!");
                    }
                    break;
                case 5:
                    return;
                default:
                    System.out.println("❌ Invalid choice!");
            }
        }
    }

    // ========== SECTION MANAGEMENT MENU ==========
    private static void sectionManagementMenu() {
        while (true) {
            System.out.println("\n┌─────────────────────────────────────────┐");
            System.out.println("│     SECTION MANAGEMENT                   │");
            System.out.println("├─────────────────────────────────────────┤");
            System.out.println("│  [1] Create New Section                  │");
            System.out.println("│  [2] Assign Instructor to Section        │");
            System.out.println("│  [3] View All Sections                   │");
            System.out.println("│  [4] View Section Details                │");
            System.out.println("│  [5] Update Section Capacity             │");
            System.out.println("│  [6] Back to Main Menu                   │");
            System.out.println("└─────────────────────────────────────────┘");
            System.out.print("👉 Choice: ");

            int choice = getIntInput();

            switch (choice) {
                case 1:
                    System.out.print("Enter Section Name (e.g., BSIT-1A): ");
                    String name = scanner.nextLine();
                    System.out.print("Enter Max Capacity: ");
                    int capacity = getIntInput();
                    System.out.println(programChair.createSection(name, capacity));
                    System.out.println(deptHead.addSectionToDepartment(name));
                    break;
                case 2:
                    System.out.print("Enter Instructor ID: ");
                    String instructorId = scanner.nextLine();
                    System.out.print("Enter Section Name: ");
                    String sectionName = scanner.nextLine();
                    System.out.println(programChair.assignInstructorToSection(instructorId, sectionName));
                    break;
                case 3:
                    System.out.println(programChair.viewAllSections());
                    break;
                case 4:
                    System.out.print("Enter Section Name: ");
                    sectionName = scanner.nextLine();
                    System.out.println(programChair.getSectionDetails(sectionName));
                    break;
                case 5:
                    System.out.print("Enter Section Name: ");
                    sectionName = scanner.nextLine();
                    System.out.print("Enter New Max Capacity: ");
                    int newCapacity = getIntInput();
                    System.out.println(programChair.updateSectionCapacity(sectionName, newCapacity));
                    break;
                case 6:
                    return;
                default:
                    System.out.println("❌ Invalid choice!");
            }
        }
    }

    // ========== ENROLLMENT MENU ==========
    private static void enrollmentMenu() {
        while (true) {
            System.out.println("\n┌─────────────────────────────────────────┐");
            System.out.println("│     ENROLLMENT                           │");
            System.out.println("├─────────────────────────────────────────┤");
            System.out.println("│  [1] Enroll Student in Section           │");
            System.out.println("│  [2] Check Available Seats               │");
            System.out.println("│  [3] View Students in Section            │");
            System.out.println("│  [4] Remove Student from Section         │");
            System.out.println("│  [5] Back to Main Menu                   │");
            System.out.println("└─────────────────────────────────────────┘");
            System.out.print("👉 Choice: ");

            int choice = getIntInput();

            switch (choice) {
                case 1:
                    System.out.print("Enter Student ID: ");
                    String studentId = scanner.nextLine();
                    System.out.print("Enter Section Name: ");
                    String sectionName = scanner.nextLine();
                    System.out.println(deptHead.enrollStudentInSection(studentId, sectionName));
                    break;
                case 2:
                    System.out.print("Enter Section Name: ");
                    sectionName = scanner.nextLine();
                    System.out.println(deptHead.getAvailableSeatsInSection(sectionName));
                    break;
                case 3:
                    System.out.print("Enter Section Name: ");
                    sectionName = scanner.nextLine();
                    System.out.println(programChair.getSectionDetails(sectionName));
                    break;
                case 4:
                    System.out.print("Enter Student ID: ");
                    studentId = scanner.nextLine();
                    System.out.print("Enter Section Name: ");
                    sectionName = scanner.nextLine();
                    System.out.println(deptHead.removeStudentFromSection(studentId, sectionName));
                    break;
                case 5:
                    return;
                default:
                    System.out.println("❌ Invalid choice!");
            }
        }
    }

    // ========== TUITION MENU ==========
    private static void tuitionMenu() {
        while (true) {
            System.out.println("\n┌─────────────────────────────────────────┐");
            System.out.println("│     TUITION MANAGEMENT                   │");
            System.out.println("├─────────────────────────────────────────┤");
            System.out.println("│  [1] Calculate Tuition Fee               │");
            System.out.println("│  [2] Make Payment                        │");
            System.out.println("│  [3] Check Balance                       │");
            System.out.println("│  [4] View Tuition Statement              │");
            System.out.println("│  [5] Back to Main Menu                   │");
            System.out.println("└─────────────────────────────────────────┘");
            System.out.print("👉 Choice: ");

            int choice = getIntInput();

            switch (choice) {
                case 1:
                    System.out.print("Enter Student ID: ");
                    String id = scanner.nextLine();
                    System.out.print("Enter Number of Units: ");
                    int units = getIntInput();
                    System.out.print("Enter Discount Rate (0.00 - 1.00): ");
                    double discount = getDoubleInput();
                    System.out.println(accounting.calculateStudentTuition(id, units, discount));
                    break;
                case 2:
                    System.out.print("Enter Student ID: ");
                    id = scanner.nextLine();
                    System.out.print("Enter Payment Amount: ");
                    double amount = getDoubleInput();
                    System.out.println(accounting.processPayment(id, amount));
                    break;
                case 3:
                    System.out.print("Enter Student ID: ");
                    id = scanner.nextLine();
                    System.out.println(accounting.checkStudentBalance(id));
                    break;
                case 4:
                    System.out.print("Enter Student ID: ");
                    id = scanner.nextLine();
                    System.out.println(accounting.getTuitionDetails(id));
                    break;
                case 5:
                    return;
                default:
                    System.out.println("❌ Invalid choice!");
            }
        }
    }

    // ========== VIEW HIERARCHY MENU ==========
    private static void viewHierarchyMenu() {
        System.out.println(deptHead.viewDepartmentHierarchy());

        System.out.println("\nPress Enter to continue...");
        scanner.nextLine();
    }
}