package jdbcmariadb;

import java.sql.Connection;

//import java.sql.Connection;

public class Outputs {
	BibliothekVerwalten bv;
	private Connection con;
	
	public Outputs (Connection con, BibliothekVerwalten bv) {
		this.con = con;
		this.bv = bv;	
		if (con != null) {
			System.out.println("Verbindung mit Datenbank vom Outputs korrekt.");
		} else {
			System.out.println("Fehlerhafte Verbindung mit Datenbank vom Outputs.");
		}
	}
	
	public void menuOutput() {
		System.out.println("-".repeat(79));
		System.out.println(" ".repeat(33) + "*** MENU ***");
		System.out.println("-".repeat(79));
		System.out.println("1. Buch ");
		System.out.println("2. Autor ");
		System.out.println("3. Verlag ");
		System.out.println("4. Kategorie ");
		System.out.println("0. Exit ");
	}
	
	public void optionenOutput() {
		int output = bv.output;
		String outputMenuToString = bv.convertMenuFromIntToString(output);
		System.out.println("-".repeat(79));
		System.out.println(outputMenuToString);
		System.out.println("-".repeat(79));
		System.out.print("1. Anzeigen" + " ".repeat(5));
		System.out.print("2. Erzeugen" + " ".repeat(5));
		System.out.print("3. Aktualisieren" + " ".repeat(5));
		System.out.print("4. Löschen" + " ".repeat(5));
		System.out.println("0. Zurück");
		System.out.println(".".repeat(79));
	}
	
	public void buchAktualisierenOutput() {
		System.out.print("Was möchten Sie aktualisieren? ");
		System.out.print("1. Verlag" + " ".repeat(5));
		System.out.print("2. Titel" + " ".repeat(5));
		System.out.print("3. Jahr" + " ".repeat(5));
		System.out.print("4. ISBN" + " ".repeat(5));
		System.out.print("5. Version" + " ".repeat(5));
		System.out.print("6. Seitenanzahl" + " ".repeat(5));
		System.out.print("7. Kategorie" + " ".repeat(5));
		System.out.print("8. Autoren" + " ".repeat(5));
		System.out.println("0. Zurück");
	}
	
	public void katAktualisierenCase7Output() {
		System.out.println("-".repeat(79));
		System.out.println("Kategorie ");
		System.out.println("-".repeat(79));
		System.out.print("1. Hinzufügen " + " ".repeat(5));
		System.out.print("2. Löschen " + " ".repeat(5));
		System.out.println("0. Zurück");
	}
	
	public void buchAktualisierenCase8Output() {
		System.out.println("-".repeat(79));
		System.out.println("Autor ");
		System.out.println("-".repeat(79));
		System.out.print("1. Hinzufügen " + " ".repeat(5));
		System.out.print("2. Löschen " + " ".repeat(5));
		System.out.println("0. Zurück");
	}
}
