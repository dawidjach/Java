package aufgabe14;

import java.util.Arrays;

public class ArrayAlgorithmus {

	public static int[] sortList(int[] list) {
		for (int i = 1; i < list.length; i++) {
			int key = list[i];
			int j = i - 1;
			while (j >= 0 && list[j] > key) {
				list[j + 1] = list[j];
				j--;
			}
			list[j + 1] = key;
		}
		return list;
	}

	// 3 - testen ob sortiert
	public static boolean isSorted(int[] list) {
		boolean bool = true;

		for (int i = 1; i < list.length; i++) {
			if (list[i] < list[i - 1]) {
				bool = false;
				break;
			}
		}
		return bool;
	}

	// 2 Minimum finden
	public static int findMinimum(int[] list, int startIndex, int endIndex) {
		int minimum = list[startIndex];

		for (int i = startIndex + 1; i <= endIndex; i++) {
			if (list[i] < minimum) {
				minimum = list[i];
			}
		}
		return minimum;
	}

	// 1 - tauschen
	public static void swap(int[] list, int position1, int position2) {
		int temp = list[position1];
		list[position1] = list[position2];
		list[position2] = temp;
	}

	
	
	public static void main(String[] args) {
		int[] list = { 5, 8, 3, 6, 8, 9, 4, 2 }; // 8 Elemente
		System.out.println("Original array int[] list = " + Arrays.toString(list));
		
		// list sortieren
		sortList(list);
		System.out.println("Sorted array int[] list = " + Arrays.toString(list));

		// 1
		int position1 = list[0];
		int position2 = list.length - 1;
		swap(list, position1, position2);
		System.out.println("Swap = " + Arrays.toString(list));

		// 2
		System.out.println("Minimum is: " + findMinimum(list, 0, list.length - 1));

		// 3
		System.out.println("Is int[] list sorted? " + isSorted(list));
		list = sortList(list);
		System.out.println("Is int[] sortList sorted? " + isSorted(list));
	}

}
