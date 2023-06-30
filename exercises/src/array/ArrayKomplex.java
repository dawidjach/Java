import java.util.Arrays;
import java.util.Scanner;

// array nach Nutzereingabe + eine Kopie erzeugen
public class ArrayKomplex {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.printf("How many elements? ");
		int elements = scanner.nextInt();
		int[] newArray = new int[elements];
		
		for(int i = 0; i < newArray.length; i++) {
			System.out.printf("Write a number: ");
			newArray[i] = scanner.nextInt();
		}
		System.out.printf("Original array: ");
		for(int i = 0; i < newArray.length; i++) {
			System.out.printf(newArray[i] + ", ");
		}	
			// copy of int[]newArray
			int newLengthForCopy = newArray.length;
			int[] copyOfNewArray = Arrays.copyOf(newArray, newLengthForCopy);
			System.out.printf("Copied array = " + Arrays.toString(copyOfNewArray));
		
	}

}
