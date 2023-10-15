package src.abw.updater;

public class Sieve {
	private String msn, abwtxt;
	
	public Sieve(String msn, String abwtxt) {
		this.msn = msn;
		this.abwtxt = abwtxt;
	}
	
	public Sieve() {
		
	}
	
	public String getMsn() {
		return msn;
	}

	public void setMsn(String msn) {
		this.msn = msn;
	}

	public String getAbwtxt() {
		return abwtxt;
	}

	public void setAbwtxt(String abwtxt) {
		this.abwtxt = abwtxt;
	}

}
