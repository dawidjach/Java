package jdbcmariadb;

public class Verlag {
	private int verlagID;
	private String verlagName;
	
	public Verlag() {
		
	}
	
	// Konstruktor nur um Daten aus DB einzulesen
	public Verlag(int verlagID, String verlagName) {
		this.verlagID = verlagID;
		this.verlagName = verlagName;
	}

	public int getVerlagID() {
		return verlagID;
	}

	public Verlag setVerlagID(int verlagID) {
		this.verlagID = verlagID;
		return this;
	}

	public String getVerlagName() {
		return verlagName;
	}

	public void setVerlagName(String verlagName) {
		this.verlagName = verlagName;
	}
	
	
}
