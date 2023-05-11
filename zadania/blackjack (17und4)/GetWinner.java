package SiebzehnUndVier;

public class GetWinner {
	static boolean winner = false;
	public static int anzahlMenschSiege = 0;
	public static int anzahlMenschNiederlagen = 0;
	public static int unentschiedeneSpiele = 0;
	public static int spielanzahl = anzahlMenschSiege + anzahlMenschNiederlagen + unentschiedeneSpiele;
	
	public static void whoWin() {		
		if (Spiel.menschPunkte > 21 || Spiel.computerPunkte > 21) {
			System.out.printf("\nGAME OVER! %s verloren!\n\n", Spiel.playerName);
			if(Spiel.playerName.equals(Spiel.mensch)) {
				anzahlMenschNiederlagen++;
			}
			winner = true;
			Bilanz.bilanzTable();
			Spiel.nochEinmalSpielen();
			System.exit(0);			
		} else if (Spiel.menschPunkte == 21 || Spiel.computerPunkte == 21) {
			System.out.printf("\nGAME OVER! %s gewonnen!\n\n", Spiel.playerName);
			if(Spiel.playerName.equals(Spiel.mensch)) {
				anzahlMenschSiege++;
			}
			winner = false;
			Bilanz.bilanzTable();
			Spiel.nochEinmalSpielen();
			System.exit(0);			
		} else if (Spiel.kampflosGewinnen == true) {
			System.out.printf("\nUnentschieden!\n\n");
			unentschiedeneSpiele++;
			winner = false;
			Bilanz.bilanzTable();
			Spiel.nochEinmalSpielen();
		}
	}
	
	public static void punktenanzahl() {
		System.out.printf("\nPunktenanzahl: \nComputerspieler %d vs Mensch %d ", Spiel.computerPunkte, Spiel.menschPunkte);
	}
	
	public static void kartenanzahl() {
		System.out.printf("\nKartenanzahl: \nComputerspieler %d vs Mensch %d ", Spiel.anzahlKartenComputer, Spiel.anzahlKartenMensch);		
	}

	public static void main(String[] args) {
		whoWin();
	}

}
