package SiebzehnUndVier.copy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Karten {
	List<Karte> kartenListe = new ArrayList<>();
	static String[] symbole = { "Kreuz", "Pik", "Herz", "Karo" };
	static String[] werte = { "2", "3", "4", "5", "6,", "7", "8", "9", "10", "Bube", "Dame", "König", "Ass" };
	static int[] punkte = { 2, 3, 4, 5, 6, 7, 8, 9, 10, 10, 10, 10, 11 };
	
	Random random = new Random();

	public Karten() {
		kartenInListErzeugen();
        kartenMischen(kartenListe);
	}

	// 3
	// oder Collection `shuffle()`
	public void kartenVerteilen(Spieler spieler) {
		if (!kartenListe.isEmpty()) {
			Karte karte = kartenListe.get(0);
			System.out.print(karte.getWert() + " " + karte.getSymbol());
			spieler.punkte += karte.getPunkt();
			karte = kartenListe.remove(0);
		}
	}

	// 2 - umbauen / umschreiben (selection.sort, swap...)
	public void kartenMischen(List<Karte> kartenListe) {
		System.out.println();
		// oder Collections.shuffle(kartenListe);
		for (int i = 0; i < 7; i++) {
			for (int j = 0; j < kartenListe.size()-1; j++) {
				int randomIndex = random.nextInt(12) + 1;
				swap(kartenListe, j, randomIndex);
			}
		}
		// ausgabe();
	}

	public void swap(List<Karte> kartenListe, int position1, int position2) {
//		Karte temp = kartenListe.get(position1);
//		kartenListe.set(position1, kartenListe.get(position2));
//		kartenListe.set(position2, temp);
//		//System.out.println(kartenListe.get(position1) + " " + kartenListe.get(position2));
//		kartenMischen(kartenListe);
		Collections.swap(kartenListe, position1, position2);
	}

	// 1
	public void kartenInListErzeugen() {
		for (int i = 0; i < symbole.length; i++) {
			for (int j = 0; j < werte.length; j++) {
				kartenListe.add(new Karte(symbole[i], werte[j], punkte[j]));
			}
		}
		// ausgabe();
		swap(kartenListe, 0, 1);
	}

	public void ausgabe() {
		for (Karte k : kartenListe) {
			System.out.println(k.getName());
		}
	}
}
