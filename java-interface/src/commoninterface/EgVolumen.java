package commoninterface;

import java.util.ArrayList;
import java.util.List;

public class EgVolumen {
	double dieZRVorschubX, dieZRVorschubY, dieZRSpantiefeX, dieZRSpantiefeY;
	List<Double> diePunktefolge = new ArrayList<>();

	public EgVolumen(double eineZRVorschubX, double eineZRVorschubY, double eineZRSpantiefeX, double eineZRSpantiefeY, List<Double> diePunktefolge) {
		setDieZRVorschubX(eineZRVorschubX);
		setDieZRVorschubY(eineZRVorschubY);
		
		setDieZRSpantiefeX(eineZRSpantiefeX);
		setDieZRSpantiefeY(eineZRSpantiefeY);
		
		setDiePunktefolge(diePunktefolge);
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

	public List<Double> getDiePunktefolge() {
		return diePunktefolge;
	}

	private void setDiePunktefolge(List<Double> diePunktefolge) {
		this.diePunktefolge = diePunktefolge;
	}

	public String toString() {
		return String.format("DieZRVorschubX %s, DieZRVorschubY %s,\n"
				+ "DieZRSpantiefeX %s, DieZRSpantiefeY %s,\n"
				+ "DiePunktefolge %s.", dieZRVorschubX, dieZRVorschubY, dieZRSpantiefeX, 
				dieZRSpantiefeY, diePunktefolge);
	}
}
