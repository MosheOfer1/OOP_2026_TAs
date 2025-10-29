package TA1;

import java.util.*;

public class EnrollmentDemo {
    public static void main(String[] args) {
        System.out.println("=== OOP Approach Demo ===");
        demonstrateOOP();

        System.out.println("\n=== FP Approach Demo ===");
        demonstrateFP();

    }

    private static void demonstrateOOP() {
        // Create courses
        Course javaCourse = new Course("CS101", "Java Programming", 3);
        Course pythonCourse = new Course("CS102", "Python Programming", 2);

        // Create professors
        Professor profJohn = new Professor("P1", "John Doe", "john@university.edu", "Computer Science");
        profJohn.assignCourse(javaCourse);
        Professor profJane = new Professor("P2", "Jane Smith", "jane@university.edu", "Computer Science");
        profJane.assignCourse(pythonCourse);

        // Create students
        Student alice = new Student("S1", "Alice Brown", "alice@university.edu", "Computer Science");
        Student bob = new Student("S2", "Bob Wilson", "bob@university.edu", "Computer Science");
        Student charlie = new Student("S3", "Charlie Davis", "charlie@university.edu", "Computer Science");

        // Create enrollment system with validators
        EnrollmentSystem enrollmentSystem = new EnrollmentSystem();

        // Perform enrollments
        System.out.println("Enrolling students in Java course:");
        System.out.println("Alice enrollment: " + enrollmentSystem.enrollStudent(alice, javaCourse));
        System.out.println("Bob enrollment: " + enrollmentSystem.enrollStudent(bob, javaCourse));
        System.out.println("Charlie enrollment: " + enrollmentSystem.enrollStudent(charlie, javaCourse));

        System.out.println("\nJava course enrollments:");
        javaCourse.getEnrolledStudents().forEach(student ->
                System.out.println(student.getName() + " (ID: " + student.getId() + ")"));

        System.out.println("\nEnrolling students in Python course:");
        System.out.println("Alice enrollment: " + enrollmentSystem.enrollStudent(alice, pythonCourse));
        System.out.println("Bob enrollment: " + enrollmentSystem.enrollStudent(bob, pythonCourse));
        System.out.println("Charlie enrollment: " + enrollmentSystem.enrollStudent(charlie, pythonCourse));
    }

    private static void demonstrateFP() {
        // Create immutable records for courses
        List<CourseRecord> courses = new ArrayList<>(Arrays.asList(
                new CourseRecord("CS101", "Java Programming", "P1", new ArrayList<>(), 3),
                new CourseRecord("CS102", "Python Programming", "P2", new ArrayList<>(), 2)
        ));

        // Create immutable records for students
        List<StudentRecord> students = Arrays.asList(
                new StudentRecord("S1", "Alice Brown", "alice@university.edu", "Computer Science", new ArrayList<>()),
                new StudentRecord("S2", "Bob Wilson", "bob@university.edu", "Computer Science", new ArrayList<>()),
                new StudentRecord("S3", "Charlie Davis", "charlie@university.edu", "Computer Science", new ArrayList<>())
        );

        // Create enrollment requests
        List<String> enrollmentRequests = Arrays.asList(
                "CS101", "CS101", "CS101",  // Three students requesting Java course
                "CS102", "CS102", "CS102"   // Three students requesting Python course
        );

        // Process enrollments functionally
        EnrollmentProcessor processor = new EnrollmentProcessor();
        List<CourseRecord> updatedCourses = processor.processEnrollments(courses, students, enrollmentRequests);

        // Print results using functional approach
        System.out.println("Java Course enrollments:");
        updatedCourses.stream()
                .filter(course -> course.courseId().equals("CS101"))
                .findFirst()
                .ifPresent(course -> {
                    System.out.println("Total enrolled: " + course.enrolledStudentIds().size());
                    course.enrolledStudentIds().forEach(studentId ->
                            students.stream()
                                    .filter(s -> s.id().equals(studentId))
                                    .findFirst()
                                    .ifPresent(s -> System.out.println(s.name() + " (ID: " + s.id() + ")"))
                    );
                });

        System.out.println("\nPython Course enrollments:");
        updatedCourses.stream()
                .filter(course -> course.courseId().equals("CS102"))
                .findFirst()
                .ifPresent(course -> {
                    System.out.println("Total enrolled: " + course.enrolledStudentIds().size());
                    course.enrolledStudentIds().forEach(studentId ->
                            students.stream()
                                    .filter(s -> s.id().equals(studentId))
                                    .findFirst()
                                    .ifPresent(s -> System.out.println(s.name() + " (ID: " + s.id() + ")"))
                    );
                });

        // Demonstrate function composition
        System.out.println("\nTrying to enroll one more student in Java course:");
        StudentRecord newStudent = new StudentRecord("S4", "David Lee", "david@university.edu",
                "Computer Science", new ArrayList<>());

        updatedCourses.stream()
                .filter(course -> course.courseId().equals("CS101"))
                .findFirst()
                .flatMap(course -> EnrollmentService.validateAndEnroll(newStudent).apply(course))
                .ifPresentOrElse(
                        course -> System.out.println("Successfully enrolled David"),
                        () -> System.out.println("Could not enroll David (course might be full)")
                );
    }
}