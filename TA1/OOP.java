// OOP Approach
// Demonstrates encapsulation, inheritance, polymorphism, and abstraction
package TA1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// Abstract base class demonstrating abstraction
abstract class Person {
    private final String id;
    private String name;
    private String email;

    public Person(String id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    // Encapsulation through getters/setters
    public String getId() { return id; }
    public String getName() { return name; }
    public String getEmail() { return email; }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    // Abstract method demonstrating abstraction
    public abstract String getRole();
}

// Inheritance demonstrated through Student class
class Student extends Person {
    private final List<Course> enrolledCourses;
    private String major;

    public Student(String id, String name, String email, String major) {
        super(id, name, email);
        this.major = major;
        this.enrolledCourses = new ArrayList<>();
    }

    @Override
    public String getRole() { return "STUDENT"; }

    public void enrollInCourse(Course course) {
        if (course.hasAvailableSeats()) {
            enrolledCourses.add(course);
            course.addStudent(this);
        }
    }

    public List<Course> getEnrolledCourses() {
        return new ArrayList<>(enrolledCourses);
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }
}

// Another example of inheritance
class Professor extends Person {
    private final List<Course> taughtCourses;
    private final String department;

    public Professor(String id, String name, String email, String department) {
        super(id, name, email);
        this.department = department;
        this.taughtCourses = new ArrayList<>();
    }

    @Override
    public String getRole() { return "PROFESSOR"; }

    public List<Course> getTaughtCourses() {
        return taughtCourses;
    }

    public String getDepartment() {
        return department;
    }

    public void assignCourse(Course course) {
        taughtCourses.add(course);
        course.setProfessor(this);
    }
}

class Course {
    private String courseId;
    private String name;
    private Professor professor;
    private final List<Student> enrolledStudents;
    private int maxCapacity;

    public List<Student> getEnrolledStudents() {
        return enrolledStudents;
    }

    public Course(String courseId, String name, int maxCapacity) {
        this.courseId = courseId;
        this.name = name;
        this.maxCapacity = maxCapacity;
        this.enrolledStudents = new ArrayList<>();

    }

    public boolean hasAvailableSeats() {
        return enrolledStudents.size() < maxCapacity;
    }

    public void addStudent(Student student) {
        if (hasAvailableSeats()) {
            enrolledStudents.add(student);
        }
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Professor getProfessor() {
        return professor;
    }

    public int getMaxCapacity() {
        return maxCapacity;
    }

    public void setMaxCapacity(int maxCapacity) {
        this.maxCapacity = maxCapacity;
    }
}

// Polymorphism demonstrated through interface
interface EnrollmentValidator {
    boolean validateEnrollment(Student student, Course course);
}

// Different implementations showing polymorphism
class CapacityValidator implements EnrollmentValidator {
    @Override
    public boolean validateEnrollment(Student student, Course course) {
        return course.hasAvailableSeats();
    }
}

class PrerequisiteValidator implements EnrollmentValidator {
    @Override
    public boolean validateEnrollment(Student student, Course course) {
        // Check prerequisites logic
        return true;
    }
}

class EnrollmentSystem {
    private final List<EnrollmentValidator> validators;

    public EnrollmentSystem() {
        validators = Arrays.asList(
            new CapacityValidator(),
            new PrerequisiteValidator()
        );
    }

    public boolean enrollStudent(Student student, Course course) {
        // Using polymorphism to validate enrollment
        boolean isValid = validators.stream()
            .allMatch(validator -> validator.validateEnrollment(student, course));
        
        if (isValid) {
            student.enrollInCourse(course);
            return true;
        }
        return false;
    }
}
