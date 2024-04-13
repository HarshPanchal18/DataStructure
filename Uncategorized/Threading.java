//package Uncategorized;

public class Threading {
    public static void main(String[] args) {
        NewThread t1 = new NewThread("One");
        NewThread t2 = new NewThread("Two");
        // NewThread t3 = new NewThread("Three");
        // NewThread t4 = new NewThread("Four");

        t1.t.start();
        t2.t.start();
        // t3.t.start();
        // t4.t.start();

        System.out.println("Thread one is alive: " + t1.t.isAlive());
        System.out.println("Thread two is alive: " + t2.t.isAlive());
        // System.out.println("Thread three is alive: " + t3.t.isAlive());
        // System.out.println("Thread four is alive: " + t4.t.isAlive());

        try {

            System.out.println("Waiting for threads to finish");

            t1.t.join(); // join() - Waits for this thread to be idle.
            t2.t.join();
            // t3.t.join();
            // t4.t.join();

        } catch (InterruptedException e) {
            System.out.println("Main thread interrupted");
        }

        System.out.println("Thread one is alive: " + t1.t.isAlive());
        System.out.println("Thread two is alive: " + t2.t.isAlive());
        // System.out.println("Thread three is alive: " + t3.t.isAlive());
        // System.out.println("Thread four is alive: " + t4.t.isAlive());

        System.out.println("Main thread exiting");

        currentThread();
    }

    public static void currentThread() {

        Thread t = Thread.currentThread(); // Returns a reference to the currently executing thread object.

        System.out.println("Current thread : " + t);
        t.setName("My Thread");
        System.out.println("After name change : " + t);
    }
}

class NewThread implements Runnable {

    String name;
    Thread t;

    NewThread() {
    }

    NewThread(String threadName) {
        name = threadName;
        t = new Thread(this, name);
        System.out.println("Thread: " + t);
    }

    @Override
    public void run() {
        try {
            for (int i = 3; i >= 1; i--) {
                System.out.println(name + " " + i);
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            System.out.println(name + " Interrupted");
        }
        System.out.println(name + " Exiting");
    }
}