package Sort;

import java.util.Arrays;

class BubbleSortImproved {

    public static void main(String args[]) {

        String[] test = {"Ada", "C++", "Lisp", "Java", "Scala"};
        System.out.println("Before Sorting : " + Arrays.toString(test));
        bubbleSortImproved(test);
        System.out.println("After Sorting : " + Arrays.toString(test));
    }

    /*
     * An improved implementation of Bubble Sort algorithm, which will only do
     * 1 pass and n-1 comparison if array is already sorted. 
     */
    public static void bubbleSortImproved(String[] names) {        
        boolean swapped = true;
        int last = names.length - 2;

        // only continue if swapping of number has occurred
        while (swapped) {
            swapped = false;
            
            for (int i = 0; i <= last; i++) {
                if (names[i].compareTo(names[i + 1]) > 0) {
                    
                    // pair is out of order, swap them
                    swap(names, i, i + 1);
                    
                    swapped = true; // swapping occurred
                }
            }

            // after each pass largest element moved to end of array
            last--;
        }

    }

    public static void swap(String[] names, int fromIdx, int toIdx) {
        String temp = names[fromIdx]; // exchange
        names[fromIdx] = names[toIdx];
        names[toIdx] = temp;
    }

}