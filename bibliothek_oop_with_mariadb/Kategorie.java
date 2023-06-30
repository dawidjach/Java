package jdbcmariadb;

public class Kategorie {
	private int katID;
	private String katName;
	
	public Kategorie() {
		
	}
	
	// Konstruktor nur um Daten aus DB einzulesen
	public Kategorie(int katID, String katName) {
		this.katID = katID;
		this.katName = katName;
	}

	public int getKatID() {
		return katID;
	}

	public void setKatID(int katID) {
		this.katID = katID;
	}

	public String getKatName() {
		return katName;
	}

	public void setKatName(String katName) {
		this.katName = katName;
	}
	
}
