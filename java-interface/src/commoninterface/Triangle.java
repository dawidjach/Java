package commoninterface;

public class Triangle {
	double dieXKP1, dieYKP1, dieZKP1;
	double dieXKP2, dieYKP2, dieZKP2;
	double dieXKP3, dieYKP3, dieZKP3;
	double derXKNV, dieYKNV, dieZKNV;
	
	public Triangle(double eineXKP1, double eineYKP1, double eineZKP1, 
			double eineXKP2, double eineYKP2, double eineZKP2, 
			double eineXKP3, double eineYKP3, double eineZKP3,
			double einXKNV, double eineYKNV, double eineZKNV) {
		setDieXKP1(eineXKP1);
		setDieYKP1(eineYKP1);
		setDieZKP1(eineZKP1);
		
		setDieXKP2(eineXKP2);
		setDieYKP2(eineYKP2);
		setDieZKP2(eineZKP2);
		
		setDieXKP3(eineXKP3);
		setDieYKP3(eineYKP3);
		setDieZKP3(eineZKP3);
		
		setDerXKNV(einXKNV);
		setDieYKNV(eineYKNV);
		setDieZKNV(eineZKNV);
	}

	private double getDieXKP1() {
		return dieXKP1;
	}
	
	// um @SuppressWarnings("unused") zu vermeiden
	public double retrieveDieXKP1() {
		return getDieXKP1();
	}

	private void setDieXKP1(double dieXKP1) {
		this.dieXKP1 = dieXKP1;
	}

	private double getDieYKP1() {
		return dieYKP1;
	}

	// um @SuppressWarnings("unused") zu vermeiden
	public double retrieveDieYKP1() {
		return getDieYKP1();
	}
	
	private void setDieYKP1(double dieYKP1) {
		this.dieYKP1 = dieYKP1;
	}

	private double getDieZKP1() {
		return dieZKP1;
	}
	
	// um @SuppressWarnings("unused") zu vermeiden
	public double retrieveDieZKP1() {
		return getDieZKP1();
	}

	private void setDieZKP1(double dieZKP1) {
		this.dieZKP1 = dieZKP1;
	}

	private double getDieXKP2() {
		return dieXKP2;
	}
	
	// um @SuppressWarnings("unused") zu vermeiden
	public double retrieveDieXKP2() {
		return getDieXKP2();
	}

	private void setDieXKP2(double dieXKP2) {
		this.dieXKP2 = dieXKP2;
	}

	private double getDieYKP2() {
		return dieYKP2;
	}
	
	// um @SuppressWarnings("unused") zu vermeiden
	public double retrieveDieYKP2() {
		return getDieYKP2();
	}

	private void setDieYKP2(double dieYKP2) {
		this.dieYKP2 = dieYKP2;
	}

	private double getDieZKP2() {
		return dieZKP2;
	}
	
	// um @SuppressWarnings("unused") zu vermeiden
	public double retrieveDieZKP2() {
		return getDieZKP2();
	}

	private void setDieZKP2(double dieZKP2) {
		this.dieZKP2 = dieZKP2;
	}

	private double getDieXKP3() {
		return dieXKP3;
	}
	
	// um @SuppressWarnings("unused") zu vermeiden
	public double retrieveDieXKP3() {
		return getDieXKP3();
	}

	private void setDieXKP3(double dieXKP3) {
		this.dieXKP3 = dieXKP3;
	}

	private double getDieYKP3() {
		return dieYKP3;
	}
	
	// um @SuppressWarnings("unused") zu vermeiden
	public double retrieveDieYKP3() {
		return getDieYKP3();
	}

	private void setDieYKP3(double dieYKP3) {
		this.dieYKP3 = dieYKP3;
	}

	private double getDieZKP3() {
		return dieZKP3;
	}
	
	// um @SuppressWarnings("unused") zu vermeiden
	public double retrieveDieZKP3() {
		return getDieZKP3();
	}

	private void setDieZKP3(double dieZKP3) {
		this.dieZKP3 = dieZKP3;
	}

	private double getDerXKNV() {
		return derXKNV;
	}
	
	// um @SuppressWarnings("unused") zu vermeiden
	public double retrieveDerXKNV() {
		return getDerXKNV();
	}

	private void setDerXKNV(double derXKNV) {
		this.derXKNV = derXKNV;
	}

	private double getDieYKNV() {
		return dieYKNV;
	}
	
	// um @SuppressWarnings("unused") zu vermeiden
	public double retrieveDieYKNV() {
		return getDieYKNV();
	}

	private void setDieYKNV(double dieYKNV) {
		this.dieYKNV = dieYKNV;
	}

	private double getDieZKNV() {
		return dieZKNV;
	}
	
	// um @SuppressWarnings("unused") zu vermeiden
	public double retrieveDieZKNV() {
		return getDieZKNV();
	}

	private void setDieZKNV(double dieZKNV) {
		this.dieZKNV = dieZKNV;
	}
	
	public String toString() {
		return String.format("DieXKP1/DieYKP1/DieZKP1 %s/%s/%s "
				+ "/nDieXKP2/DieYKP2/DieZKP2 %s/%s/%s"
				+ "/nDieXKP3/DieYKP3/DieZKP3 %s/%s/%s"
				+ "/nDerXKNV/DieYKNV/DieZKNV %s/%s/%s", dieXKP1, dieYKP1, dieZKP1, dieXKP2, dieYKP2, dieZKP2, dieXKP3, dieYKP3, dieZKP3, derXKNV, dieYKNV, dieZKNV);
	}
}
