package arrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BilddatenKomprimierung2 {

	public static void main(String[] args) {
		char[] BildDaten = "QQQQRRRRRRTTTTTTTTTTLLLLLLLLLLLMNNNVVVVVVVVVVVAAAAAAAAAAAAA".toCharArray();
		// List<String> KomprimierteDaten = Arrays.asList(BildDaten); // gibt Fehler am Ausgeben
		List<Character> KomprimierteDaten = new ArrayList<>();
		int laenge = BildDaten.length;
		int index = 0;
		int i;

		while (index < laenge) {
			int anzahlBuchstaben = 0;
			i = index;
			// char ch = BildDaten.charAt(index);
			// anzahlBuchstaben++;
			while (i < laenge && BildDaten[i]==BildDaten[index]) {
				i++;
				anzahlBuchstaben++;
			}
//			System.out.println(anzahlBuchstaben);
			if (anzahlBuchstaben > 3) {
				KomprimierteDaten.add('§');
				KomprimierteDaten.add(String.valueOf(anzahlBuchstaben).charAt(0));
				KomprimierteDaten.add(BildDaten[index]);
				index += anzahlBuchstaben;
			} else {
				i = 0;
				while (i < anzahlBuchstaben) {
					KomprimierteDaten.add(BildDaten[index]);
					i++;
				}
				index += anzahlBuchstaben;
			}
		}
		for (Character s : KomprimierteDaten) {
			System.out.print(s);
		}
	}
}
