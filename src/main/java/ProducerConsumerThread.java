// Java program to implement the solution of producer consumer problem.

import java.util.LinkedList;

public class ProducerConsumerThread {

    // list shared by producer and consumer
    // Size of the list is 1.
    LinkedList<Integer> list = new LinkedList<>();
    int capacity = 1;

    // Function called by producer thread
    public void produce() throws InterruptedException {
        int value = 0;
        while (true) {
            synchronized (this) {
                // producer thread waits while list is full
                while (list.size() == capacity)
                    wait();
                System.out.println("Producer produced: " + value);

                // to insert the jobs in the list
                list.add(value++);

                // notifies the consumer thread that
                // now it can start consuming
                notify();

                // makes the working of program easier
                // to understand
                Thread.sleep(1000);
            }
        }
    }

    // Function called by consumer thread
    public void consume() throws InterruptedException {
        while (true) {
            synchronized (this) {
                // consumer thread waits while list is empty
                while (list.size() == 0)
                    wait();
                // to retrieve the first job in the list
                int val = list.removeFirst();

                System.out.println("Consumer consumed: " + val);
                // Wake up producer thread
                notify();
                //sleep
                Thread.sleep(1000);
            }
        }
    }

}

