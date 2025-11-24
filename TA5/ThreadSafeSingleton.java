package TA5;

public class ThreadSafeSingleton {

    // 1. Private static variable to hold the single instance.
    // 'volatile' ensures that multiple threads handle the instance variable
    // correctly when it's being initialized.
    private static volatile ThreadSafeSingleton instance;

    // 2. Private constructor to prevent direct instantiation.
    private ThreadSafeSingleton() {
        // Optional: Add a check here to prevent instantiation via Reflection
        if (instance != null) {
            throw new IllegalStateException("Cannot create a new instance, use getInstance() method.");
        }
        System.out.println("Singleton instance created in Thread: " + Thread.currentThread().getName());
    }

    // 3. Public static method to provide access to the instance.
    public static ThreadSafeSingleton getInstance() {
        // First check (outside the synchronized block):
        // Highly efficient, most calls will return here without locking.
        if (instance == null) {
            // Synchronized block: Only entered on the first call or when instance is null.
            synchronized (ThreadSafeSingleton.class) {
                // Second check (inside the synchronized block):
                // Ensures that if multiple threads reach here, only one creates the instance.
                if (instance == null) {
                    instance = new ThreadSafeSingleton();
                }
            }
        }
        return instance;
    }

    // Example method to show the singleton is working
    public void showMessage() {
        System.out.println("Message from Singleton instance: " + this.hashCode());
    }

    public static void main(String[] args) {

        // Task 1: Create an instance in Thread 1
        Runnable task1 = () -> {
            ThreadSafeSingleton singleton1 = ThreadSafeSingleton.getInstance();
            singleton1.showMessage();
        };

        // Task 2: Create an instance in Thread 2
        Runnable task2 = () -> {
            ThreadSafeSingleton singleton2 = ThreadSafeSingleton.getInstance();
            singleton2.showMessage();
        };

        // Create and start two threads concurrently
        Thread t1 = new Thread(task1, "Thread-1");
        Thread t2 = new Thread(task2, "Thread-2");

        t1.start();
        t2.start();
    }
}