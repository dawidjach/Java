package SiebzehnUndVier;

import java.util.Scanner;

public class Mensch {
	static Scanner scanner = new Scanner(System.in);
	
	public static int menschSpielProlog() {			
		System.out.print("Du hast " + Spiel.anzahlKartenMensch + " Karten: ");
		for (int i = 1; i <= Spiel.anzahlKartenMensch; i++) {
			int randomKarte = (int) (Math.random() * 10) + 2;
			System.out.print(" +" + randomKarte);
			Spiel.menschPunkte += randomKarte;		
		}
		return Spiel.menschPunkte;
	}

	public static int menschSpiel() {
		// int karten = (int) (Math.random() * 10) + 2; // oder (Math.random() * (maxKarte - 2 + 1)) + 2;
		int neinAnzahl = 0;
		
		System.out.print("\nMöchtest du eine neue Karte haben? (j/n): ");
		String output = scanner.next();
		if (output.equals("j")) {
			int randomKarte = (int) (Math.random() * 10) + 2;
			System.out.println("Deine neue Karte: " + randomKarte);
			Spiel.menschPunkte += randomKarte;
			Spiel.anzahlKartenMensch++;
		} else if (output.equals("n") && Spiel.menschPunkte >= 17) {
			neinAnzahl++;
			if(neinAnzahl == 2) {
				Spiel.kampflosGewinnen = true;
				GetWinner.whoWin();
			}
		}
		return Spiel.menschPunkte;
	}

	public static void main(String[] args) {
		menschSpielProlog();
		menschSpiel();
	}
}
