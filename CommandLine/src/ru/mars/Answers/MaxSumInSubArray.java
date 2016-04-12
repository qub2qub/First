package ru.mars.Answers;

import java.util.Random;

public class MaxSumInSubArray {
	
	public static void main(String[] args) {
		int[] arr = new int[20];

		Random r = new Random();
		for (int i = 0; i < arr.length; i++) {
			arr[i] = r.nextInt(11) - 5;
		}

		for (int n : arr) System.out.print(n + " ");
		System.out.println();
		
		int[] arr2 = {1, 2, 0, -5, 3, -1, 2};
		findMaxSubarray(arr);
	}

	public static void findMaxSubarray(int[] arr) {
		if (arr == null || arr.length == 0) {
			System.out.println("empty array");
			return;
		}
		
		// Для начали во все параметры задаём первый элемент.
		int maxSumStartIndex = 0;
		int maxSumLastIndex = 0;
		int maxSum = arr[0];

		int lastSumStartIndex = 0;
		int lastSum = arr[0];

		for (int i = 1; i < arr.length; i++) {
			System.out.println("____N="+(i+1)+"____v="+arr[i]+"______["+lastSumStartIndex+","+i+"]_________");
			System.out.println("lastSum1="+lastSum);
			// Прибавляем к последней сумме следующий элемент.
			lastSum += arr[i];
			// Если эта сумма получилась меньше чем сам элемент
			// Значит элемент больше чем вся предыдущая сумма
			// Значит начинаем считать новую сумму с этого элемента
			if (lastSum < arr[i]) {
				lastSum = arr[i];
				lastSumStartIndex = i;
			}
			System.out.println("lastSum2="+lastSum);
			System.out.println("maxSum="+maxSum);
			// Проверяем последнюю сумму с максимальной суммой
			// Если последняя (с добавленным текущим элементом) больше чем максимальная
			// меняем их местами и обновляем индексы
			if (maxSum < lastSum) {
				maxSumStartIndex = lastSumStartIndex;
				// В последней сумме последним индексом будет индекс текущего проверяемого элемента
				maxSumLastIndex = i;
				maxSum = lastSum;
			}
			System.out.println("maxSum2="+maxSum);
		} // end of for

		System.out.println("sum( arr[" + maxSumStartIndex + "] .. arr[" + maxSumLastIndex + "] ) = " + maxSum);
		for (int i = maxSumStartIndex; i <= maxSumLastIndex; i++) {
			System.out.print(arr[i] + " ");
		}
	}
}
