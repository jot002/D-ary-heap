/*
 * Name: Jonathan Tran
 * PID:  A15967290
 */

import java.util.*;

/**
 * This class is created to implement a d-ary heap, which will provide even
 * faster insertion with the cost of possibly slower deletion. D-ary heap
 * is just a heap with branching factor d. dHeap uses a heap-structured
 * array to represent a heap and stores data of generic type T.
 * 
 * @param <T> Generic type
 */
public class dHeap<T extends Comparable<? super T>> implements dHeapInterface<T> {

    private T[] heap; // heap array
    private int d; // branching factor
    private int nelems; // number of elements
    private boolean isMaxHeap; // boolean to indicate whether heap is max or min
    private int CAPACITY = 6;
    private int BINARY = 2;
    private int DOUBLE = 2;

    /**
     * Initializes a binary max heap with capacity = 6
     */
    @SuppressWarnings("unchecked")
    public dHeap() {
        this.heap = (T[]) new Comparable[CAPACITY];
        this.nelems = 0;
        this.isMaxHeap = true;
        this.d = BINARY;
    }

    /**
     * Initializes a binary max heap with a given initial capacity.
     *
     * @param heapSize The initial capacity of the heap.
     */
    @SuppressWarnings("unchecked")
    public dHeap(int heapSize) {
        this.heap = (T[]) new Comparable[heapSize];
        this.nelems = 0;
        this.isMaxHeap = true;
        this.d = BINARY;
    }

    /**
     * Initializes a d-ary heap (with a given value for d), with a given initial
     * capacity.
     *
     * @param d         The number of child nodes each node in the heap should have.
     * @param heapSize  The initial capacity of the heap.
     * @param isMaxHeap indicates whether the heap should be max or min
     * @throws IllegalArgumentException if d is less than one.
     */
    @SuppressWarnings("unchecked")
    public dHeap(int d, int heapSize, boolean isMaxHeap) throws IllegalArgumentException {
        this.heap = (T[]) new Comparable[heapSize];
        this.nelems = 0;
        this.isMaxHeap = isMaxHeap;
        this.d = d;
    }

    /**
     * Returns the number of elements stored in the heap.
     *
     * @return the number of elements stored in the heap
     */
    @Override
    public int size() {
        return this.nelems;
    }

    /**
     * Adds the given data to the heap.
     *
     * @param data a generic type that will be added into the heap
     * @exception NullPointerException if data is null
     */
    @Override
    public void add(T data) throws NullPointerException {
        if (data == null) {
            throw new NullPointerException();
        }
        // resize if needed
        resize();
        // adds the value to the end
        this.heap[this.nelems] = data;
        this.nelems++;
        // adjusts the location of the value
        bubbleUp(this.nelems-1);
    }

    /**
     * Returns and removes the root element from the heap.
     *
     * @return T the root element
     * @exception NoSuchElementException if the heap is empty
     */
    @Override
    public T remove() throws NoSuchElementException {
        if (this.nelems == 0) {
            throw new NoSuchElementException();
        }
        // get the root
        T rootElem = this.heap[0];
        // places the last value at the root
        this.heap[0] = this.heap[this.nelems-1];
        this.nelems--;
        trickleDown(0);
        return rootElem;
    }

    /**
     * Clear all elements in the heap.
     *
     */
    @SuppressWarnings("unchecked")
    @Override
    public void clear() {
        this.nelems = 0;
    }

    /**
     * Returns the root element of the heap.
     *
     * @return T the root element of the heap
     * @exception NoSuchElementException if the heap is empty
     */
    public T element() throws NoSuchElementException {
        if (this.nelems == 0) {
            throw new NoSuchElementException();
        }
        return this.heap[0];
    }

    /**
     * Swaps values of the parent and child
     *
     * @param index of the place to start comparing from
     */
    private void trickleDown(int index) {
        int childIndex = (this.d * index) + 1;
        T elem = this.heap[index];
        // keeps going as long as it is in the heap
        while (childIndex < this.nelems) {
            T neededValue = elem;
            int maxMin = -1;
            // goes through each child
            for (int i = 0; i < d && i + childIndex < this.nelems; i++) {
                if (isMaxHeap) {
                    // get the max value and index
                    if (this.heap[i + childIndex].compareTo(neededValue) > 0) {
                        neededValue = this.heap[i + childIndex];
                        maxMin = i + childIndex;
                    }
                }
                else {
                    // gets the min value and index
                    if (this.heap[i + childIndex].compareTo(neededValue) < 0) {
                        neededValue = this.heap[i + childIndex];
                        maxMin = i + childIndex;
                    }
                }
            }
            if (neededValue == elem) {
                return;
            }
            // swap the values
            else {
                T temp = this.heap[index];
                this.heap[index] = this.heap[maxMin];
                this.heap[maxMin] = temp;
                // updates the indices so the while loop can compare
                // new values
                index = maxMin;
                childIndex = (d * index) + 1;
            }
        }
    }

    /**
     * Swaps values of the parent and child
     *
     * @param index of the place to start comparing from
     */
    private void bubbleUp(int index) {
        int parentIndex = parent(index);
        if (isMaxHeap) {
            // keeps going as long as the child is greater than the parent
            while (index > 0 && this.heap[parentIndex]
                    .compareTo(this.heap[index]) < 0) {
                // swaps the values
                T temp = this.heap[index];
                this.heap[index] = this.heap[parentIndex];
                this.heap[parentIndex] = temp;
                // updates the indices to compare other values
                index = parentIndex;
                parentIndex = parent(index);
            }
        }
        else {
            // keeps going as long as the child is less than the parent
            while (index > 0 && this.heap[parentIndex]
                    .compareTo(this.heap[index]) > 0) {
                // swaps the values
                T temp = this.heap[index];
                this.heap[index] = this.heap[parentIndex];
                this.heap[parentIndex] = temp;
                // updates the indices to compare other values
                index = parentIndex;
                parentIndex = parent(index);
            }
        }
    }

    /**
     * Doubles the size of array before adding if is full
     *
     */
    @SuppressWarnings("unchecked")
    private void resize() {
        // checks if it is full
        if (this.nelems == this.heap.length) {
            // doubles the size of the heap
            T[] newHeap = (T[]) new Comparable[DOUBLE * this.heap.length];
            for (int i = 0; i < this.heap.length; i++) {
                newHeap[i] = this.heap[i];
            }
            this.heap = newHeap;
        }
    }

    /**
     * Gets the index of the parent
     *
     * @param index of the child
     */
    private int parent(int index) {
        return (index - 1) / this.d;
    }

    /**
     * Displays the heap for better visualization
     *
     */
    public void heapRepresentation() {
        for (int i = 0; i < this.nelems; i++) {
            System.out.print(this.heap[i] + ",");
        }
        System.out.println();
    }
}
