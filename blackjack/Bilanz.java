package blackjack;

public class Bilanz {
	GetWinner getWinner = new GetWinner();

	public static String[][] table = new String[][] { { "Bilanz der Spieler", "Spiele", "Siege", "Niederlagen" },
			{ "Computer", "", "", "" }, { "Mensch", "", "", "" } };

	static String spielanzahl = String.valueOf(GetWinner.spielanzahl);
	static String anzahlComputerSiege = String.valueOf(GetWinner.anzahlMenschNiederlagen);
	static String anzahlComputerNiederlagen = String.valueOf(GetWinner.anzahlMenschSiege);
	static String anzahlMenschSiege = String.valueOf(GetWinner.anzahlMenschSiege);
	static String anzahlMenschNiederlagen = String.valueOf(GetWinner.anzahlMenschNiederlagen);

	public static void bilanzTable() {
		table[1][1] = spielanzahl;
		table[1][2] = anzahlComputerSiege;
		table[1][3] = anzahlComputerNiederlagen;
		table[2][1] = spielanzahl;
		table[2][2] = anzahlMenschSiege;
		table[2][3] = anzahlMenschNiederlagen;

		getBilanzTable();
	}

	public static void getBilanzTable() {
		int[] maxColumnLengths = new int[table[0].length];
		for (int col = 0; col < table[0].length; col++) {
			int maxLength = 0;
			for (int row = 0; row < table.length; row++) {
				int length = table[row][col].length();
				if (length > maxLength) {
					maxLength = length;
				}
			}
			maxColumnLengths[col] = maxLength;
		}

		for (String[] row : table) {
			for (int col = 0; col < row.length; col++) {
				// jedes Element
				String element = row[col];
				int lengthDifference = maxColumnLengths[col] - element.length();
				String padding = "";
				for (int i = 0; i < lengthDifference; i++) {
					padding += " ";
				}
				System.out.print(element + padding + "\t");
			}
			System.out.println();
		}
	}

	public static void main(String[] args) {
		bilanzTable();
	}

}
