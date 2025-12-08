public class Box<T> {
    private T item;

    public void setItem(T item) {
        this.item = item;
    }

    public T getItem() {
        return item;
    }

    public static void main(String[] args) {
        Box<String> stringBox = new Box<>();
        stringBox.setItem("Hello");
        System.out.println("String Box contains: " + stringBox.getItem());

        Box<Integer> integerBox = new Box<>();
        integerBox.setItem(42);
        System.out.println("Integer Box contains: " + integerBox.getItem());
    }
}
