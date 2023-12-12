package commoninterface;


public class Error {
	int derErrorCode;

	public Error(int einErrorCode) {
		setDerErrorCode(einErrorCode);
	}

	public int getDerErrorCode() {
		return derErrorCode;
	}
	
	private void setDerErrorCode(int einErrorCode) {
		this.derErrorCode = einErrorCode;
	}

	public String toString() {
		return String.format("BiboErrors %s", derErrorCode);
	}
}
