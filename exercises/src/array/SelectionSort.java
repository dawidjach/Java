package aufgabe14;

import java.util.Arrays;
import java.util.Collections;

public class SelectionSort {
	
	public static void swap(int[] array, int position1, int position2) {
		int[] list = {5, 8, 3, 6, 8, 9, 4, 2};
		array = new int[list.length];
        
        for (int i = 0; i < list.length; i++) {
        	for(int j = 0; j < array.length; j++) {
        		if (j == i) {
        			array[j] = list[i];
        			System.out.print(array[j] + ", ");
        		}
        	}
        	
        }
	}


	private static int[] selectionsort(int[] sortieren) {
		for(int i = 0; i < sortieren.length - 1; i++) {
			for(int j = i + 1; j < sortieren.length; j++) {
				if(sortieren[i] > sortieren[i]) {
					int temp = sortieren[i];
					sortieren[i] = sortieren[j];
					sortieren[j] = temp;
				}
			}
		}
		return sortieren;
	}

	public static void main(String[] args) {
		int[] list = {5, 8, 3, 6, 8, 9, 4, 2};
		int[] listSortiert = selectionsort(list);
		swap(list, 0, 0);
		
		Arrays.sort(list);
		System.out.println("\narrays.sort = " + Arrays.toString(list));
		
		// int min = Collections.min(Arrays.asList(list));
		
		
		
		for(int i = 0; i < listSortiert.length; i++) {
			//System.out.printf(listSortiert[i] + ", ");
		}
	}
}
