public class MaxFinder<T extends Comparable<T>> {
    public T findMax(T a, T b) {
        return (a.compareTo(b) > 0) ? a : b;
    }


    public static void main(String[] args) {
        MaxFinder<Integer> intMaxFinder = new MaxFinder<>();
        System.out.println("Max of 10 and 20: " + intMaxFinder.findMax(10, 20));

        MaxFinder<String> stringMaxFinder = new MaxFinder<>();
        System.out.println("Max of 'Apple' and 'Banana': " + stringMaxFinder.findMax("Apple", "Banana"));
    }
}
