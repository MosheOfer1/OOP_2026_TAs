package TA5;

// Singleton class representing God
class God {
    private static God instance;

    // Private constructor to prevent instantiation
    private God() {
    }

    // Method to get the single instance of the class
    public static God createGod() {
        if (instance == null) {
            instance = new God();
        }
        return instance;
    }

    // Method to create the world
    public void createWorld() {
        System.out.println("Creating the world");
    }

    // Method to destroy the world
    public void destroyWorld() {
        System.out.println("Destroying the world");
    }
}
    public class Singelton{

    public static void main(String[] args) {
        // Using the Singleton pattern to get the same instance
        God godInstance1 = God.createGod();
        godInstance1.createWorld();

        God godInstance2 = God.createGod();
        godInstance2.destroyWorld();

        // Both instances should be the same
        System.out.println(godInstance1 == godInstance2);  // Output: true
    }
}
