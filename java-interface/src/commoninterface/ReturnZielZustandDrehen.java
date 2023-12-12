package commoninterface;

public class ReturnZielZustandDrehen {
	Error derBiboError;
	Zustand derBiboZustand;
	Lage dieLageZielZustand;
	MaxAbmessungen dieBiboMaxAbmessungen;
	
	public ReturnZielZustandDrehen(Error einBiboError, Zustand einBiboZustand, Lage einLageZielZustand, MaxAbmessungen einBiboMaxAbmessungen) {
		setDerBiboError(einBiboError);
		setDerBiboZustand(einBiboZustand);
		setDieLageZielZustand(einLageZielZustand);
		setDieBiboMaxAbmessungen(einBiboMaxAbmessungen);
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

	public Lage getDieLageZielZustand() {
		return dieLageZielZustand;
	}

	public void setDieLageZielZustand(Lage einLageZielZustand) {
		this.dieLageZielZustand = einLageZielZustand;
	}

	public MaxAbmessungen getDieBiboMaxAbmessungen() {
		return dieBiboMaxAbmessungen;
	}

	public void setDieBiboMaxAbmessungen(MaxAbmessungen einBiboMaxAbmessungen) {
		this.dieBiboMaxAbmessungen = einBiboMaxAbmessungen;
	}
	
	public String toString() {
		return String.format("DerBiboError %s, DerBiboZustand %s, DieLageZielZustand %s, DieBiboMaxAbmessungen %s", derBiboError,derBiboZustand,dieLageZielZustand,dieBiboMaxAbmessungen);
	}
}
