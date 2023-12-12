package commoninterface;

public class MaxAbmessungen {
	double derD;
	double dieL;
	
	public MaxAbmessungen(double einD, double eineL) {
		setDerD(einD);
		setDieL(eineL);
	}
	
	public double getDerD() {
		return derD;
	}
	
	private void setDerD(double derD) {
		this.derD = derD;
	}

	public double getDieL() {
		return dieL;
	}

	private void setDieL(double dieL) {
		this.dieL = dieL;
	}
	
	public String toString() {
		return String.format("DerD %s, DieL %s", derD, dieL);
	}
}
