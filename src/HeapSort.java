/*
 * Name: Jonathan Tran
 * PID:  A15967290
 */

/**
 * The class HeapSort is a sorting algorithm that uses the heap structure
 * to sort the input integer array.
 */
public class HeapSort {

    /**
     * Sort the array arr between index start and end in ascending order.
     * This method must use a dHeap instance either uses a dHeap
     * instance or directly heapify the array.
     *
     * @param arr int array that needs to be sorted
     * @param start beginning of the position to be sorted
     * @param end the last position to be sorted
     */
    public static void heapSort(int[] arr, int start, int end) {
        dHeap sortingArray = new dHeap(2,5,false);
        // adds the values into the sorting array between index start and end
        for (int i = start; i <= end; i++) {
            sortingArray.add(arr[i]);
        }
        // switches the values between start and end to the values in
        // sorting array
        for (int i = start; i <= end; i++) {
            // sorts in ascending order
            arr[i] = (int) sortingArray.remove();
        }
    }
}
