package aufgabe13;
import java.util.Arrays;

public class FibonacciProgramm {
	int[] fibonacciArray = new int[20];
	
	public static int fibonacci(int n) {
		if(n == 0) {
			return 0;
		} else if(n == 1) {
			return 1;
		} else if(n > 1) {
			int aktuelleSumme = fibonacci(n-1) + fibonacci(n-2);
			fibonacciArray[n] = aktuelleSumme;
			return aktuelleSumme;
		} else {
			System.out.println("Fehlermeldung! Überprüf ob eine negative Zahl eingegeben wurde.");
			return -1;		
		}
	}
	
	public static void main(String[] args) {
		System.out.println(fibonacci(-1));	// -1
		System.out.println(fibonacci(10));	// 55
		
	}

}
