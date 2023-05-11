package SiebzehnUndVier;

import java.util.Scanner;

public class Spiel {
	public static String mensch = "Mensch";
	public static String computerspieler = "Computerspieler";
	public final static int minKarte = 2;
	public final static int maxKarte = 11;
	public static int computerPunkte = 0;
	public static int menschPunkte = 0;
	static boolean prologGespielt = false;
	static int anzahlKartenComputer = 2;
	static int anzahlKartenMensch = 2;
	static String playerName;
	static boolean kampflosGewinnen = false;
	boolean rueckkampf = false;
	

	public static void prolog() {
		Mensch.menschSpielProlog();
		Computer.computerSpielProlog();
		GetWinner.whoWin();
		prologGespielt = true;
		if (prologGespielt == true) {
			runde();
		}
	}

	public static void runde() {
		boolean werSpielt = true;
		do {
			GetWinner.punktenanzahl();
			GetWinner.kartenanzahl();
			if (computerPunkte < 21 || menschPunkte < 21) {
				playerName = werSpielt ? mensch : computerspieler;				
				if (werSpielt) {
					Mensch.menschSpiel();
				} else {
					Computer.computerSpiel();
				}		
				GetWinner.whoWin();				
				werSpielt = !werSpielt;
			}
		} while (prologGespielt == true);
	}
	
	public static void nochEinmalSpielen() {
		if (GetWinner.winner != false) {
			Scanner scanner = new Scanner(System.in);
			System.out.print("\nMöchtest du noch einmal spielen?(j/n): ");
			String output = scanner.next();
			if(output.equals("j")) {
				String wiederholen = "*".repeat(20);
				System.out.println(wiederholen);
				anzahlKartenComputer = 2;
				anzahlKartenMensch = 2;
				prolog();
			} else {
				System.exit(0);
			}
		}
	}

	public static void main(String[] args) {
		prolog();
	}

}
