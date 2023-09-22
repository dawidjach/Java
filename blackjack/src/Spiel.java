package SiebzehnUndVier.copy;

import java.util.Scanner;

public class Spiel {
//	public String menschspieler = "Mensch";
//	public String computerspieler = "Computerspieler";
	Spieler currentPlayer;

	boolean weiterspielen = true;
	boolean winner = false;
	
	public int spielanzahl;

	Karten karten;
	Bilanz bilanz;
	Mensch mensch;
	Computer computer;

	public void prolog(Mensch mensch, Computer computer) {
		karten = new Karten();
		karten.kartenInListErzeugen();
		currentPlayer = mensch;
		mensch.spielProlog();
		currentPlayer = computer;
		computer.spielProlog();
		whoWin();
//		weiterspielen = true;
		if (weiterspielen == true) {
			runde();
		}
	}

	public void runde() {
		boolean switchThePlayer = true;
		do {
			punktenanzahlAusgabe();
			kartenanzahlAusgabe();
			if (computer.getPunkte() < 21 || mensch.getPunkte() < 21) {
				currentPlayer = switchThePlayer ? mensch : computer;
				if (switchThePlayer) {
					mensch.spiel();
				} else {
					computer.spiel();
				}
				whoWin();
				switchThePlayer = !switchThePlayer;
			}
		} while (weiterspielen == true);
	}

	public void nochEinmalSpielen() {
		if (winner != false) {
			Scanner scanner = new Scanner(System.in);
			System.out.print("\nMöchtest du noch einmal spielen?(j/n): ");
			String output = scanner.nextLine();
//			scanner.close();
			if (output.equals("j")) {
				System.out.println("*".repeat(53));
				computer.setAnzahlKarten(0);
				computer.setPunkte(0);
				
				mensch.setAnzahlKarten(0);
				mensch.setPunkte(0);
				mensch.neueKarteZiehen = true;
				prolog(mensch,computer); // oder prolog(mensch, new Computer());
			} else {
				bilanz.bilanzArray(this, computer, mensch);
				System.exit(0);
//				weiterspielen = false;
			}
		}
	}
	
	public void whoWin() {
		if (mensch.getPunkte() > 21 || computer.getPunkte() > 21) {
			System.out.printf("\nGAME OVER! %s verloren!\n\n", currentPlayer.getName());
			if (currentPlayer.equals(mensch)) {
				mensch.verloren();
				spielanzahl++;
				computer.gewonnen();
			} else {
				computer.niederlagen++;
				spielanzahl++;
				mensch.siege++;
			}
			winner = true;
			bilanz.bilanzArray(this, computer, mensch);
			nochEinmalSpielen();
		} else if ((mensch.getPunkte() == 21 || computer.getPunkte() == 21)
				|| (computer.getPunkte() > 17 && mensch.getPunkte() > computer.getPunkte())) {
			System.out.printf("\nGAME OVER! %s gewonnen!\n\n", currentPlayer.getName());
			if (currentPlayer.equals(mensch)) {
				mensch.siege++;
				spielanzahl++;
				computer.niederlagen++;
			} else {
				computer.siege++;
				spielanzahl++;
				mensch.niederlagen++;
			}
			winner = true;
			bilanz.bilanzArray(this, computer, mensch);
			nochEinmalSpielen();
		} else if (computer.getPunkte() >= 17 && mensch.neueKarteZiehen == false
				&& computer.getPunkte() >= mensch.getPunkte()) {
			System.out.printf("\nGAME OVER! %s gewonnen!\n\n", computer.getName());
			computer.siege++;
			mensch.niederlagen++;
			spielanzahl++;
			winner = true;
			bilanz.bilanzArray(this, computer, mensch);
			nochEinmalSpielen();
		}
	}
	public void punktenanzahlAusgabe() {
		System.out.printf("\nPunktenanzahl: \nComputerspieler %d vs Mensch %d ", computer.punkte,
				mensch.punkte);
	}

	public void kartenanzahlAusgabe() {
		System.out.printf("\nKartenanzahl: \nComputerspieler %d vs Mensch %d ", computer.anzahlKarten,
				mensch.anzahlKarten);
	}
	public static void main(String[] args) {
		Karten karten = new Karten();
		Computer computer = new Computer(karten);	// ?
		Mensch mensch = new Mensch(karten);
		Bilanz bilanz = new Bilanz();
		Spiel spiel = new Spiel();
		spiel.bilanz = bilanz;
		spiel.mensch = mensch;
		spiel.computer = computer;
		spiel.prolog(mensch, computer); // ? oder spiel.prolog(new Mensch(new Karten(), new Computer());

	}

}
