package aufgabe01;

// 42% und Glückspiel
public class Glueckspiel {

	public static void main(String[] args) {
		double randomValue = Math.random();
		double gewonnen = 0.42;
		double spieler1 = Math.random();
		double spieler2 = Math.random();
		double distanzSpieler1 = Math.abs(randomValue - spieler1);
		double distanzSpieler2 = Math.abs(randomValue - spieler2);

		if (randomValue <= gewonnen) {
			System.out.println("Gewonnen! " + (String.format("%.2f", randomValue)));
		} else {
			System.out.println("Verloren! " + (String.format("%.2f", randomValue)));
		}

		if(distanzSpieler1 == distanzSpieler2) {
			System.out.println("Unentschieden!");
		} else if(distanzSpieler1 > distanzSpieler2) {
			System.out.println("Spieler1 hat gewonnen! " + String.format("%.2f" + "+-" + "%.2f", spieler1, spieler2));
		} else {
			System.out.println("Spieler2 hat gewonnen! " + String.format("%.2f" + "+-" + "%.2f", spieler1, spieler2));
		}
	}
}
