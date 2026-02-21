package org.example.service;
import org.example.model.Course;
import java.util.ArrayList;
import java.util.Scanner;

public class CourseRegistration {
    private ArrayList<Course> courseArrayList = new ArrayList<>();
    private Scanner scanner = new Scanner(System.in);

    //All service or server layers are all in this, this includes the CRUD.

    //Create
    public void addCourse(Course course){
        courseArrayList.add(course);
    }

    //Read
    public void displayAll() {
        System.out.println(courseArrayList);
    }

    //Update
    public void  updateCourse(Course course){
        for (int i = 0; i < courseArrayList.size(); i++) {
            if(courseArrayList.get(i).getCourseID() == (course.getCourseID())){
                System.out.print("Enter name: ");
                String name = scanner.nextLine();

                System.out.print("Enter program: ");
                int program = scanner.nextInt();

                courseArrayList.set(i, new Course(course.getCourseID(), name, program));
                break;
            }
        }
    }

    //Remove
    public String delete(Course course){
        for(int i = 0; i < courseArrayList.size(); i++){
            if(courseArrayList.get(i).getCourseID() == (course.getCourseID())){
                courseArrayList.remove(i);
                return "Successfully Deleted!";
            }
        }

        return "No such person";
    }
}