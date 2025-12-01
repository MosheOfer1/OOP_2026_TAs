package TA6;

import java.util.Iterator;
import java.util.NoSuchElementException;

// Custom list of names
class NameList implements Iterable<String> {
    private String[] names;
    private int size;

    // Constructor to define the capacity of the list
    public NameList(int capacity) {
        names = new String[capacity];
        size = 0;
    }

    // Add a name to the list
    public void add(String name) {
        if (size >= names.length) {
            throw new IllegalStateException("List is full!");
        }
        names[size++] = name;
    }

    // Implementing the iterator method for NameList
    @Override
    public Iterator<String> iterator() {
        return new NameListIterator();
    }

    // Internal iterator for NameList
    private class NameListIterator implements Iterator<String> {
        private int currentIndex = 0;

        @Override
        public boolean hasNext() {
            return currentIndex < size;
        }

        @Override
        public String next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return names[currentIndex++];
        }
    }
}

// Demo code
public class SimpleIterator {
    public static void main(String[] args) {
        NameList nameList = new NameList(5);

        // Adding names to the list
        nameList.add("Alice");
        nameList.add("Bob");
        nameList.add("Charlie");

        // Using foreach loop to iterate through the names
        System.out.println("Names in the list:");
        for (String name : nameList) {
            System.out.println(name);
        }

        // Manual iteration using Iterator
        System.out.println("\nManual iteration:");
        Iterator<String> iterator = nameList.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }
}

