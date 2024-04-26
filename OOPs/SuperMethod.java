package OOPs;

public class SuperMethod {
    public static void main(String[] args) {
        Child ch = new Child(41, 65);
        ch.show();
    }
}

class Parent {
    int i;
}

class Child extends Parent {
    int i; // Will shadow the parent 'i'

    Child(int a, int b) {
        super.i = a;
        i = b;
    }

    void show() {
        System.out.println("i in superclass: " + super.i);
        System.out.println("i in subclass: " + i);
    }
}
