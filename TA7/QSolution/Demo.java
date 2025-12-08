package QSolution;

public class Demo {
    public static void main(String[] args) {
        Interface1<Double,Integer> computation =
                (number1, number2)-> (double)number1/number2;
        System.out.println("Result1 = " + computation.compute(4,2));

        computation = (number1,number2)-> Math.pow(number1,number2);
        System.out.println("Result2 = " + computation.compute(4,2));
    }
}


