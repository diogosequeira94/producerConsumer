package org.academiadecodigo.bootcamp.concurrency;

import org.academiadecodigo.bootcamp.concurrency.bqueue.BQueue;

import java.util.concurrent.ScheduledExecutorService;

/**
 * Produces and stores integers into a blocking queue
 */
public class Producer implements Runnable {

    private final BQueue<Integer> queue;
    private volatile int elementNum;

    /**
     * @param queue      the blocking queue to add elements to
     * @param elementNum the number of elements to produce
     */
    public Producer(BQueue queue, int elementNum) {
        this.queue = queue;
        this.elementNum = elementNum;
    }



    @Override
    public void run() {

        produce();

    }

    public void produce() {

        while (elementNum != 0) {
            try {
                Thread.sleep(100);
                queue.offer(elementNum);
                elementNum--;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }


        }
    }
}
