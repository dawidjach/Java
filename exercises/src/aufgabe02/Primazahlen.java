package aufgabe02;

public class Primazahlen {
	private int primazahl;
	private int numer = 2;
	boolean istPrimazahl = true;

	public Primazahlen(int primazahl) {
		this.primazahl = primazahl;
		this.numer = numer;
		this.istPrimazahl = istPrimazahl;

		if (primazahl < 2) {
			istPrimazahl = false;
		} else {
			for (int i = 2; i <= primazahl/2; i++) {
				if (numer % i == 0) {
					istPrimazahl = false;
					break;
				}
			}
			if(istPrimazahl) {
				System.out.println(primazahl + " ist keine Primazahl");
				
			} else {
				System.out.println(primazahl + " ist eine Primazahl");
			}
		}
	}

	public static void main(String[] args) {
		Primazahlen primazahl01 = new Primazahlen(25);
		Primazahlen primazahl02 = new Primazahlen(11);
	}

}
