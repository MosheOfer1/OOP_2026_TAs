import java.util.Arrays;

public class GenericMethods {
    public static <T> T getFirstElement(T[] array) {
        if (array.length == 0) {
            return null; // במקרה של מערך ריק
        }
        return array[0];
    }

    public static void main(String[] args) {
        String[] stringArray = {"Apple", "Banana", "Cherry"};
        Integer[] intArray = {10, 20, 30};
        class Person{}
        Person[] people = {new Person(), new Person()};
        System.out.println("people: "+ getFirstElement(people));
        System.out.println("First in String Array: " + getFirstElement(stringArray));
        System.out.println("First in Integer Array: " + getFirstElement(intArray));
    }
}
