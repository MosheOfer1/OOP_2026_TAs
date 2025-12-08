import java.util.ArrayList;
import java.util.Arrays;

public class MyArrayList<T> {
    private Object[] elements; // Array to store the elements
    private int size; // The number of elements in the list

    // Constructor
    public MyArrayList() {
        elements = new Object[10]; // Initialize the array with size 10
        size = 0;
    }

    // Method to add an element to the list
    public void add(T element) {
        if (size == elements.length) {
            // If the array is full, double its size
            elements = Arrays.copyOf(elements, elements.length * 2);
        }
        elements[size++] = element;
    }

    // Method to get an element by index
    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }
        return (T) elements[index]; // Cast to T since the internal array is of type Object[]
    }

    // Method to get the size of the list
    public int size() {
        return size;
    }

    // Method to print the list
    public void printList() {
        for (int i = 0; i < size; i++) {
            System.out.print(elements[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        MyArrayList<String> list = new MyArrayList<>();
        list.add("Apple");
        list.add("Banana");
        list.add("Cherry");

        System.out.println("List size: " + list.size());
        list.printList();

        System.out.println("Element at index 1: " + list.get(1));
    }
}
