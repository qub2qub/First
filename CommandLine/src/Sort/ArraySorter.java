package Sort;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

/**
 * Couple of examples of Multi-dimensional array in Java. It shows how to
 * declare multidimensional array in Java, how to initialise them both inline
 * and using for loop and how to access particular elements from two dimensional
 * array.
 *
 * @author Javin Paul
 */

public class ArraySorter {

    public static void main(String args[]) {

        // How to sort Integer array in Java - ascending order
        int[] random = { 33, 22, 11, 21, 55, 32, 3, 4 };
        System.out.println("Array before sorting : " + Arrays.toString(random));
        Arrays.sort(random); // sorts primitive array using quicksort algorithm
        System.out.println("Array after sorting in ascending order : "
                + Arrays.toString(random));

       
        // How to sort String array in Java
        String[] names = {"John", "Steve", "Shane", "Adam", "Ben"};
        System.out.println("String array before sorting : " + Arrays.toString(names));
       
        Arrays.sort(names); // sorts object array using mergesort algorithm
        System.out.println("String array after sorting in ascending order : "
                + Arrays.toString(names));
       
       
        // How to sort String array in descending order in Java
        Arrays.sort(names, 0, names.length, Collections.reverseOrder());
        System.out.println("String array after sorting in descending order : "
                + Arrays.toString(names));
       
       
        // How to Sort Object Array in Java using Comparator and Comparable
        Course[] courses = new Course[4];
        courses[0] = new Course(101, "Java", 200);
        courses[1] = new Course(201, "Ruby", 300);
        courses[2] = new Course(301, "Python", 400);
        courses[3] = new Course(401, "Scala", 500);
       
        System.out.println("Object array before sorting : " + Arrays.toString(courses));
        Arrays.sort(courses);
        System.out.println("Object array after sorting in natural order : " + Arrays.toString(courses));
       
        Arrays.sort(courses, new Course.PriceComparator());
        System.out.println("Object array after sorting by price : " + Arrays.toString(courses));
       
        Arrays.sort(courses, new Course.NameComparator());
        System.out.println("Object array after sorting by name : " + Arrays.toString(courses));

        // How to sort two dimensional array in Java on first column, increasing order
        Integer[][] numbers = { {9, 6, 5}, {3, 2, 4}, {1, 5, 7} };
        System.out.println("Two dimensional array before sorting : " + Arrays.deepToString(numbers));
        Arrays.sort(numbers, new ColumnComparator(0, SortingOrder.ASCENDING));
        System.out.println("2D array after sorting in ascending order on first column : " + Arrays.deepToString(numbers));
       
        // sorting 2D array on second column in descending order
        Arrays.sort(numbers, new ColumnComparator(1,SortingOrder.DESCENDING));
        System.out.println("Sorting two dimensional String array in Java, Second column, Ascending order : " + Arrays.deepToString(numbers));

    }

}

/*
 * Simple Enum to represent sorting order e.g. ascending and descending order
 */
enum SortingOrder{
    ASCENDING, DESCENDING;
};

/*
 * Utility Comparator class to sort two dimensional array in Java
 */
class ColumnComparator implements Comparator<Comparable[]> {
    private final int iColumn;
    private final SortingOrder order;

    public ColumnComparator(int column, SortingOrder order) {
        this.iColumn = column;
        this.order = order;
    }

    @Override
    public int compare(Comparable[] c1, Comparable[] c2) {
        int result = c1[iColumn].compareTo(c2[iColumn]);
        return order==SortingOrder.ASCENDING ? result : -result;
    }
}

class Course implements Comparable<Course>{
    int id;
    String name;
    int price;
   
    public Course(int id, String name, int price){
        this.id = id;
        this.name = name;
        this.price = price;
    }

    @Override
    public int compareTo(Course c) {
        return this.id - c.id;
    }
   
    @Override
    public String toString() {
        return String.format("#%d %s@%d ", id, name, price);
    }

    public static class PriceComparator implements Comparator<Course>{
        @Override
        public int compare(Course c1, Course c2) {
            return c1.price - c2.price;
        }       
    }
   
    public static class NameComparator implements Comparator<Course>{
        @Override
        public int compare(Course c1, Course c2) {
            return c1.name.compareTo(c2.name);
        }
    }
   
}
