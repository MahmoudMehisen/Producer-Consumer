import java.util.concurrent.BlockingQueue;

public class ProducerConsumerBlockingQueue {
    static class producer implements Runnable {
        BlockingQueue<Integer> obj;
        public producer(BlockingQueue<Integer> obj)
        {
            this.obj = obj;
        }
        @Override public void run()
        {
            for (int i = 1; i <= 5; i++) {
                try {
                    obj.put(i);
                    System.out.println("Produced " + i);
                }
                catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    static class consumer implements Runnable {
        BlockingQueue<Integer> obj;
        int taken = -1;
        public consumer(BlockingQueue<Integer> obj)
        {
            this.obj = obj;
        }
        @Override public void run()
        {
            while (taken != 5) {
                try {
                    taken = obj.take();
                    System.out.println("Consumed " + taken);
                }
                catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
