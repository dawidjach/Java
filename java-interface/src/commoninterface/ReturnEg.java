package commoninterface;

public class ReturnEg {
	Error derBiboError;
	EgVolumen dieBiboEg;

	public ReturnEg(Error einBiboError, EgVolumen einBiboEg) {
		setDerBiboError(einBiboError);
		setDieBiboEg(einBiboEg);
	}

	public Error getDerBiboError() {
		return derBiboError;
	}

	private void setDerBiboError(Error einBiboError) {
		this.derBiboError = einBiboError;
	}

	public EgVolumen getDieBiboEg() {
		return dieBiboEg;
	}

	private void setDieBiboEg(EgVolumen einBiboEg) {
		this.dieBiboEg = einBiboEg;
	}

	public String toString() {
		return String.format("DieBiboErrors %s, DieBiboEg /%s", derBiboError, dieBiboEg);
	}
}
