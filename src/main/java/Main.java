import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class Main {
    public static void main(String args[]){
        // semaphore();
        thread();
        //blockingQueue();
    }

   public static void semaphore(){
        // creating buffer queue
        Que q = new Que();
        // starting consumer thread
        new Consumer(q);
        // starting producer thread
        new Producer(q);
    }

    public static void thread() {
        // Object of a class that has both produce()
        // and consume() methods
        final ProducerConsumerThread producerConsumerThread = new ProducerConsumerThread();

        // producer thread
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run()
            {
                try {
                    producerConsumerThread.produce();
                }
                catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        // consumer thread
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run()
            {
                try {
                    producerConsumerThread.consume();
                }
                catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        // Start both threads
        t1.start();
        t2.start();
        // t1 finishes before t2
        try {
            t1.join();
            t2.join();
        }catch (InterruptedException e) {
            System.out.println("InterruptedException caught");
        }

    }

    public static void blockingQueue(){
        // Create an ArrayBlockingQueue object with capacity 5
        BlockingQueue<Integer> bqueue = new ArrayBlockingQueue<Integer>(5);

        // Create 1 object each for producer
        // and consumer and pass them the common
        // buffer created above
        ProducerConsumerBlockingQueue.producer p1 = new ProducerConsumerBlockingQueue.producer(bqueue);
        ProducerConsumerBlockingQueue.consumer c1 = new ProducerConsumerBlockingQueue.consumer(bqueue);
        // Create 1 thread each for producer
        // and consumer and pass them their
        // respective objects.
        Thread pThread = new Thread(p1);
        Thread cThread = new Thread(c1);
        // Start both threads
        pThread.start();
        cThread.start();
    }
}


