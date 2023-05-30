package SiebzehnUndVier.copy;

public class Bilanz {

	private int[][] table = new int[3][4];

	public void bilanzArray(Spiel spiel, Spieler computer, Spieler mensch) {
		table[1][1] = spiel.spielanzahl;
		table[1][2] = computer.getSiege();
		table[1][3] = computer.getNiederlagen();
		table[2][1] = spiel.spielanzahl;
		table[2][2] = mensch.getSiege();
		table[2][3] = mensch.getNiederlagen();

		getBilanzArray();
	}

	public void getBilanzArray() {
		int[] maxColumnLengths = new int[table[0].length];
		for (int col = 0; col < table[0].length; col++) {
			int maxLength = 0;
			for (int row = 0; row < table.length; row++) {
				int length = String.valueOf(table[row][col]).length();
				if (length > maxLength) {
					maxLength = length;
				}
			}
			maxColumnLengths[col] = maxLength;
		}
		ausgabe();
	}

	public void ausgabe() {
		System.out.println("Bilanz der Spieler\tSpiele\tSiege\tNiederlagen");
		System.out.println("Computer\t\t" + table[1][1] + "\t" + table[1][2] + "\t" + table[1][3]);
		System.out.println("Mensch\t\t\t" + table[2][1] + "\t" + table[2][2] + "\t" + table[2][3]);
	}

}
