package commoninterface;

public class EG {
	int errorCodes;
	String eg;
	
	public EG(int errorCodes, String eg) {
		this.errorCodes = errorCodes;
		this.eg = eg;
	}

	public int getErrorCodes() {
		return errorCodes;
	}


	public void setErrorCodes(int errorCodes) {
		this.errorCodes = errorCodes;
	}


	public String getEg() {
		return eg;
	}


	public void setEg(String eg) {
		this.eg = eg;
	}


	public String toString() {
		return errorCodes + ", " + eg;
	}
}
