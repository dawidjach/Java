package arrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BilddatenKomprimierung {

	public static void main(String[] args) {
		String[] BildDaten = { "QQQQRRRRRRTTTTTTTTTTLLLLLLLLLLLMNNNVVVVVVVVVVVAAAAAAAAAAAAA" };
		//List<String> KomprimierteDaten = Arrays.asList(BildDaten);	// gibt Fehler am Ausgeben
		ArrayList<String> KomprimierteDaten = new ArrayList<>();
		int laenge = BildDaten[0].length();
		int anzahlBuchstaben = 0;
		int index = 0;

		while (index < laenge) {
			char ch = BildDaten[0].charAt(index);
			anzahlBuchstaben++;
			while (index + anzahlBuchstaben < laenge && BildDaten[0].charAt(index + anzahlBuchstaben) == ch) {
				anzahlBuchstaben++;
			}
			if (anzahlBuchstaben > 3) {
				System.out.print("§"+anzahlBuchstaben+ch);
			} else {
				for (int i = 0; i < anzahlBuchstaben; i++) {
					System.out.print(ch);
				}
			}
			index += anzahlBuchstaben;
		}
		for (String s : KomprimierteDaten) {
			System.out.print(s);
		}
	}
}
