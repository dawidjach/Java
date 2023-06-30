package jdbcmariadb;

import java.util.ArrayList;
import java.util.List;

public class Buch {
	private int buchID;
	private String titel;
	private int jahr;
	private int isbn;
	private String version;
	private int anzahlSeiten;
	private Verlag verlag;

	private List<Autor> autoren = new ArrayList<>();
	private List<Kategorie> kategorien = new ArrayList<>();

	public Buch() {
	}
	
	// Konstruktor nur um Daten aus DB auszulesen
	public Buch(int buchID, Verlag verlag, String titel, int jahr, int isbn, String version, int anzahlSeiten) {
		this.buchID = buchID;
		this.verlag = verlag;
		this.titel = titel;
		this.setJahr(jahr);
		this.setIsbn(isbn);
		this.version = version;
		this.anzahlSeiten = anzahlSeiten;
	}

	public int getBuchID() {
		return buchID;
	}

	public void setBuchID(int buchID) {
		this.buchID = buchID;
	}
	
	public List<Autor> getAutoren() {
		return autoren;
	}
	
	public void setAutoren(List<Autor> autoren) {
		this.autoren = autoren;
	}
	
	public void addAutor(Autor autor) {
	    autoren.add(autor);
	}
    
	public String getTitel() {
		return titel;
	}

	public void setTitel(String titel) {
		this.titel = titel;
	}

	public int getJahr() {
		return jahr;
	}

	public void setJahr(int jahr) {
		this.jahr = jahr;
	}

	public int getIsbn() {
		return isbn;
	}

	public void setIsbn(int isbn) {
		this.isbn = isbn;
	}

	public List<Kategorie> getKategorien() {
		return kategorien;
	}

	public void setKategorien(List<Kategorie> kategorien) {
		this.kategorien = kategorien;
	}


	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}
	
	public int getAnzahlSeiten() {
		return anzahlSeiten;
	}
	
	public void setAnzahlSeiten(int anzahlSeiten) {
		this.anzahlSeiten = anzahlSeiten;
	}
	
	public Verlag getVerlag() {
		return verlag;
	}

	public void setVerlag(Verlag verlag) {
		this.verlag = verlag;
	}
}