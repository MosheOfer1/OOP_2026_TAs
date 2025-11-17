package TA3;
import java.util.*;

import static java.util.Collections.sort;

class Student implements Comparable<Student> {
    private String name;
    private int age;
    private double grade;

    public Student(String name, int age, double grade) {
        this.name = name;
        this.age = age;
        this.grade = grade;
    }

    // Getters
    public String getName() { return name; }
    public int getAge() { return age; }
    public double getGrade() { return grade; }

    // Comparable (natural order: by name)
    @Override
    public int compareTo(Student other) {
        return this.name.compareTo(other.name);
    }

    @Override
    public String toString() {
        return String.format("%-10s | Age: %2d | Grade: %.2f", name, age, grade);
    }
}

public class RandomClassroom {

    public static void main(String[] args) {
        Random rand = new Random();
        List<Student> students = new ArrayList<>();

        // Generate 10 random students for demo
        for (int i = 0; i < 10; i++) {
            students.add(new Student("Student" + i, 18 + rand.nextInt(10), 50 + rand.nextDouble() * 50));
        }

        System.out.println("=== Original ===");
        students.forEach(System.out::println);

        sort(students);




        // 1️⃣ Anonymous inner class
        sort(students, new Comparator<Student>() {
            @Override
            public int compare(Student s1, Student s2) {
                return Integer.compare(s1.getAge(), s2.getAge());
            }
        });
        System.out.println("\n1️⃣ Sorted by Age (Anonymous Class):");
        students.forEach(System.out::println);

        // 2️⃣ Lambda expression
        students.sort((s1, s2) -> Double.compare(s2.getGrade(), s1.getGrade()));
        System.out.println("\n2️⃣ Sorted by Grade Descending (Lambda):");
        students.forEach(System.out::println);

        // 3️⃣ Using Comparator.comparing()
        students.sort(Comparator.comparing(Student::getName));
        System.out.println("\n3️⃣ Sorted by Name (Comparator.comparing):");
        students.forEach(System.out::println);

        // 4️⃣ Using method chaining (multiple criteria)
        students.sort(
                Comparator.comparing(Student::getGrade).reversed()   // by grade descending
                        .thenComparing(Student::getAge)           // then by age ascending
                        .thenComparing(Student::getName)          // then by name
        );
        System.out.println("\n4️⃣ Sorted by Grade ↓, Age ↑, Name ↑ (Chained Comparator):");
        students.forEach(System.out::println);
    }
}
