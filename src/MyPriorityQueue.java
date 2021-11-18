/*
 * Name: Jonathan Tran
 * PID:  A15967290
 */

/**
 * MyPriorityQueue is a class that uses the dHeap class to implement the
 * priority queue operations. The priority queue uses a 5-ary max-heap
 * for its implementation. The constructor for MyPriorityQueue takes
 * one argument: the initial size of the queue
 *
 * @param <T> Generic type
 */
public class MyPriorityQueue<T extends Comparable<? super T>> {

    private dHeap<T> pQueue;

    /**
     * Constructor that creates a new priority queue
     * 
     * @param initialSize the given size
     */
    public MyPriorityQueue(int initialSize) {
        this.pQueue = new dHeap<T>(5, initialSize,true);
    }

    /**
     * Inserts an element into the Priority Queue. The element received cannot be
     * null.
     *
     * @param element Element to be inserted.
     * @throws NullPointerException if the element received is null.
     * @return returns true
     */
    public boolean offer(T element) throws NullPointerException {
        if (element == null) {
            throw new NullPointerException();
        }
        this.pQueue.add(element);
        return true;
    }

    /**
     * Retrieves the head of this Priority Queue (largest element), or null if the
     * queue is empty.
     *
     * @return The head of the queue (largest element), or null if queue is empty.
     */
    public T poll() {
        if (this.pQueue.size() == 0) {
            return null;
        }
        return this.pQueue.remove();
    }

    /**
     * Clears the contents of the queue
     */
    public void clear() {
        this.pQueue.clear();
    }

    /**
     * Retrieves, but does not remove, the head of this queue, or returns null if
     * this queue is empty.
     * 
     * @return the next item to be removed, null if the queue is empty
     */
    public T peek() {
        if (this.pQueue.size() == 0) {
            return null;
        }
        return this.pQueue.element();
    }

    /**
     * Indicates whether the queue is empty.
     * @return true is the queue is empty, false otherwise
     */
    public boolean isEmpty() {
        if (this.pQueue.size() == 0) {
            return true;
        }
        return false;
    }
}
