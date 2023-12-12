package commoninterface;

public class Lage {
	double derDWX, derDWY, derDWZ;
	double dieVerschiebungX, dieVerschiebungY, dieVerschiebungZ;

	public Lage(double einDWX, double einDWY, double einDWZ, 
			double eineVerschiebungX, double eineVerschiebungY, double eineVerschiebungZ) {
		setDerDWX(einDWX);
		setDerDWY(einDWY);
		setDerDWZ(einDWZ);
		
		setDieVerschiebungX(eineVerschiebungX);
		setDieVerschiebungY(eineVerschiebungY);
		setDieVerschiebungZ(eineVerschiebungZ);
	}

	public double getDerDWX() {
		return derDWX;
	}

	private void setDerDWX(double derDWX) {
		this.derDWX = derDWX;
	}

	public double getDerDWY() {
		return derDWY;
	}

	private void setDerDWY(double derDWY) {
		this.derDWY = derDWY;
	}

	public double getDerDWZ() {
		return derDWZ;
	}

	private void setDerDWZ(double derDWZ) {
		this.derDWZ = derDWZ;
	}

	public double getDieVerschiebungX() {
		return dieVerschiebungX;
	}

	private void setDieVerschiebungX(double dieVerschiebungX) {
		this.dieVerschiebungX = dieVerschiebungX;
	}

	public double getDieVerschiebungY() {
		return dieVerschiebungY;
	}

	private void setDieVerschiebungY(double dieVerschiebungY) {
		this.dieVerschiebungY = dieVerschiebungY;
	}

	public double getDieVerschiebungZ() {
		return dieVerschiebungZ;
	}

	private void setDieVerschiebungZ(double dieVerschiebungZ) {
		this.dieVerschiebungZ = dieVerschiebungZ;
	}
	
	public String toString() {
		return String.format("DWX/DWY/DWZ %s/%s/%s, VerschiebungXYZ %s/%s/%s", derDWX, derDWY, derDWZ, dieVerschiebungX, dieVerschiebungY, dieVerschiebungZ);
	}
}
