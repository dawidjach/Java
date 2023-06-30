package aufgabe06;

import java.util.Scanner;

public class Zahlenumrechner {
	// 5
	public static void hexadezimalNachDezimal(String hexadezimal) {
		int dezimal = 0;
		for (int i = 0; i < hexadezimal.length(); i++) {
			char c = hexadezimal.charAt(i);
			int nummer = 0;
			if (c >= '0' && c <= '9') {
				nummer = c - '0';
			} else if (c >= 'A' && c <= 'F') {
				nummer = c - 'A' + 10;
			}
			dezimal += nummer * Math.pow(16, hexadezimal.length() - i - 1);
		}
		System.out.println(dezimal);
	}

	// 4
	public static void binaerNachDezimal(String binaerString) {
		// int binaerInt = Integer.parseInt(binaerString);
		int dezimal = 0;
		int grundZahl = 1;

		for (int i = binaerString.length() - 1; i >= 0; i--) {
			if (binaerString.charAt(i) == '1') {
				dezimal += grundZahl;
			}
			grundZahl *= 2;
		}
		System.out.println(dezimal);
	}

	// 2
	public static void dezimalNachHexadezimal(int dezimal) {
		int output;
		String hex = "";
		char hexChars[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };
		while (dezimal > 0) {
			output = dezimal % 16;
			hex = hexChars[output] + hex;
			dezimal = dezimal / 16;
		}
		System.out.println(hex);
	}

	// 1
	public static void dezimalNachBinaer(int dezimal) {
		int stellenAnzahl = 0;
		int output = dezimal;
		int zero = 0;

		while (output == 0) {
			System.out.print(zero);
			break;
		}

		while (output != 0) {
			output = output / 2;
			stellenAnzahl++;
		}

		int[] zahlen = new int[stellenAnzahl];

		for (int i = 0; i < stellenAnzahl; i++) {
			zahlen[i] = dezimal % 2;
			dezimal = dezimal / 2;
		}

		// Rückwerts
		String binaer = "";
		for (int i = stellenAnzahl - 1; i >= 0; i--) {
			binaer += zahlen[i];
		}

		System.out.println(binaer);

		/////// oder kurzer: /////
//		String d = "";
//		int a = 21;
//		while (a > 0) {
//			d = (a % 2) + d;
//			System.out.println(d + " ");
//			a = a / 2;
//		}
//		System.out.println("d = " + d);
		
		/// oder ///
//		for(int i = 7; i >= 0; i--) {
//			if((dezimal & 0b1 << i) != 0b0) {
//				System.out.print("1");
//			} else {
//				System.out.print("0");			
//			}
//		}
	}

	public static void main(String[] args) {
		// 1
		System.out.println("***Übung 1 - dec to bin");
		dezimalNachBinaer(0); // 0
		dezimalNachBinaer(42); // 101010
		// Hilfsfunktion
		int dezNachBin = 25;
		String binaerAusDezimal = Integer.toBinaryString(dezNachBin);
		System.out.println(binaerAusDezimal + "\n");

		// 2
		System.out.println("***Übung 2 - dec to hex");
		dezimalNachHexadezimal(10); // A
		dezimalNachHexadezimal(15); // F
		dezimalNachHexadezimal(289); // 121
		// Hilfsfunktion
		int dezNachHex = 25;
		String hexAusDezimal = Integer.toHexString(dezNachHex);
		System.out.println(hexAusDezimal + "\n");

		// 3 (Ctrl/)
//		System.out.println("***Übung 3 - bin or hex?");
//		Scanner scanner = new Scanner(System.in);
//		
//		System.out.printf("Gib eine Zahl: ");
//		int zahl = scanner.nextInt();
//		System.out.printf("Gib 'B' (binär) oder 'H' (hexadezimal): ");
//		String umrechnung = scanner.next().toUpperCase();
//		
//		if(zahl <= 0) {
//			System.out.println("Gib bitte natürliche Zahl!");
//		} else if(umrechnung.equals("B")) {
//			String benutzerUmrechnungNachBin = Integer.toBinaryString(zahl);
//			System.out.println(benutzerUmrechnungNachBin);
//		} else if(umrechnung.equals("H")) {
//			String benutzerUmrechnungNachHex = Integer.toHexString(zahl).toUpperCase();
//			System.out.println(benutzerUmrechnungNachHex);
//		} else {
//			System.out.println("Hast du " + umrechnung + " gedrückt???");
//		}

		// 4
		System.out.println("***Übung 4 - bin to dec");
		binaerNachDezimal("1010"); // 10
		binaerNachDezimal("101010"); // 42
		// Hilfsfunktion
		String binNachDez = "1010";
		int dezAusBin = Integer.parseInt(binNachDez, 2); // 10
		System.out.println(dezAusBin + "\n");

		// 5
		System.out.println("***Übung 5 - hex to dec");
		hexadezimalNachDezimal("1F"); // 31
	}
}
