/*
 * Name: Jonathan Tran
 * PID:  A15967290
 */

import java.util.*;

/**
 * TODO
 * 
 * @param <T> Generic type
 */
public class dHeap<T extends Comparable<? super T>> implements dHeapInterface<T> {

    private T[] heap; // heap array
    private int d; // branching factor
    private int nelems; // number of elements
    private boolean isMaxHeap; // boolean to indicate whether heap is max or min
    private int MAX = 1000000000;

    /**
     * Initializes a binary max heap with capacity = 6
     */
    @SuppressWarnings("unchecked")
    public dHeap() {
        this.heap = (T[]) new Comparable[6];
        this.nelems = 0;
        this.isMaxHeap = true;
        this.d = 2;
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
        this.d = 2;
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

    @Override
    public int size() {
        return this.nelems;
    }

    @Override
    public void add(T data) throws NullPointerException {
        if (data == null) {
            throw new NullPointerException();
        }
        resize();
        this.heap[this.nelems] = data;
        this.nelems++;
        bubbleUp(this.nelems-1);
    }

    @Override
    public T remove() throws NoSuchElementException {
        if (this.nelems == 0) {
            throw new NoSuchElementException();
        }
        T rootElem = this.heap[0];
        this.heap[0] = this.heap[this.nelems-1];
        this.nelems--;
        trickleDown(0);
        return rootElem;
    }

    @SuppressWarnings("unchecked")
    @Override
    public void clear() {
        this.nelems = 0;

    }

    public T element() throws NoSuchElementException {
        if (this.nelems == 0) {
            throw new NoSuchElementException();
        }
        return this.heap[0];
    }

    private void trickleDown(int index) {
        int firstChild = this.d * index + 1;
        int lastChild = this.d * index + this.d;
        T maxChild = this.heap[index];
        int max=0;
        for (int i = firstChild; i <= lastChild; i++) {
            if (this.heap[i].compareTo(maxChild) > 0) {
                maxChild = this.heap[i];
                max = i;
            }
        }
//        for (int i = firstChild; i <= lastChild; i++) {
//            if (this.heap[i].compareTo(max) == 0) {
//                T temp = this.heap[i];
//                this.heap[i] = this.heap[main];
//                this.heap[main] = temp;
//                break;
//            }
//        }

        T tempRoot = this.heap[index];       // save root in temp variable
        while ((max < this.nelems) && (this.heap[max].compareTo(tempRoot) > 0)){
            this.heap[index] = this.heap[max];
            index = max;

            int firstMaxChild = this.d * index + 1;
            int lastMaxChild = this.d * index + this.d;
            for (int i = firstChild; i <= lastChild; i++) {
                if (this.heap[i].compareTo(maxChild) > 0) {
                    maxChild = this.heap[i];
                    max = i;
                }
            }
            T temp = this.heap[max];
                this.heap[max] = this.heap[index];
                this.heap[index] = temp;

        this.heap[index] = tempRoot;
    }
    }

    private void bubbleUp(int index) {
        int parentIndex = parent(index);
        while (index > 0 && this.heap[parentIndex].compareTo(this.heap[index]) < 0) {
            T temp = this.heap[index];
            this.heap[index] = this.heap[parentIndex];
            this.heap[parentIndex] = temp;
            index = parentIndex;
            parentIndex = parent(index);
        }
    }

    @SuppressWarnings("unchecked")
    private void resize() {
        if (this.nelems == this.heap.length) {
            T[] newHeap = (T[]) new Comparable[2 * this.heap.length];
            for (int i = 0; i < this.heap.length; i++) {
                newHeap[i] = this.heap[i];
            }
            this.heap = newHeap;
        }
    }

    private int parent(int index) {
        return (index - 1) / this.d;
    }


    public void heapRepresentation() {
        for (int i = 0; i < this.nelems; i++) {
            System.out.print(this.heap[i] + ",");
        }
        System.out.println();
    }
}
