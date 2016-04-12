package ru.mars.Sort;

import java.util.Arrays;


/**
* Java program to Sort integer array using QuickSort algorithm using recursion.
* Recursive QuickSort algorithm, partitioned list into two parts by a pivot,
* and then recursively sorts each list.
* @author Javin Paul
*/
public class QuickSort{

    public static void main(String args[]) {

        int[] input = { 23, 31, 1, 21, 36, 72};
        int[] input2 = { 1,1, 1, 23, 1, 31, 1, 21, 1, 1, 36, 1, 72, 1};
        System.out.println("Before sorting : " + Arrays.toString(input));
        quickSort(input); // sort the integer array using quick sort algorithm
        System.out.println("After sorting : " + Arrays.toString(input));
        System.out.println("2Before sorting : " + Arrays.toString(input2));
        quickSort(input2); // sort the integer array using quick sort algorithm
        System.out.println("2After sorting : " + Arrays.toString(input2));
        Arrays.sort(input2);
     
        // input with duplicates
        int[] withDuplicates = { 11, 14, 16, 12, 11, 15};
        System.out.println("Before sorting : " + Arrays.toString(withDuplicates));
        quickSort(withDuplicates); // sort the integer array using quick sort algorithm
        System.out.println("After sorting : " + Arrays.toString(withDuplicates));
    }

    /**
     * public method exposed to client, sorts given array using QuickSort
     * Algorithm in Java
     * @param array
     */
    public static void quickSort(int[] array) {
        recursiveQuickSort(array, 0, array.length - 1);
    }

    /**
     * Recursive quicksort logic
     *
     * @param array input array
     * @param startIdx start index of the array
     * @param endIdx end index of the array
     */
    public static void recursiveQuickSort(int[] array, int startIdx, int endIdx) {
     
        int idx = partition(array, startIdx, endIdx);

        // Recursively call quicksort with left part of the partitioned array
        if (startIdx < idx - 1) {
            recursiveQuickSort(array, startIdx, idx - 1);
        }

        // Recursively call quick sort with right part of the partitioned array
        if (endIdx > idx) {
            recursiveQuickSort(array, idx, endIdx);
        }
    }

    /**
     * Divides array from pivot, left side contains elements less than
     * Pivot while right side contains elements greater than pivot.
     *
     * @param array array to partitioned
     * @param left lower bound of the array
     * @param right upper bound of the array
     * @return the partition index
     */
    public static int partition(int[] array, int left, int right) {
        int pivot = array[left]; // taking first element as pivot

        while (left <= right) {
            //searching number which is greater than pivot, bottom up
            while (array[left] < pivot) {
                left++;
            }
            //searching number which is less than pivot, top down
            while (array[right] > pivot) {
                right--;
            }

            // swap the values
            if (left <= right) {
                int tmp = array[left];
                array[left] = array[right];
                array[right] = tmp;

                //increment left index and decrement right index
                left++;
                right--;
            }
        }
        return left;
    }
}