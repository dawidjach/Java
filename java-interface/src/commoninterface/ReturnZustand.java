package commoninterface;

public class ReturnZustand {
	Error derBiboError;
	Zustand derBiboZustand;
	
	public ReturnZustand(Error einBiboError, Zustand einBiboZustand) {
		setDerBiboError(einBiboError);
		setDerBiboZustand(einBiboZustand);
	}
	
	public Error getDerBiboError() {
		return derBiboError;
	}
	
	private void setDerBiboError(Error einBiboError) {
		this.derBiboError = einBiboError;
	}
	
	public Zustand getDerBiboZustand() {
		return derBiboZustand;
	}
	
	private void setDerBiboZustand(Zustand einBiboZustand) {
		this.derBiboZustand = einBiboZustand;
	}
	
	public String toString() {
		return String.format("DerBiboError %s, DerBiboZustand %s", derBiboError, derBiboZustand);
	}
}
