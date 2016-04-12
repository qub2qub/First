package ru.mars.Answers;

import java.util.Arrays;

public class InsertInSortedArray {

	private static int count = 0;
	
	private static int findIndex(int[] arr, int beginIndex, int endIndex, int k) {
		System.out.println(++count + ") beg="+beginIndex + ", end="+endIndex);
//		for (int i = beginIndex; i <= endIndex; i++) {
//			System.out.print("["+i+"]="+arr[i]+", ");
//		}
//		System.out.println("---*---");
		if (endIndex == beginIndex) {
			return beginIndex;
		}
		int mid = beginIndex + (endIndex - beginIndex) / 2 ;
		int idx=0;
		System.out.println("midIndex = "+mid);
		if (k>arr[mid]) {
			idx=findIndex(arr, mid+1, endIndex, k);
		} else if (k<arr[mid]) {
			idx=findIndex(arr, beginIndex, mid, k);
		} else {
			System.out.println("return MID");
			return mid;
		}
		return idx;
	}
	
	public static int[] insertMe(int[] arr, int k) {
		count = 0;
		int idx = findIndex(arr, 0, arr.length-1, k);
		int[] res = new int[arr.length+1];
		
		System.arraycopy(arr, 0, res, 0, idx);
		System.out.println("\n1 res="+Arrays.toString(res));
		res[idx]=k;
		System.out.println("2 res="+Arrays.toString(res));
		System.arraycopy(arr, idx, res, idx+1, arr.length - idx);
		System.out.println("3 res="+Arrays.toString(res));
		return res;
	}
    public static int[] getRandomArray(int length) {
        int[] numbers = new int[length];
        for (int i = 0; i < length; i++) {
            numbers[i] = (int) (Math.random() * 100);
        }
        return numbers;
    }
    
	public static void main(String[] args) {
		int[] arr = new int[] {1, 2, 5, 7, 10};
		int[] arr2 = getRandomArray(1000);
		Arrays.sort(arr2);
		System.out.println("Arr2="+Arrays.toString(arr2));
//		int idx = findIndex(arr, 0, arr.length-1, 6);
//		System.out.println("Reuslt="+idx);
		int[] res = insertMe(arr2, 44);
		
	}

}
