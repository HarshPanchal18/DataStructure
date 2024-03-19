import java.util.concurrent.Semaphore;

class SharedResource {
    private int capacity;
    private int item;
    private final Semaphore emptyPermits; // Semaphores accessible here
    private final Semaphore fullPermits;

    public SharedResource(int capacity, Semaphore emptyPermits, Semaphore fullPermits) {
        this.capacity = capacity;
        this.emptyPermits = emptyPermits;
        this.fullPermits = fullPermits;
    }

    public int getCapacity() {
        return capacity;
    }

    public synchronized void put(int item) throws InterruptedException {
        emptyPermits.acquire(); // Acquire permit for an empty slot

        this.item = item;
        System.out.println("Produced: " + item);

        fullPermits.release(); // Release permit for a full slot
    }

    public synchronized int get() throws InterruptedException {
        fullPermits.acquire(); // Acquire permit for a full slot

        int item = this.item;
        System.out.println("Consumed: " + item);

        emptyPermits.release(); // Release permit for an empty slot
        return item;
    }
}

class Producer implements Runnable {
    Semaphore emptyPermits;
    private final SharedResource sharedResource;
    private final Semaphore fullPermits;

    public Producer(SharedResource sharedResource, Semaphore emptyPermits, Semaphore fullPermits) {
        this.sharedResource = sharedResource;
        this.emptyPermits = emptyPermits;
        this.fullPermits = fullPermits;
    }

    @Override
    public void run() {
        for (int i = 1; i <= 5; i++) { // Consume 5 items
            try {
                sharedResource.put(i);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class Consumer implements Runnable {
    private final SharedResource sharedResource;
    private final Semaphore emptyPermits;
    private final Semaphore fullPermits;

    public Consumer(SharedResource sharedResource, Semaphore emptyPermits, Semaphore fullPermits) {
        this.sharedResource = sharedResource;
        this.emptyPermits = emptyPermits;
        this.fullPermits = fullPermits;
    }

    @Override
    public void run() {
        for (int i = 0; i < 5; i++) { // Consume 5 items
            try {
                sharedResource.get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

public class ProducerConsumerProblem {
    public static void main(String[] args) {

        SharedResource sharedResource = new SharedResource(5, new Semaphore(5), new Semaphore(5)); // Buffer size of 5
        Semaphore emptyPermits = new Semaphore(sharedResource.getCapacity()); // Permits for empty slots
        Semaphore fullPermits = new Semaphore(0); // Permits for full slots (initially none)

        // Create threads for producer and consumer
        Thread producerThread = new Thread(new Producer(sharedResource, emptyPermits, fullPermits));
        Thread consumerThread = new Thread(new Consumer(sharedResource, emptyPermits, fullPermits));

        // Start the threads
        producerThread.start();
        consumerThread.start();
    }
}
