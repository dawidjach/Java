package jdbcmariadb;

public class Autor {
	private int autorID;
	private String vorname;
	private String nachname;

	public Autor() {

	}

	// Konstruktor nur um Daten aus DB einzulesen
	public Autor(int autorID, String vorname, String nachname) {
		this.autorID = autorID;
		this.vorname = vorname;
		this.nachname = nachname;
	}
	
	// um Bücher und entsprechende Autoren in die Konsole ausgeben
	public Autor(String vorname, String nachname) {
		this.vorname = vorname;
		this.nachname = nachname;
	}

	public int getAutorID() {
		return autorID;
	}

	public void setAutorID(int autorID) {
		this.autorID = autorID;
	}

	public String getVorname() {
		return vorname;
	}

	public void setVorname(String vorname) {
		this.vorname = vorname;
	}

	public String getNachname() {
		return nachname;
	}

	public void setNachname(String nachname) {
		this.nachname = nachname;
	}

}
