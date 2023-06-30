package aufgabe03;

public class Quader {
	private double a, b, c, quaderVolumen, quaderOberflaeche, 
		quaderLaengerRaumdiagonalen, quaderGesamtkantenlaenge;

	public Quader(double a, double b, double c) {
		this.a = a;
		this.b = b;
		this.c = c;
		double zweiMalabc = 2*a + 2*b + 2*c;	// 18 (4+6+8)
		
		// quaderVolumen = abc
		// quaderOberflaeche = (2ab) + (2ac) + (2bc)
		// quaderLaengerRaumdiagonalen = √a2 + b2 + c2
		// quaderGesamtkantenlaenge = 4a+4b+4c
		
		quaderOberflaeche = (2*a*b) + (2*a*c) + (2*b*c);
		quaderLaengerRaumdiagonalen = Math.sqrt(zweiMalabc);
		quaderGesamtkantenlaenge = 2*zweiMalabc;
		quaderVolumen = quaderGesamtkantenlaenge / 3 * 2;
	}
	
	public String toString() {
	    return String.format("\nquaderVolumen = %.02f, quaderOberflaeche = %.02f, quaderLaengerRaumdiagonalen = %.02f und quaderGesamtkantenlaenge = %.02f", 
	        quaderVolumen, quaderOberflaeche, quaderLaengerRaumdiagonalen, quaderGesamtkantenlaenge);
	}

}
