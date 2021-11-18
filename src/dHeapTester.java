import java.util.NoSuchElementException;

import static org.junit.Assert.*;

public class dHeapTester {

    @org.junit.Test
    public void size() {
        dHeap array = new dHeap(5);
        assertEquals(0, array.size());
        array.add(6);
        assertEquals(1, array.size());
        array.add(2);
        assertEquals(2, array.size());
        array.add(8);
        assertEquals(3, array.size());
        array.add(25);
        assertEquals(4, array.size());
        array.add(3);
        assertEquals(5, array.size());
        array.add(87);
        assertEquals(6, array.size());
    }

    @org.junit.Test
    public void add() {
        dHeap array = new dHeap(5);
        assertEquals(0, array.size());
        array.add(6);
        assertEquals(1, array.size());
        array.add(2);
        assertEquals(2, array.size());
        array.add(8);
        assertEquals(3, array.size());
        array.add(25);
        assertEquals(4, array.size());
        array.add(3);
        assertEquals(5, array.size());
        array.add(87);
        assertEquals(6, array.size());
        array.add(453);
        array.add(12);
        array.add(30);
        array.add(8);
        array.heapRepresentation();
        dHeap array2 = new dHeap(3,5,true);
        array2.add(453);
        array2.add(12);
        array2.add(30);
        array2.add(8);
        array2.add(40);
        array2.add(1202);
        array2.add(300);
        array2.add(9);
        array2.add(1201);
        array2.add(41);
        array2.add(301);
        array2.add(7);
        array2.add(299);
        assertEquals(13, array2.size());
        array2.heapRepresentation();
    }

    @org.junit.Test(expected = NullPointerException.class)
    public void addFail() {
        dHeap array = new dHeap(5);
        array.add(null);
    }

    @org.junit.Test
    public void remove() {
        dHeap array2 = new dHeap(5,5,true);
        array2.add(453);
        array2.add(12);
        array2.add(30);
        array2.add(8);
        array2.add(40);
        array2.add(1202);
        array2.add(300);
        array2.add(9);
        array2.add(1201);
        assertEquals(9, array2.size());
        array2.heapRepresentation();
        assertEquals(1202,array2.remove());
        array2.heapRepresentation();
        assertEquals(1201,array2.remove());
        array2.heapRepresentation();
        assertEquals(453,array2.remove());
        array2.heapRepresentation();
        assertEquals(300,array2.remove());
        array2.heapRepresentation();
        assertEquals(40,array2.remove());
        array2.heapRepresentation();
        assertEquals(30,array2.remove());
        array2.heapRepresentation();
        assertEquals(12,array2.remove());
        array2.heapRepresentation();
    }

    @org.junit.Test(expected = NoSuchElementException.class)
    public void removeFail() {
        dHeap array = new dHeap(5);
        array.remove();
    }

    @org.junit.Test
    public void clear() {
        dHeap array = new dHeap(5);
        assertEquals(0, array.size());
        array.add(6);
        array.add(2);
        assertEquals(2, array.size());
        array.clear();
        assertEquals(0, array.size());
        array.heapRepresentation();
        dHeap array2 = new dHeap(5);
        array2.clear();
        assertEquals(0, array.size());
        dHeap array3 = new dHeap(5,5,true);
        array3.add(453);
        array3.add(12);
        array3.add(30);
        assertEquals(3, array3.size());
        array3.clear();
        assertEquals(0, array3.size());
    }

    @org.junit.Test
    public void element() {
        dHeap array2 = new dHeap(5,5,true);
        array2.add(453);
        array2.add(12);
        array2.add(30);
        array2.add(8);
        assertEquals(453, array2.element());
        array2.remove();
        assertEquals(30, array2.element());
        array2.remove();
        assertEquals(12, array2.element());
        array2.remove();
        assertEquals(8, array2.element());
    }

    @org.junit.Test(expected = NoSuchElementException.class)
    public void elementFail() {
        dHeap array = new dHeap(5);
        array.element();
    }
}