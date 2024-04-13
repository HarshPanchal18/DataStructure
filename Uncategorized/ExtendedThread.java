package Uncategorized;

public class ExtendedThread {
    public static void main(String[] args) {
        NewThread nt = new NewThread();
        nt.run();

        try {
            for (int i = 5; i > 0; i--) {
                System.out.println("Main Thread: " + i);
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            System.out.println("Main thread interrupted");
        }

        System.out.println("Main thread exiting");

    }
}

class NewThread implements Runnable {

    NewThread() { // Create a new , second thread
        // super("Demo Thread");
        System.out.println("Child Thread : " + this);
    }

    @Override
    public void run() {

        try {
            for (int i = 5; i > 0; i--) {
                System.out.println("Child Thread : " + i);
                Thread.sleep(500);
            }
        } catch (Exception e) {
            System.out.println("Child interrupted");
        }
        System.out.println("Exiting child thread");

    }

}