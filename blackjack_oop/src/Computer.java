package blackjack;

public class Computer {

	public static int computerSpielProlog() {

		System.out.print("\nComputerspieler hat " + Spiel.anzahlKartenComputer + " Karten: ");
		for (int i = 1; i <= Spiel.anzahlKartenComputer; i++) {
			int karten = (int) (Math.random() * 10) + 2;
			System.out.print(" +" + karten);
			Spiel.computerPunkte += karten;
		}
		return Spiel.computerPunkte;
	}

	public static int computerSpiel() {
		if (Spiel.computerPunkte < 17) {
			int karten = (int) (Math.random() * 10) + 2;
			System.out.print("\nComputerspieler möchte eine neue Karte: +" + karten);
			Spiel.computerPunkte += karten;
			Spiel.anzahlKartenComputer++;
		} else {
			System.out.println("\nComputerspieler möchte keine neue Karte.");
		}
		return Spiel.computerPunkte;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
