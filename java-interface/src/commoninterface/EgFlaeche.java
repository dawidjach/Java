package commoninterface;


public class EgFlaeche {
	double dieZRVorschubX, dieZRVorschubY, dieZRSpantiefeX, dieZRSpantiefeY;

	public EgFlaeche(double eineZRVorschubX, double eineZRVorschubY, double eineZRSpantiefeX, double eineZRSpantiefeY) {
		setDieZRVorschubX(eineZRVorschubX);
		setDieZRVorschubY(eineZRVorschubY);
		
		setDieZRSpantiefeX(eineZRSpantiefeX);
		setDieZRSpantiefeY(eineZRSpantiefeY);
	}

	public double getDieZRVorschubX() {
		return dieZRVorschubX;
	}

	private void setDieZRVorschubX(double dieZRVorschubX) {
		this.dieZRVorschubX = dieZRVorschubX;
	}

	public double getDieZRVorschubY() {
		return dieZRVorschubY;
	}

	private void setDieZRVorschubY(double dieZRVorschubY) {
		this.dieZRVorschubY = dieZRVorschubY;
	}

	public double getDieZRSpantiefeX() {
		return dieZRSpantiefeX;
	}

	private void setDieZRSpantiefeX(double dieZRSpantiefeX) {
		this.dieZRSpantiefeX = dieZRSpantiefeX;
	}

	public double getDieZRSpantiefeY() {
		return dieZRSpantiefeY;
	}

	private void setDieZRSpantiefeY(double dieZRSpantiefeY) {
		this.dieZRSpantiefeY = dieZRSpantiefeY;
	}

	public String toString() {
		return String.format("DieZRVorschubX %s, DieZRVorschubY %s,\n"
				+ "DieZRSpantiefeX %s, DieZRSpantiefeY %s.", dieZRVorschubX, dieZRVorschubY, dieZRSpantiefeX, 
				dieZRSpantiefeY);
	}
}
