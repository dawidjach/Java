package arrays;
import java.util.ArrayList;
import java.util.Arrays;

public class ArraysAufgaben {
	
	public static void main(String[] args) {
		int[] list = {5, 8, 3, 6, 8, 9, 4, 2}; 
	    int minimum = findMinimum(list, 0, list.length - 1);
	    System.out.println("Minimum is " + minimum);
	    
		int[] array = { 0,1,2,3,4,5,6,7,0,9 };
		int[] arrayReturn = new int[array.length];
//		for(int i = 0; i < arrayReturn.length; i++) {
//			arrayReturn[i] = array[array.length - 1 - i];
//		}
//		System.out.println(Arrays.toString(arrayReturn));
		
		for(int j = array.length -1; j >= 0; j--) {
			//System.out.print("[" + array[j] + "]");
		}
		System.out.println();
		
		
		ArrayList<Integer> array2 = new ArrayList<>();
		for(int i = 0; i < array.length; i++) {
			if(!array2.contains(array[i])) {
				array2.add(array[i]);
			}
		}
		int[] newArray2 = new int[array2.size()];
		for(int i = 0; i < array2.size(); i++) {
			newArray2[i] = array2.get(i);
		}
		//System.out.println(Arrays.toString(newArray2));
	}
	
	public static int findMinimum(int[] list, int startIndex, int endIndex) {
		int minimum = list[startIndex];
	    for (int i = startIndex + 1; i <= endIndex; i++) {
	        if (list[i] < minimum) {
	            minimum = list[i];
	        }
	    }
	    return minimum;
	}
}
