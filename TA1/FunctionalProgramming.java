// Functional Programming Approach
// Using immutable data structures and pure functions
package TA1;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

record StudentRecord(String id, String name, String email, String major, List<String> enrolledCourseIds) {}
record ProfessorRecord(String id, String name, String email, String department, List<String> taughtCourseIds) {}
record CourseRecord(String courseId, String name, String professorId, List<String> enrolledStudentIds, int maxCapacity) {}

class EnrollmentService {
    // Pure functions that don't modify state
    public static Optional<CourseRecord> enrollStudent(
            CourseRecord course,
            StudentRecord student) {

        if (isEnrollmentValid(course, student)) {
            List<String> newEnrolledStudents = new ArrayList<>(course.enrolledStudentIds());
            newEnrolledStudents.add(student.id());

            return Optional.of(new CourseRecord(
                    course.courseId(),
                    course.name(),
                    course.professorId(),
                    newEnrolledStudents,
                    course.maxCapacity()
            ));
        }
        return Optional.empty();
    }

    public static boolean isEnrollmentValid(CourseRecord course, StudentRecord student) {
        return course.enrolledStudentIds().size() < course.maxCapacity() &&
                !course.enrolledStudentIds().contains(student.id());
    }

    // Function composition example
    public static Function<CourseRecord, Optional<CourseRecord>> validateAndEnroll(StudentRecord student) {
        return course -> isEnrollmentValid(course, student) ?
                enrollStudent(course, student) : Optional.empty();
    }
}

// Example usage of FP approach with streams and immutable data
class EnrollmentProcessor {
    public List<CourseRecord> processEnrollments(
            List<CourseRecord> courses,
            List<StudentRecord> students,
            List<String> enrollmentRequests) {

        return courses.stream()
                .map(course -> enrollmentRequests.stream()
                        .filter(req -> req.equals(course.courseId()))
                        .findFirst()
                        .flatMap(req -> students.stream()
                                .filter(student -> !student.enrolledCourseIds().contains(req))
                                .findFirst()
                                .flatMap(student -> EnrollmentService.enrollStudent(course, student)))
                        .orElse(course))
                .collect(Collectors.toList());
    }
}

