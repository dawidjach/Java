package aufgabe12;

import java.util.Arrays;

public class Quersummen {
	public static final int von = 0;
	public static final int bis = 1000;

	// 6 - Querprodukt berechnen
	public static int querproduktBestimmen(int zahl) {
		int querprodukt = 1;
		while(zahl > 0) {
			querprodukt *= zahl % 10;
			zahl /= 10;
		}
		return querprodukt;
	}
	
	// 6 - QS + QP berechnen
	public static void quersummeUndQuerprodukt() {
		for (int i = von; i <= bis; i++) {
			if (i == quersummeBestimmen(i) + querproduktBestimmen(i)) {
				System.out.print(i + ", ");			
			}
		}
	}

	// 5
	public static int ziffernwurzel(int zahl) {
		int summeEins = quersummeBestimmen(zahl);
		int summeZwei = quersummeBestimmen(summeEins);
		
		System.out.println("summeEins = " + summeEins);
		return summeZwei;
	}

	// 4
	public static int quersummenVorkommen(int von, int bis) {
		int max = 0;
		int vorkommen = 0;
		// while(bis != 0) {
		// vorkommen++;
		// bis /= 10;
		// }
		int[] quersummen = new int[bis];
		// System.out.println(quersummen.length);
		for (int i = von; i <= bis; i++) {
			int summe = quersummeBestimmen(i);
			// System.out.println("Summe= " + summe);
			quersummen[summe] = quersummen[summe] + 1;
		}
		// System.out.println(Arrays.toString(quersummen));

		for (int j = 0; j < quersummen.length; j++) {
			if (quersummen[j] >= max) {
				max = quersummen[j];
			}
		}
		return max;
	}

	// 3
	public static void quersummeDurchSieben(int von, int bis, int querSieben) {
		for (int i = von; i <= bis; i++) {
			int zahl = quersummeBestimmen(i);
			if (zahl % querSieben == 0) {
				System.out.printf(i + ", ");
			}
		}
	}

	// 2
	public static void quersummeFuenfzehn(int von, int bis, int querFuenfzehn) {
		for (int i = von; i <= bis; i++) {
			int zahl = quersummeBestimmen(i);
			if (zahl == querFuenfzehn) {
				System.out.printf(i + ", ");
			}
		}
	}

	// 1
	public static int quersummeBestimmen(int zahl) {
		int quersumme = 0;

		while (zahl != 0) {
			quersumme = quersumme + (zahl % 10);
			zahl /= 10;
		}
		return quersumme;
	}

	public static void main(String[] args) {
		// 1
		System.out.println("***Punkt 1");
		System.out.println(quersummeBestimmen(178));
		// 2
		System.out.println("\n***Punkt 2");
		quersummeFuenfzehn(69, bis, 15);
		// 3
		System.out.println("\n***Punkt 3");
		quersummeFuenfzehn(7, bis, 7);
		// 4
		System.out.println("\n***Punkt 4");
		System.out.println("häufigste Anzahl: " + quersummenVorkommen(von, bis));

		// 5
		System.out.println("\n***Punkt 5");
		System.out.println("Ziffernwurzel = " + ziffernwurzel(47));

		// 6
		System.out.println("\n***Punkt 6");
		quersummeUndQuerprodukt();
	}
}
