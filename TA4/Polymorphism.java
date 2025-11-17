public class Polymorphism {
    public static void main(String[] args) {
        A a = new A();
        A b = new B();
        A c = new C();

        a.f();
        b.f();
        c.f();

        A[] arr = {a, b, c};
        for(A o : arr) {
            o.f();
        }

    }
}

    class A {
        public void f(){
            System.out.println("A");
        }
    }
    class B extends A{
    public void f(){
            System.out.println("B");
        }
    }
    class C extends B{
        public void f(){
            System.out.println("C");
        }
    }

