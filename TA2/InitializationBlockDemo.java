public class InitializationBlockDemo {

    // Static variable
    static int staticCount;

    // Instance variable
    int instanceCount;

    // Static initialization block
    static {
        System.out.println("Static initialization block executed");
        staticCount = 10; // Initializing static variable
    }

    // Instance initialization block
    {
        System.out.println("Instance initialization block executed");
        instanceCount = 20; // Initializing instance variable
    }

    // Constructor
    public InitializationBlockDemo() {
        System.out.println("Constructor executed");
        instanceCount += 5;
        staticCount += 5;
    }

    // Main method to test the blocks
    public static void main(String[] args) {
        System.out.println("Creating first instance:");
        InitializationBlockDemo demo1 = new InitializationBlockDemo();

        System.out.println("\nCreating second instance:");
        InitializationBlockDemo demo2 = new InitializationBlockDemo();
    }
}
