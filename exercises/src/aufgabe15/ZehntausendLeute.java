package aufgabe15;

public class ZehntausendLeute {

	public static void main(String[] args) {
		int anzahlPersonen = 10_000;
		int[] kreis = new int[anzahlPersonen];
		int aktuellePersonIndex = 0;
		
		for(int i = 0; i < anzahlPersonen; i++) {
			kreis[i] = i + 1;
		}
		
		while(anzahlPersonen > 2) {
//			for(int i = 0; i < 2; i++) {
//				aktuellePersonIndex = (aktuellePersonIndex + 1) % anzahlPersonen;
//			}
			aktuellePersonIndex = (aktuellePersonIndex + 2) % anzahlPersonen;
			for(int i = aktuellePersonIndex; i < anzahlPersonen -1; i++) {
				kreis[i] = kreis[i+1];
			}
			anzahlPersonen--;
		}
		System.out.println("Zwei Personen, die übrig bleiben, sind: "
				+ kreis[0] + " und " + kreis[1]);
	}

}
