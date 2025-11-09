package TA3;

import java.awt.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Objects;

class Person implements Comparable<Person> {
    private int id;
    private String name;
    private int age;

    // Constructor
    public Person(int id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    // Getters

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    /**
     * Implementing Comparators by fields
     **/
    // Comparator for sorting by age
    public static Comparator<Person> ageComparator = Comparator.comparingInt(Person::getAge);

    // Comparator for sorting by name
    public static Comparator<Person> nameComparator = Comparator.comparing(Person::getName);

    // Comparator for sorting by id
    public static Comparator<Person> idComparator = Comparator.comparingInt(Person::getId);

    @Override
    public int compareTo(Person o) {
        return Integer.compare(this.age, o.age);
    }


    // Override toString method
    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }


}