package aufgabe08;

public class Zweierpotenzen {

	public static void main(String[] args) {
		int[] zweierPotenzen = new int[20];
		int zwPot = 1;

		for (int i = 0; i < zweierPotenzen.length; i++) {
			zweierPotenzen[i] = zwPot;
			zwPot *= 2;
		}
		for (int i = 0; i < zweierPotenzen.length; i++) {
			System.out.println("2 * " + i + " = " + zweierPotenzen[i]);
		}
	}

}
