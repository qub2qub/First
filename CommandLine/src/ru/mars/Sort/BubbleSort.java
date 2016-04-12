package ru.mars.Sort;

import java.util.Arrays;

public class BubbleSort {
	
	static void bubbleSort1(int[] a) {
		for(int i = 0; i < a.length - 1; i++) {
		    for(int j = 0; j < a.length - i - 1; j++)
		        if(a[j] > a[j + 1]) {
		            int b = a[j];
		            a[j] = a[j + 1];
		            a[j + 1] = b;
		        }
		    System.out.printf("%d pass %s: %n", 
            		i+1, Arrays.toString(a));
		}
	}
	
	/*
     * In bubble sort we need n-1 iteration to sort n elements
     * at end of first iteration larget number is sorted and subsequently numbers smaller
     * than that.
     */
    public static void bubbleSort2(int[] unsorted){
        System.out.println("Before sorting : " 
        		+ Arrays.toString(unsorted));
     
        // Outer loop - need n-1 iteration to sort n elements
        for(int i=0; i<unsorted.length -1; i++){
         
            //Inner loop to perform comparision and swapping 
        	//between adjacent numbers
            //After each iteration one index from last is sorted
            for(int j= 1; j<unsorted.length -i; j++){
             
                //If current number is greater than swap those two
                if(unsorted[j-1] > unsorted[j]){
                    int temp = unsorted[j];
                    unsorted[j] = unsorted[j-1];
                    unsorted[j-1] = temp;
                }
            }
            System.out.printf("After %d pass %s: %n", 
            		i+1, Arrays.toString(unsorted));
        }
    }
    
    /*
     * This method sort the integer array using bubble sort algorithm
     */
    public static void bubbleSort3(int[] numbers) {
        System.out.printf("Unsorted array in Java :%s %n", Arrays.toString(numbers));

        for (int i = 0; i < numbers.length; i++) {
            for (int j = numbers.length -1; j > i; j--) {
                if (numbers[j] < numbers[j - 1]) {
                    swap(numbers, j, j-1);
                }
            }
        }

        System.out.printf("Sorted Array using Bubble sort algorithm :%s %n",
                Arrays.toString(numbers));
    }
    
    /*
     * Utility method to swap two numbers in array
     */
    public static void swap(int[] array, int from, int to){
        int temp = array[from];
        array[from] = array[to];
        array[to] = temp;
    }
 

	public static void main(String[] args) {
		int[] intArray = {1, 2, 0, -5, 3, -1, 2};
		for (int n : intArray) System.out.print(n + " ");
		System.out.print("\n------------------------------\n");
		bubbleSort1(intArray);
		for (int n : intArray) System.out.print(n + " ");
		System.out.print("\n------------------------------\n");
		
		//testing our bubble sort method in Java
        int[] unsorted = {32, 39,21, 45, 23, 3};
        bubbleSort2(unsorted);
     
        //one more testing of our bubble sort code logic in Java
        int[] test = { 5, 3, 2, 1};
        bubbleSort2(test);
        
        
        bubbleSort3(new int[] { 20, 12, 45, 19, 91, 55 });
        bubbleSort3(new int[] { -1, 0, 1 });
        bubbleSort3(new int[] { -3, -9, -2, -1 });
	}

}
