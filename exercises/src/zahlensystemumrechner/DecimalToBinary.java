package aufgabe06;

public class DecimalToBinary {
	
	public static void dezimalNachBinaer(int dezimal) {
		int anzahlStellen = 0;
		int output = dezimal;
		
		while(output != 0) {
			output /= 2;
			anzahlStellen++;
		}
		System.out.println("Anzahl Stellen = " + anzahlStellen); // 5
		
		int[] array = new int[anzahlStellen];
		
		for(int i = 0; i < anzahlStellen; i++) {
			array[i] = dezimal % 2;
			dezimal = dezimal / 2;
		}
		
		String binaer = "";
		for(int i = anzahlStellen -1; i >= 0; i--) {
			binaer += array[i];
		}
		System.out.println(binaer);
		
		/////// oder kurzer: /////
		String d = "";
		int a = 21;
		while (a > 0) {
		d = (a % 2) + d;
		System.out.println(d + " ");
		a = a / 2;
		}
		System.out.println("d = " + d);


		//// oder ////
		for(int i = 15; i >= 0; i--) {
			if((dezimal & 0b1 << i) != 0b0) {
				System.out.print("1");
			} else {
				System.out.print("0");			
			}
		}
	}

	public static void main(String[] args) {
		dezimalNachBinaer(21);
	}

}
