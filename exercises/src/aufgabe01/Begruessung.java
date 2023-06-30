package aufgabe01;

public class Begruessung {
	// Aufgabe 1 - Begrüßung
	private int alter;
	private String name;
	private char geschlecht; // 'm'=männlich, 'w'=weiblich, 'd'=diverse
	
	// Aufgabe 1 - Begrüßung 
	public Begruessung(int alter, String name, char geschlecht) {
		this.alter = alter;
		this.name = name;
		this.geschlecht = geschlecht;		
	}
	
	public String toString() {
		// Aufgabe 1 - Begrüßung
		if(alter >= 18 && geschlecht == 'm') {
			return("Guten Tag Herr " + name + "!");			
		} else if(alter >= 18 && geschlecht == 'w') {
			return("Guten Tag Frau " + name + "!");
		} else if(alter > 0 && alter < 18) {
			return("Hallo " + name + "!");
		} else {
			return("Fehler!");
		}
	}
		
	public static void main(String[] args) {
		// Aufgabe 1 - Begrüßung
		Begruessung begruessung01 = new Begruessung(50, "Walter", 'm');
		Begruessung begruessung02 = new Begruessung(18, "Julia", 'w');
		Begruessung begruessung03 = new Begruessung(15, "Susanne", 'd');
		Begruessung begruessung04 = new Begruessung(-5, "Susanne", 'd');
		
		System.out.println(begruessung01);
		System.out.println(begruessung02);
		System.out.println(begruessung03);
		System.out.println(begruessung04);	
	}

}
