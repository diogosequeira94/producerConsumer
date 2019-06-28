package org.academiadecodigo.bootcamp.concurrency.bqueue;

import java.util.LinkedList;

/**
 * Blocking Queue
 *
 * @param <T> the type of elements stored by this queue
 */
public class BQueue<T> {

    /**
     * Constructs a new queue with a maximum size
     *
     * @param limit the queue size DONE
     */
    private LinkedList<T> integerLinkedList;
    private int limit;

    public BQueue(int limit) {

        /* ArrayBlockingQueue is a classic "bounded buffer",
        in which a fixed-sized array holds elements inserted by producers and extracted by consumers.
        This class supports an optional fairness policy for ordering waiting producer and consumer threads
         */

        this.limit = limit;
        integerLinkedList = new LinkedList<>();
        System.out.println("Queue was created with a limit of " + limit + " elements");

    }

    /**
     * Inserts the specified element into the queue
     * Blocking operation if the queue is full
     *
     * @param data the data to add to the queue
     */

    public synchronized void offer(T data) {


            while (getLimit() == getSize()) {


                //has to wait
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (IllegalMonitorStateException e) {
                    e.printStackTrace();
                }


            }

         //Keeps checking until condition changes

            System.out.println("Element added to the queue, it now has: " + getSize() );
            integerLinkedList.add(data);
            notifyAll();

        }



    /**
     * Retrieves and removes data from the head of the queue
     * Blocking operation if the queue is empty
     *
     * @return the data from the head of the queue
     */
    public synchronized T poll() {


            while (getLimit() == 0) {

                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (IllegalMonitorStateException e) {
                    e.printStackTrace();
                }


            }


            notifyAll();


        T element = integerLinkedList.poll();
        System.out.println("Element removed from head of the queue");

        if(getSize() > 1){
            System.out.println("Queue now at: " + (getSize() -1) + " capacity.");

        } else {
            System.out.println("Queue now at: " + getSize() + " capacity.");
        }

        return element;

    }

    /**
     * Gets the number of elements in the queue
     *
     * @return the number of elements
     */
    public int getSize() {

        return integerLinkedList.size();

    }

    /**
     * Gets the maximum number of elements that can be present in the queue
     *
     * @return the maximum number of elements
     */
    public int getLimit() {

        return limit;

    }

}
