class ThrowExceptionExample{
    public static void throwExample() throws MyCustomException {
        throw new MyCustomException("This is a custom exception!");
    }
}

public class ExceptionExample2 {
    public static void main(String[] args) {
        try {
            ThrowExceptionExample.throwExample();
        } catch (MyCustomException e) {
            throw new RuntimeException(e);
        }
    }
}
