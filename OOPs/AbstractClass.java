package OOPs;

abstract class A {
    abstract void call();

    void callMeToo() {
        System.out.println("Concrete method");
    }
}

class B extends A {

    @Override
    void call() {
        System.out.println("B is calling call()");
    }
}

public class AbstractClass {

    public static void main(String[] args) {
        B b = new B();
        b.call();
        b.callMeToo();
    }

}
