package TA3;

import java.util.Comparator;

/**
 * Implementing Comparators by outer class
 */
public class AgeComparator implements Comparator<Person> {
    @Override
    public int compare(Person o1, Person o2) {
        return Integer.compare(o1.getAge(), o2.getAge());
    }
}
