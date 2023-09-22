package SiebzehnUndVier.copy;

import java.util.Scanner;

public class Mensch extends Spieler {
	private Scanner scanner;
	public boolean neueKarteZiehen = true;

	public Mensch(Karten karten) {
		super(karten);
		scanner = new Scanner(System.in);
		setName("Mensch");
	}

	@Override
	public int spiel() {
		if (neueKarteZiehen) {
			System.out.print("\nMöchtest du eine neue Karte haben? (j/n): ");
			Scanner scanner = new Scanner(System.in);
			String output = scanner.nextLine();
			if (output.equals("j")) {
				System.out.print("Deine neue Karte: ");
				karteZiehen();
				
			} else if (output.equals("n")) {
				neueKarteZiehen = false;

			}
		}
		return punkte;
	}
}
