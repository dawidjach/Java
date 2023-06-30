package aufgabe08;

public class nurEinerSchleife {

	public static void main(String[] args) {

		int[] zweierPotenzen = new int[20];
		// int zwPot = 1;

		for (int i = 0, zwPot = 1; i < 20; i++, zwPot *= 2) {
			zweierPotenzen[i] = zwPot;
			System.out.println("2 hoch " + i + " = " + zwPot);
		}

	}
}
