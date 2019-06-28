package org.academiadecodigo.bootcamp.concurrency;

import org.academiadecodigo.bootcamp.concurrency.bqueue.BQueue;

/**
 * Consumer of integers from a blocking queue
 */
public class Consumer implements Runnable {

    private final BQueue<Integer> queue;
    private volatile int elementNum;

    /**
     * @param queue      the blocking queue to consume elements from
     * @param elementNum the number of elements to consume
     */
    public Consumer(BQueue queue, int elementNum) {
        this.queue = queue;
        this.elementNum = elementNum;
    }

    @Override
    public void run() {

        consume();
    }

    public void consume() {



        while (elementNum != 0){

            try{
                Thread.sleep(200);
                queue.poll();
                elementNum --;
            } catch (InterruptedException e){
                e.printStackTrace();
            }



        }

    }

}



