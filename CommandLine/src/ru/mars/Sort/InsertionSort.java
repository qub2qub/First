package ru.mars.Sort;

import java.util.Arrays;

/**
 * Java program to sort an array using Insertion sort algorithm.
 * Insertion sort works great with already sorted, small arrays but not suitable for
 * large array with random order.
 *
 * @author Javin Paul
 */
public class InsertionSort {

    public static void main(String args[]) {

        // getting unsorted integer array for sorting
        int[] randomOrder = getRandomArray(9);
        System.out.println("Random Integer array before Sorting : " + Arrays.toString(randomOrder));

        // sorting array using insertion sort in Java
        insertionSort(randomOrder);
        System.out.println("Sorted array uisng insretion sort : " + Arrays.toString(randomOrder));

        // one more example of sorting array using insertion sort
        randomOrder = getRandomArray(7);
        System.out.println("Before Sorting : " + Arrays.toString(randomOrder));
        insertionSort(randomOrder);
        System.out.println("After Sorting : " + Arrays.toString(randomOrder));

        // Sorting String array using Insertion Sort in Java
        String[] cities = {"London", "Paris", "Tokyo", "NewYork", "Chicago"};
        System.out.println("String array before sorting : " + Arrays.toString(cities));
        insertionSort(cities);
        System.out.println("String array after sorting : " + Arrays.toString(cities));
    }

    public static int[] getRandomArray(int length) {
        int[] numbers = new int[length];
        for (int i = 0; i < length; i++) {
            numbers[i] = (int) (Math.random() * 100);
        }
        return numbers;
    }

    /*
     * Java implementation of insertion sort algorithm to sort
     * an integer array.
     */
    public static void insertionSort(int[] array) {

        // insertion sort starts from second element
        for (int i = 1; i < array.length; i++) {
        	System.out.println("------------------------------------\n"+i+") = "+array[i]);
            int numberToInsert = array[i];
            int compareIndex = i; // или же освобождаемое место, место с ?
            
            while (compareIndex > 0 && array[compareIndex - 1] > numberToInsert) {
                array[compareIndex] = array[compareIndex - 1]; // shifting element
                compareIndex--; // moving backwards, towards index 0
                System.out.print("-N=" + compareIndex);
                // в конце мы освободили предыдущее место, т.е. "вопросик?" на 1 шаг ближе к началу
                // и в него возможно вставим наш проверяемый элемент
            }

            // compareIndex now denotes proper place for number to be sorted
            array[compareIndex] = numberToInsert;
            System.out.println("\ninsN=" + compareIndex);
            System.out.println(Arrays.toString(array));
        }
    }

    /*
     * Method to Sort String array using insertion sort in Java.
     * This can also sort any object array which implements
     * Comparable interface.
     */
    public static void insertionSort(Comparable[] objArray) {

        // insertion sort starts from second element
        for (int i = 1; i < objArray.length; i++) {
            Comparable objectToSort = objArray[i];

            int j = i;
            while (j > 0 && objArray[j - 1].compareTo(objectToSort) > 0) {
                objArray[j] = objArray[j - 1];
                j--;
            }
            objArray[j] = objectToSort;
        }
    }

    public static int[] doInsertionSort(int[] input){
        int temp;
        for (int i = 1; i < input.length; i++) {
            for(int j = i ; j > 0 ; j--){
                if(input[j] < input[j-1]){
                    temp = input[j];
                    input[j] = input[j-1];
                    input[j-1] = temp;
                }
            }
        }
        return input;
    }
    
}