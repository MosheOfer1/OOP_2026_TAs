package QSolution;


public class Demo2 {
    public static void main(String[] args) {
        Interface2<String> computation2 = (p1, p2) -> p1 + p2;
        System.out.println("Result = " + computation2.compute("OO","P"));
    }
}
