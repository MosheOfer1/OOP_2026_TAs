//import java.util.List;
//
//public class SuperExample {
//    public static void printList(List<? super Dog> list) {
//        for (Object item : list) {
//            // Cannot directly access item as Dog, must cast
//            System.out.println(item);
//        }
//    }
//
//    public static void main(String[] args) {
//        List<Object> animals = List.of(new Dog(), new Dog());
//        printList(animals); // Works, because Animal is a supertype of Dog
//
//        List<Object> objects = List.of(new Dog(), new Dog());
//        printList(objects); // Works, because Object is a supertype of Dog
//    }
//}
