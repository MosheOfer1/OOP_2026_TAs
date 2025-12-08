public class ExceptionExample1 {
    public static void main(String[] args) {
        try {
            System.out.println("Before dividing by zero");
            int result = 10 / 0; // This will cause an ArithmeticException
            System.out.println("After dividing by zero"); // This will not be executed
        } catch (ArithmeticException e) {
            System.out.println("1:  Caught exception: " + e.getMessage()); // Handles division
            throw e;// by zero
        } catch (Exception e) {
            System.out.println("2:  Caught exception: " + e.getMessage());
        }
        finally {
            System.out.println("Finally block executed"); // Always executed
        }

        System.out.println("Program continues after exception handling");
    }
}
