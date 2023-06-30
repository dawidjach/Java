package aufgabe06;

public class BinaryToDecimal {
	
	public static void binaerNachDezimal(String binaer) {
		System.out.println(binaer.length());	//6
		
		int dez = 0;
		int grundZahl = 1;
		
		for(int i= binaer.length() -1; i >= 0 ;i--) {
			if(binaer.charAt(i) == '1') {
				dez += grundZahl;
			}
			grundZahl *= 2;
		}
		System.out.println(dez);
	}

	public static void main(String[] args) {
		binaerNachDezimal("101010");	// 42
	}

}
